package dominio;

public record SolicitacaoAmbiente(String nome, String provedor, int qtdMaquinas, int gbArmazenamento) {

    public SolicitacaoAmbiente {
        if (qtdMaquinas < 0 || gbArmazenamento < 0) {
            throw new IllegalArgumentException("Quantidades nao podem ser negativas");
        }
    }
}
