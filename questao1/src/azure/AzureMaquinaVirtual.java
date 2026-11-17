package azure;

import dominio.MaquinaVirtual;

import java.math.BigDecimal;

final class AzureMaquinaVirtual implements MaquinaVirtual {

    private final String id;

    AzureMaquinaVirtual(int sequencial) {
        this.id = "vm-" + sequencial;
    }

    @Override public String id() { return id; }

    @Override public BigDecimal custoPorHora() { return AzureFactory.PRECOS.vmPorHora(); }
}
