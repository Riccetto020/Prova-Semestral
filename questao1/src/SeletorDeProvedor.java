import aws.AwsFactory;
import azure.AzureFactory;
import dominio.ProvedorFactory;

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
