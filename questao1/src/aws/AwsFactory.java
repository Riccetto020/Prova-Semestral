package aws;

import dominio.Armazenamento;
import dominio.Fila;
import dominio.MaquinaVirtual;
import dominio.ProvedorFactory;
import dominio.TabelaPrecos;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/** Padrao Abstract Factory (papel: ConcreteFactory). Sequenciais atomicos: seguro com threads. */
public final class AwsFactory implements ProvedorFactory {

    static final TabelaPrecos PRECOS = new TabelaPrecos(
            new BigDecimal("0.10"), new BigDecimal("0.023"), new BigDecimal("0.40"));

    private final AtomicInteger seqVm = new AtomicInteger();
    private final AtomicInteger seqArmazenamento = new AtomicInteger();
    private final AtomicInteger seqFila = new AtomicInteger();

    @Override public String nome() { return "Aws"; }

    @Override public TabelaPrecos tabelaPrecos() { return PRECOS; }

    @Override public MaquinaVirtual criarMaquinaVirtual() {
        return new AwsMaquinaVirtual(seqVm.incrementAndGet());
    }

    @Override public Armazenamento criarArmazenamento(int gb) {
        return new AwsArmazenamento(seqArmazenamento.incrementAndGet(), gb);
    }

    @Override public Fila criarFila() {
        return new AwsFila(seqFila.incrementAndGet());
    }
}
