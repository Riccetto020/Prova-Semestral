package leilao;

/** Quem da lances. O Lote conhece apenas esta interface, nunca a classe concreta do participante. */
public interface Licitante {
    String nome();
}
