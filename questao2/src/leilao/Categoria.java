package leilao;

import leilao.comissao.RegraComissao;

public final class Categoria {

    private final String nome;
    private final RegraComissao regraPadrao;

    public Categoria(String nome, RegraComissao regraPadrao) {
        this.nome = nome;
        this.regraPadrao = regraPadrao;
    }

    public String nome() { return nome; }

    public RegraComissao regraPadrao() { return regraPadrao; }
}
