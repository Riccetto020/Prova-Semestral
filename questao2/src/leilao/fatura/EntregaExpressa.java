package leilao.fatura;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Decorator (papel: ConcreteDecorator): entrega expressa: R$ 450,00 fixos. */
public class EntregaExpressa extends ServicoAdicional {

    public EntregaExpressa(Compra compra) {
        super(compra);
    }

    @Override protected String descricaoDoServico() { return "Entrega expressa"; }

    @Override protected BigDecimal custoDoServico() { return new BigDecimal("450.00"); }
}
