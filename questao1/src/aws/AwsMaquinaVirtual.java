package aws;

import dominio.MaquinaVirtual;

import java.math.BigDecimal;

final class AwsMaquinaVirtual implements MaquinaVirtual {

    private final String id;

    AwsMaquinaVirtual(int sequencial) {
        this.id = "i-" + sequencial;
    }

    @Override public String id() { return id; }

    @Override public BigDecimal custoPorHora() { return AwsFactory.PRECOS.vmPorHora(); }
}
