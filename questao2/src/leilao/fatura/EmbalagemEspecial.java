package leilao.fatura;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Decorator (papel: ConcreteDecorator): embalagem especial: R$ 180,00 fixos. */
public class EmbalagemEspecial extends ServicoAdicional {

    public EmbalagemEspecial(Compra compra) {
        super(compra);
    }

    @Override protected String descricaoDoServico() { return "Embalagem especial"; }

    @Override protected BigDecimal custoDoServico() { return new BigDecimal("180.00"); }
}
