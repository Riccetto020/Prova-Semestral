package leilao;

import leilao.comissao.RegraComissao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class Lote {

    public enum Estado { ABERTO, VENDIDO, NAO_VENDIDO }

    private static final BigDecimal FATOR_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final BigDecimal precoInicial;
    private final BigDecimal precoReserva;
    private final List<Lance> lances = new ArrayList<>();
    private final List<ObservadorDeLote> observadores = new CopyOnWriteArrayList<>();
    private RegraComissao regraComissao;
    private Estado estado = Estado.ABERTO;

    public Lote(String nome, Categoria categoria, BigDecimal precoInicial, BigDecimal precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.regraComissao = categoria.regraPadrao();
    }

    public void acompanhar(ObservadorDeLote observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void deixarDeAcompanhar(ObservadorDeLote observador) {
        observadores.remove(observador);
    }

    private void notificar(Lance lance) {
        for (ObservadorDeLote o : observadores) {
            if (o != lance.licitante()) {
                o.aoReceberLance(this, lance);
            }
        }
    }

    public ResultadoLance darLance(Licitante licitante, BigDecimal valor) {
        if (estado != Estado.ABERTO) {
            return new ResultadoLance(false, "Lote '" + nome + "' esta encerrado e nao aceita lances.");
        }
        BigDecimal minimo = lanceAtual().multiply(FATOR_MINIMO);
        if (valor.compareTo(minimo) < 0) {
            return new ResultadoLance(false, "Lance de " + Moeda.formatar(valor) + " recusado: o minimo aceito e "
                    + Moeda.formatar(minimo) + " (5% acima do lance atual de " + Moeda.formatar(lanceAtual()) + ").");
        }
        Lance lance = new Lance(licitante, valor);
        lances.add(lance);
        notificar(lance);
        return new ResultadoLance(true, "Lance de " + Moeda.formatar(valor) + " aceito para '" + nome + "'.");
    }

    public BigDecimal lanceAtual() {
        return lances.isEmpty() ? precoInicial : lances.get(lances.size() - 1).valor();
    }

    public void definirRegraComissao(RegraComissao novaRegra) {
        if (estado != Estado.ABERTO) {
            throw new IllegalStateException("A regra so pode ser trocada antes do encerramento.");
        }
        this.regraComissao = novaRegra;
    }

    public RegraComissao regraComissao() { return regraComissao; }

    public Optional<Venda> encerrar() {
        if (estado != Estado.ABERTO) {
            throw new IllegalStateException("Lote ja encerrado.");
        }
        if (!lances.isEmpty() && lanceAtual().compareTo(precoReserva) >= 0) {
            estado = Estado.VENDIDO;
            Lance vencedor = lances.get(lances.size() - 1);
            return Optional.of(new Venda(this, vencedor.licitante(), vencedor.valor(), regraComissao));
        }
        estado = Estado.NAO_VENDIDO;
        return Optional.empty();
    }

    public String nome() { return nome; }
    public Categoria categoria() { return categoria; }
    public BigDecimal precoInicial() { return precoInicial; }
    public BigDecimal precoReserva() { return precoReserva; }
    public Estado estado() { return estado; }
    public int totalLances() { return lances.size(); }
}
