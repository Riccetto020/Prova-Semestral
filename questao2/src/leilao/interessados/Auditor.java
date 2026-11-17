package leilao.interessados;

import leilao.Lance;
import leilao.Lote;
import leilao.Moeda;
import leilao.ObservadorDeLote;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** Observer concreto: registra todos os lances aceitos com data e hora. */
public class Auditor implements ObservadorDeLote {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<String> registros = new ArrayList<>();

    @Override
    public void aoReceberLance(Lote lote, Lance lance) {
        String linha = LocalDateTime.now().format(FORMATO) + " | Lote '" + lote.nome() + "' | "
                + lance.licitante().nome() + " | " + Moeda.formatar(lance.valor());
        registros.add(linha);
        System.out.println("  [Auditor] registrado: " + linha);
    }

    public List<String> registros() { return List.copyOf(registros); }

    public void imprimirRegistros() {
        System.out.println("=== Registro do auditor (" + registros.size() + " lances aceitos) ===");
        registros.forEach(r -> System.out.println("  " + r));
    }
}
