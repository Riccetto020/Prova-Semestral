package aws;

import dominio.Fila;

import java.math.BigDecimal;

final class AwsFila implements Fila {

    private final String id;

    AwsFila(int sequencial) {
        this.id = "sqs-" + sequencial;
    }

    @Override public String id() { return id; }

    @Override public BigDecimal custoMensal() { return AwsFactory.PRECOS.filaPorMes(); }
}
