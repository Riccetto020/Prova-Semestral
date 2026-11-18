package dominio;

import java.math.BigDecimal;

public record TabelaPrecos(BigDecimal vmPorHora, BigDecimal gbPorMes, BigDecimal filaPorMes) {

    public BigDecimal custoMensal(int qtdVm, int gb) {
        return CalculoDeCusto.mensal(vmPorHora, qtdVm, gbPorMes, gb, filaPorMes);
    }
}
