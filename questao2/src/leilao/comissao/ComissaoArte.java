package leilao.comissao;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ComissaoArte implements RegraComissao {
    @Override public String descricao() { return "Arte (20%)"; }

    @Override public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.20")).setScale(2, RoundingMode.HALF_UP);
    }
}
