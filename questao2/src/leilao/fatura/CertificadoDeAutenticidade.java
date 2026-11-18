package leilao.fatura;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CertificadoDeAutenticidade extends ServicoAdicional {

    public CertificadoDeAutenticidade(Compra compra) {
        super(compra);
    }

    @Override protected String descricaoDoServico() { return "Certificado de autenticidade"; }

    @Override protected BigDecimal custoDoServico() { return new BigDecimal("300.00"); }
}
