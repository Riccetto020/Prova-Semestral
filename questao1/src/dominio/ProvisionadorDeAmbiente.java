package dominio;

import infra.Auditoria;
import infra.Configuracao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public final class ProvisionadorDeAmbiente {

    private final ProvedorFactory fabrica;

    public ProvisionadorDeAmbiente(ProvedorFactory fabrica) {
        this.fabrica = fabrica;
    }

    public Optional<Ambiente> provisionar(SolicitacaoAmbiente solicitacao) {
        BigDecimal orcamento = Configuracao.getInstance().orcamentoMensalMaximo();
        BigDecimal estimado = fabrica.tabelaPrecos()
                .custoMensal(solicitacao.qtdMaquinas(), solicitacao.gbArmazenamento());

        if (estimado.compareTo(orcamento) > 0) {
            String motivo = "custo estimado " + moeda(estimado)
                    + " ultrapassa o orcamento de " + moeda(orcamento);
            Auditoria.getInstance().registrar(solicitacao.nome(), fabrica.nome(), "RECUSADO", motivo);
            System.out.println("[RECUSADO] " + solicitacao.nome() + " (" + fabrica.nome() + "): " + motivo);
            return Optional.empty();
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();
        Armazenamento armazenamento;
        Fila fila;

        synchronized (fabrica) {
            for (int i = 0; i < solicitacao.qtdMaquinas(); i++) {
                maquinas.add(fabrica.criarMaquinaVirtual());
            }
            armazenamento = fabrica.criarArmazenamento(solicitacao.gbArmazenamento());
            fila = fabrica.criarFila();
        }

        Ambiente ambiente = new Ambiente(solicitacao.nome(), fabrica.nome(),
                Configuracao.getInstance().regiaoPadrao(), maquinas, armazenamento, fila);

        Auditoria.getInstance().registrar(solicitacao.nome(), fabrica.nome(), "ACEITO",
                "custo estimado " + moeda(ambiente.custoMensalEstimado()));

        System.out.println(resumo(ambiente));
        return Optional.of(ambiente);
    }

    private static String resumo(Ambiente a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ACEITO] Ambiente: ").append(a.nome()).append('\n');
        sb.append("  Provedor: ").append(a.provedor()).append('\n');
        sb.append("  Regiao: ").append(a.regiao()).append('\n');
        a.maquinas().forEach(m -> sb.append("  Maquina virtual: ").append(m.id()).append('\n'));
        sb.append("  Armazenamento: ").append(a.armazenamento().id())
                .append(" (").append(a.armazenamento().gb()).append(" GB)\n");
        sb.append("  Fila: ").append(a.fila().id()).append('\n');
        sb.append("  Custo mensal estimado: ").append(moeda(a.custoMensalEstimado()));
        return sb.toString();
    }

    public static String moeda(BigDecimal valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.forLanguageTag("pt-BR")));
        return "R$ " + df.format(valor.setScale(2, RoundingMode.HALF_UP));
    }
}
