package leilao.comissao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComissaoImoveis implements RegraComissao {
    private static final BigDecimal FIXO = new BigDecimal("1500.00");

    @Override public String descricao() { return "Imoveis (3% + R$ 1.500,00)"; }

    @Override public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP).add(FIXO);
    }
}
