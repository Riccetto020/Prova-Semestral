package dominio;

import java.math.BigDecimal;

/** Formula unica do RF04, usada tanto na estimativa previa quanto no ambiente montado. */
public final class CalculoDeCusto {

    public static final BigDecimal HORAS_NO_MES = BigDecimal.valueOf(730);

    private CalculoDeCusto() { }

    public static BigDecimal mensal(BigDecimal vmPorHora, int qtdVm,
                                    BigDecimal gbPorMes, int gb,
                                    BigDecimal filaPorMes) {
        BigDecimal maquinas = vmPorHora.multiply(BigDecimal.valueOf(qtdVm)).multiply(HORAS_NO_MES);
        BigDecimal armazenamento = gbPorMes.multiply(BigDecimal.valueOf(gb));
        return maquinas.add(armazenamento).add(filaPorMes);
    }
}
