import dominio.ProvisionadorDeAmbiente;
import dominio.SolicitacaoAmbiente;
import infra.Auditoria;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/** RF08: quatro provisionamentos (2 Aws, 1 Azure, 1 recusado por orcamento), todos em threads distintas. */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<SolicitacaoAmbiente> solicitacoes = List.of(
                new SolicitacaoAmbiente("ambiente-dev", "Aws", 2, 100),
                new SolicitacaoAmbiente("ambiente-hml", "Aws", 3, 500),
                new SolicitacaoAmbiente("ambiente-prod", "Azure", 4, 1000),
                new SolicitacaoAmbiente("ambiente-analytics", "Azure", 12, 500)); // excede o orcamento

        CountDownLatch largada = new CountDownLatch(1);
        List<Thread> threads = solicitacoes.stream().map(s -> new Thread(() -> {
            try {
                largada.await(); // todas as threads comecam ao mesmo tempo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            new ProvisionadorDeAmbiente(SeletorDeProvedor.obter(s.provedor())).provisionar(s);
        }, "prov-" + s.nome())).toList();

        threads.forEach(Thread::start);
        largada.countDown();
        for (Thread t : threads) {
            t.join();
        }

        System.out.println();
        Auditoria.getInstance().imprimirHistorico();
    }
}
