package leilao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class Moeda {

    private Moeda() { }

    public static String formatar(BigDecimal valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00",
                DecimalFormatSymbols.getInstance(Locale.forLanguageTag("pt-BR")));
        return "R$ " + df.format(valor.setScale(2, RoundingMode.HALF_UP));
    }
}
