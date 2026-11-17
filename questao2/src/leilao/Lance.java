package leilao;

import java.math.BigDecimal;

/** Lance aceito: quem deu e quanto. */
public record Lance(Licitante licitante, BigDecimal valor) { }
