import aws.AwsFactory;
import azure.AzureFactory;
import dominio.ProvedorFactory;

/**
 * Unico ponto (alem da Main) que conhece as fabricas concretas (RNF03/RNF06).
 * Para incluir o Gcp: criar GcpFactory + recursos e acrescentar UMA linha aqui.
 * Mantem uma instancia por provedor para que os sequenciais sejam compartilhados.
 */
public final class SeletorDeProvedor {

    private static final ProvedorFactory AWS = new AwsFactory();
    private static final ProvedorFactory AZURE = new AzureFactory();

    private SeletorDeProvedor() { }

    public static ProvedorFactory obter(String provedor) {
        return switch (provedor.toLowerCase()) {
            case "aws" -> AWS;
            case "azure" -> AZURE;
            default -> throw new IllegalArgumentException("Provedor desconhecido: " + provedor);
        };
    }
}
