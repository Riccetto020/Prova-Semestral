package leilao.fatura;

import java.math.BigDecimal;

/** Linha de servico adicional na fatura. */
public record ServicoContratado(String descricao, BigDecimal custo) { }
