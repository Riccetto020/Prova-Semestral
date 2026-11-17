package leilao;

import leilao.comissao.RegraComissao;

import java.math.BigDecimal;

/**
 * Resultado de um lote vendido. Fixa a regra de comissao vigente NO MOMENTO DO ENCERRAMENTO (RF07).
 */
public final class Venda {

    private final Lote lote;
    private final Licitante comprador;
    private final BigDecimal valor;
    private final RegraComissao regraVigente;

    Venda(Lote lote, Licitante comprador, BigDecimal valor, RegraComissao regraVigente) {
        this.lote = lote;
        this.comprador = comprador;
        this.valor = valor;
        this.regraVigente = regraVigente;
    }

    public Lote lote() { return lote; }
    public Licitante comprador() { return comprador; }
    public BigDecimal valor() { return valor; }
    public RegraComissao regraVigente() { return regraVigente; }

    /** RF06. */
    public BigDecimal comissao() { return regraVigente.calcular(valor); }

    public BigDecimal liquidoVendedor() { return valor.subtract(comissao()); }
}
