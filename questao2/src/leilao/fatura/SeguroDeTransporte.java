package leilao.fatura;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Decorator (papel: ConcreteDecorator): seguro de transporte: 1,5% do valor de venda. */
public class SeguroDeTransporte extends ServicoAdicional {

    public SeguroDeTransporte(Compra compra) {
        super(compra);
    }

    @Override protected String descricaoDoServico() { return "Seguro de transporte (1,5%)"; }

    @Override protected BigDecimal custoDoServico() { return compra.valorVenda().multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP); }
}
