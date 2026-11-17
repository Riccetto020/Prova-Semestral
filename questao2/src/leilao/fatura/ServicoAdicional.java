package leilao.fatura;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Decorator (papel: Decorator). Envolve outra {@link Compra} e acrescenta uma linha e um custo.
 * Servicos se combinam por composicao, em qualquer ordem e quantidade (RNF03).
 */
public abstract class ServicoAdicional implements Compra {

    protected final Compra compra;

    protected ServicoAdicional(Compra compra) {
        this.compra = compra;
    }

    protected abstract String descricaoDoServico();

    protected abstract BigDecimal custoDoServico();

    @Override public String lote() { return compra.lote(); }

    @Override public BigDecimal valorVenda() { return compra.valorVenda(); }

    @Override
    public List<ServicoContratado> servicos() {
        List<ServicoContratado> todos = new ArrayList<>(compra.servicos());
        todos.add(new ServicoContratado(descricaoDoServico(), custoDoServico()));
        return List.copyOf(todos);
    }

    @Override public BigDecimal total() { return compra.total().add(custoDoServico()); }
}
