package leilao.interessados;

import leilao.Lance;
import leilao.Licitante;
import leilao.Lote;
import leilao.Moeda;
import leilao.ObservadorDeLote;

public class Participante implements Licitante, ObservadorDeLote {

    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    @Override public String nome() { return nome; }

    @Override
    public void aoReceberLance(Lote lote, Lance lance) {
        System.out.println("  [Participante " + nome + "] Lote '" + lote.nome()
                + "': novo lance de " + Moeda.formatar(lance.valor()));
    }
}
