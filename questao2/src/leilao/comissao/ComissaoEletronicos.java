package leilao.comissao;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Eletronicos: 12% do valor, com minimo de R$ 50,00. */
public class ComissaoEletronicos implements RegraComissao {
    private static final BigDecimal MINIMO = new BigDecimal("50.00");

    @Override public String descricao() { return "Eletronicos (12%, minimo R$ 50,00)"; }

    @Override public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.12")).setScale(2, RoundingMode.HALF_UP).max(MINIMO);
    }
}
