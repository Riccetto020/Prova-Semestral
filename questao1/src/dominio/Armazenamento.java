package dominio;

import java.math.BigDecimal;

/** Produto abstrato B da familia: armazenamento de objetos. */
public interface Armazenamento {
    String id();
    int gb();
    BigDecimal custoPorGbMes();
}
