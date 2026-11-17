package leilao.comissao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Veiculos: 8% do valor, limitada a R$ 5.000,00. */
public class ComissaoVeiculos implements RegraComissao {
    private static final BigDecimal TETO = new BigDecimal("5000.00");

    @Override public String descricao() { return "Veiculos (8%, maximo R$ 5.000,00)"; }

    @Override public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.08")).setScale(2, RoundingMode.HALF_UP).min(TETO);
    }
}
