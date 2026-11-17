package dominio;

/** RF03: dados informados na solicitacao de um ambiente. */
public record SolicitacaoAmbiente(String nome, String provedor, int qtdMaquinas, int gbArmazenamento) {

    public SolicitacaoAmbiente {
        if (qtdMaquinas < 0 || gbArmazenamento < 0) {
            throw new IllegalArgumentException("Quantidades nao podem ser negativas");
        }
    }
}
