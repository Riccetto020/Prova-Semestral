package leilao.fatura;

import java.math.BigDecimal;
import java.util.List;

/**
 * Padrao Decorator (papel: Component). A fatura e impressa sempre atraves desta interface (RNF04).
 */
public interface Compra {
    String lote();
    BigDecimal valorVenda();
    List<ServicoContratado> servicos();
    BigDecimal total();
}
