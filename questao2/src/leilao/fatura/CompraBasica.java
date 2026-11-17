package leilao.fatura;

import leilao.Venda;

import java.math.BigDecimal;
import java.util.List;

/** Decorator (papel: ConcreteComponent): a compra do lote vendido, sem servicos. */
public class CompraBasica implements Compra {

    private final Venda venda;

    public CompraBasica(Venda venda) {
        this.venda = venda;
    }

    @Override public String lote() { return venda.lote().nome(); }

    @Override public BigDecimal valorVenda() { return venda.valor(); }

    @Override public List<ServicoContratado> servicos() { return List.of(); }

    @Override public BigDecimal total() { return venda.valor(); }
}
