package dominio;

import java.math.BigDecimal;

/** Produto abstrato A da familia: maquina virtual. */
public interface MaquinaVirtual {
    String id();
    BigDecimal custoPorHora();
}
