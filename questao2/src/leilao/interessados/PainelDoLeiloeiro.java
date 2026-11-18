package leilao.interessados;

import leilao.Lance;
import leilao.Lote;
import leilao.Moeda;
import leilao.ObservadorDeLote;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PainelDoLeiloeiro implements ObservadorDeLote {

    private static final class Estatistica {
        BigDecimal maior = BigDecimal.ZERO;
        int total;
    }

    private final Map<Lote, Estatistica> porLote = new HashMap<>();

    @Override
    public void aoReceberLance(Lote lote, Lance lance) {
        Estatistica e = porLote.computeIfAbsent(lote, l -> new Estatistica());
        e.total++;
        e.maior = e.maior.max(lance.valor());
        System.out.println("  [Painel] Lote '" + lote.nome() + "': maior lance " + Moeda.formatar(e.maior)
                + " | total de lances: " + e.total);
    }
}
