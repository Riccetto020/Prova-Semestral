package leilao;

/**
 * Padrao Observer (papel: Observer). O Lote (Subject) so depende desta interface (RNF01).
 */
public interface ObservadorDeLote {
    void aoReceberLance(Lote lote, Lance lance);
}
