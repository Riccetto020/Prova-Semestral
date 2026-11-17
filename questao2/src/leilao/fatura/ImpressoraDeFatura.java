package leilao.fatura;

import leilao.Moeda;

/**
 * RF09/RNF04: trata igualmente uma compra sem servicos e uma com qualquer quantidade deles,
 * sem verificar quais servicos foram contratados.
 */
public final class ImpressoraDeFatura {

    private ImpressoraDeFatura() { }

    public static void imprimir(Compra compra) {
        System.out.println("--- FATURA DO COMPRADOR ---");
        System.out.println("Lote: " + compra.lote());
        System.out.println("Valor de venda: " + Moeda.formatar(compra.valorVenda()));
        for (ServicoContratado s : compra.servicos()) {
            System.out.println("  + " + s.descricao() + ": " + Moeda.formatar(s.custo()));
        }
        System.out.println("TOTAL A PAGAR: " + Moeda.formatar(compra.total()));
    }
}
