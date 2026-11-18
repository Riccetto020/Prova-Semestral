package infra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Auditoria {

    private static final Auditoria INSTANCIA = new Auditoria();

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private Auditoria() { }

    public static Auditoria getInstance() {
        return INSTANCIA;
    }

    public synchronized void registrar(String ambiente, String provedor, String resultado, String motivo) {
        registros.add(new RegistroAuditoria(LocalDateTime.now(), ambiente, provedor, resultado, motivo));
    }

    public synchronized List<RegistroAuditoria> historico() {
        return Collections.unmodifiableList(new ArrayList<>(registros));
    }

    public void imprimirHistorico() {
        List<RegistroAuditoria> copia = historico();
        System.out.println("=== Historico de auditoria (" + copia.size() + " registros) ===");
        copia.forEach(r -> System.out.println(r));
    }
}
