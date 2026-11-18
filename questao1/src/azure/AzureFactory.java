package azure;

import dominio.Armazenamento;
import dominio.Fila;
import dominio.MaquinaVirtual;
import dominio.ProvedorFactory;
import dominio.TabelaPrecos;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public final class AzureFactory implements ProvedorFactory {

    static final TabelaPrecos PRECOS = new TabelaPrecos(
            new BigDecimal("0.12"), new BigDecimal("0.02"), new BigDecimal("0.50"));

    private final AtomicInteger seqVm = new AtomicInteger();
    private final AtomicInteger seqArmazenamento = new AtomicInteger();
    private final AtomicInteger seqFila = new AtomicInteger();

    @Override public String nome() { return "Azure"; }

    @Override public TabelaPrecos tabelaPrecos() { return PRECOS; }

    @Override public MaquinaVirtual criarMaquinaVirtual() {
        return new AzureMaquinaVirtual(seqVm.incrementAndGet());
    }

    @Override public Armazenamento criarArmazenamento(int gb) {
        return new AzureArmazenamento(seqArmazenamento.incrementAndGet(), gb);
    }

    @Override public Fila criarFila() {
        return new AzureFila(seqFila.incrementAndGet());
    }
}
