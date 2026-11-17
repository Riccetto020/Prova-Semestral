package azure;

import dominio.Armazenamento;

import java.math.BigDecimal;

final class AzureArmazenamento implements Armazenamento {

    private final String id;
    private final int gb;

    AzureArmazenamento(int sequencial, int gb) {
        this.id = "blob://" + sequencial;
        this.gb = gb;
    }

    @Override public String id() { return id; }

    @Override public int gb() { return gb; }

    @Override public BigDecimal custoPorGbMes() { return AzureFactory.PRECOS.gbPorMes(); }
}
