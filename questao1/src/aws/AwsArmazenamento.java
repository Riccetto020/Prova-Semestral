package aws;

import dominio.Armazenamento;

import java.math.BigDecimal;

final class AwsArmazenamento implements Armazenamento {

    private final String id;
    private final int gb;

    AwsArmazenamento(int sequencial, int gb) {
        this.id = "s3://" + sequencial;
        this.gb = gb;
    }

    @Override public String id() { return id; }

    @Override public int gb() { return gb; }

    @Override public BigDecimal custoPorGbMes() { return AwsFactory.PRECOS.gbPorMes(); }
}
