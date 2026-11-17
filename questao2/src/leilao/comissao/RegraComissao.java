package leilao.comissao;

import java.math.BigDecimal;

/** Padrao Strategy (papel: Strategy). */
public interface RegraComissao {
    String descricao();
    BigDecimal calcular(BigDecimal valorVenda);
}
