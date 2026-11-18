package leilao.fatura;

import java.math.BigDecimal;
import java.util.List;

public interface Compra {
    String lote();
    BigDecimal valorVenda();
    List<ServicoContratado> servicos();
    BigDecimal total();
}
