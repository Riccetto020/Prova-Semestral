package dominio;

import java.math.BigDecimal;

public interface Armazenamento {
    String id();
    int gb();
    BigDecimal custoPorGbMes();
}
