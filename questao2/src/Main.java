import leilao.Categoria;
import leilao.ImpressoraDeAcerto;
import leilao.Lote;
import leilao.Venda;
import leilao.comissao.ComissaoArte;
import leilao.comissao.ComissaoEletronicos;
import leilao.fatura.CertificadoDeAutenticidade;
import leilao.fatura.Compra;
import leilao.fatura.CompraBasica;
import leilao.fatura.EmbalagemEspecial;
import leilao.fatura.EntregaExpressa;
import leilao.fatura.ImpressoraDeFatura;
import leilao.fatura.SeguroDeTransporte;
import leilao.interessados.Auditor;
import leilao.interessados.PainelDoLeiloeiro;
import leilao.interessados.Participante;

import java.math.BigDecimal;
import java.util.Optional;

public class Main {

    private static BigDecimal v(String s) { return new BigDecimal(s); }

    private static void lance(Lote lote, Participante p, String valor) {
        System.out.println(p.nome() + " da lance de R$ " + valor + " em '" + lote.nome() + "'");
        System.out.println("  -> " + lote.darLance(p, v(valor)).mensagem());
    }

    public static void main(String[] args) {
        Categoria arte = new Categoria("Arte", new ComissaoArte());
        Categoria eletronicos = new Categoria("Eletronicos", new ComissaoEletronicos());

        Lote quadro = new Lote("Quadro Paisagem", arte, v("1000.00"), v("1500.00"));
        Lote notebook = new Lote("Notebook Pro", eletronicos, v("2000.00"), v("4000.00"));

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelDoLeiloeiro painel = new PainelDoLeiloeiro();
        Auditor auditor = new Auditor();

        for (var o : new leilao.ObservadorDeLote[]{ana, bruno, carla, painel, auditor}) {
            quadro.acompanhar(o);
        }
        for (var o : new leilao.ObservadorDeLote[]{ana, carla, painel, auditor}) {
            notebook.acompanhar(o);
        }

        System.out.println("=== LANCES ===");
        lance(quadro, bruno, "1050.00");
        lance(quadro, ana, "1080.00");
        lance(quadro, ana, "1200.00");

        System.out.println("\nCarla deixa de acompanhar o '" + quadro.nome() + "'");
        quadro.deixarDeAcompanhar(carla);
        lance(quadro, bruno, "1600.00");

        lance(notebook, ana, "2100.00");
        lance(notebook, carla, "2500.00");

        System.out.println("\n=== CAMPANHA ===");
        System.out.println("Regra atual do '" + quadro.nome() + "': " + quadro.regraComissao().descricao());
        quadro.definirRegraComissao(new ComissaoEletronicos());
        System.out.println("Regra apos a campanha: " + quadro.regraComissao().descricao());

        System.out.println("\n=== ENCERRAMENTO ===");
        Optional<Venda> vendaQuadro = quadro.encerrar();
        Optional<Venda> vendaNotebook = notebook.encerrar();
        System.out.println("'" + quadro.nome() + "': " + quadro.estado());
        System.out.println("'" + notebook.nome() + "': " + notebook.estado()
                + " (maior lance abaixo da reserva)");
        lance(quadro, carla, "9999.00");
        System.out.println();

        vendaQuadro.ifPresent(venda -> {
            ImpressoraDeAcerto.imprimir(venda);
            System.out.println();

            System.out.println("Comprador: " + venda.comprador().nome());

            Compra semServicos = new CompraBasica(venda);
            ImpressoraDeFatura.imprimir(semServicos);
            System.out.println();

            Compra compra = new CompraBasica(venda);
            compra = new SeguroDeTransporte(compra);
            compra = new CertificadoDeAutenticidade(compra);
            compra = new EmbalagemEspecial(compra);
            compra = new EmbalagemEspecial(compra);
            compra = new EntregaExpressa(compra);
            ImpressoraDeFatura.imprimir(compra);
        });
        System.out.println();
        auditor.imprimirRegistros();
    }
}
