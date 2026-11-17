package dominio;

import java.math.BigDecimal;

/** Precos unitarios de um provedor. Permite estimar custo sem criar recursos (RF05). */
public record TabelaPrecos(BigDecimal vmPorHora, BigDecimal gbPorMes, BigDecimal filaPorMes) {

    public BigDecimal custoMensal(int qtdVm, int gb) {
        return CalculoDeCusto.mensal(vmPorHora, qtdVm, gbPorMes, gb, filaPorMes);
    }
}
