package infra;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class Configuracao {

    private final String regiaoPadrao;
    private final BigDecimal orcamentoMensalMaximo;

    private Configuracao() {
        Properties p = new Properties();
        Path arquivo = localizarArquivo();
        try (InputStream in = Files.newInputStream(arquivo)) {
            p.load(in);
        } catch (IOException e) {
            throw new UncheckedIOException("Nao foi possivel ler " + arquivo, e);
        }
        System.out.println("[Configuracao] lida uma unica vez de " + arquivo.toAbsolutePath());
        this.regiaoPadrao = p.getProperty("regiao.padrao");
        this.orcamentoMensalMaximo = new BigDecimal(p.getProperty("orcamento.mensal.maximo"));
    }

    private static Path localizarArquivo() {

        for (String candidato : new String[]{"config.properties", "../config.properties", "questao1/config.properties"}) {
            Path p = Path.of(candidato);
            if (Files.exists(p)) {
                return p;
            }
        }
        throw new IllegalStateException("config.properties nao encontrado");
    }

    private static final class Holder {
        private static final Configuracao INSTANCIA = new Configuracao();
    }

    public static Configuracao getInstance() {
        return Holder.INSTANCIA;
    }

    public String regiaoPadrao() { return regiaoPadrao; }

    public BigDecimal orcamentoMensalMaximo() { return orcamentoMensalMaximo; }
}
