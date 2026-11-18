package leilao.comissao;

import java.math.BigDecimal;

public interface RegraComissao {
    String descricao();
    BigDecimal calcular(BigDecimal valorVenda);
}
