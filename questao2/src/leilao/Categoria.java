package leilao;

import leilao.comissao.RegraComissao;

/** Categoria do lote, com a regra de comissao padrao dela (usada no cadastro do lote). */
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
