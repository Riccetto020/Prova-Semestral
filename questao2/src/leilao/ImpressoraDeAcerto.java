package leilao;

public final class ImpressoraDeAcerto {

    private ImpressoraDeAcerto() { }

    public static void imprimir(Venda venda) {
        System.out.println("--- ACERTO DO VENDEDOR ---");
        System.out.println("Lote: " + venda.lote().nome());
        System.out.println("Valor de venda: " + Moeda.formatar(venda.valor()));
        System.out.println("Comissao cobrada [" + venda.regraVigente().descricao() + "]: "
                + Moeda.formatar(venda.comissao()));
        System.out.println("Valor liquido a repassar: " + Moeda.formatar(venda.liquidoVendedor()));
    }
}
