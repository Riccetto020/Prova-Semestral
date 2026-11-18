package dominio;

public interface ProvedorFactory {
    String nome();
    TabelaPrecos tabelaPrecos();
    MaquinaVirtual criarMaquinaVirtual();
    Armazenamento criarArmazenamento(int gb);
    Fila criarFila();
}
