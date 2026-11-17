package azure;

import dominio.Fila;

import java.math.BigDecimal;

final class AzureFila implements Fila {

    private final String id;

    AzureFila(int sequencial) {
        this.id = "sb-" + sequencial;
    }

    @Override public String id() { return id; }

    @Override public BigDecimal custoMensal() { return AzureFactory.PRECOS.filaPorMes(); }
}
