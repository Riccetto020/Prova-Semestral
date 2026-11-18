package infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record RegistroAuditoria(LocalDateTime dataHora, String ambiente, String provedor,
                                String resultado, String motivo) {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    @Override
    public String toString() {
        return dataHora.format(FORMATO) + " | " + ambiente + " | " + provedor + " | " + resultado + " (" + motivo + ")";
    }
}
