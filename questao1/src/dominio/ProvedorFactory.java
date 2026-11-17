package dominio;

/**
 * Padrao Abstract Factory (papel: AbstractFactory).
 * Cada implementacao cria a familia completa de recursos de UM provedor, o que
 * impede combinar recursos de provedores diferentes (RNF01).
 */
public interface ProvedorFactory {
    String nome();
    TabelaPrecos tabelaPrecos();
    MaquinaVirtual criarMaquinaVirtual();
    Armazenamento criarArmazenamento(int gb);
    Fila criarFila();
}
