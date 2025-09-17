import controller.ContaController;
import controller.TransacaoController;
import view.BancoView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        BancoView view = new BancoView();
        view.mostrarCabecalho();
        
        ContaController conta = new ContaController("12345-6", 1000.0);
        
        AtomicInteger saquesRealizados = new AtomicInteger(0);
        AtomicInteger depositosRealizados = new AtomicInteger(0);
        
        view.mostrarInicioTransacoes();
        
        ExecutorService executor = Executors.newFixedThreadPool(20);
        
        for (int i = 1; i <= 20; i++) {
            final int transacaoId = i;
            executor.execute(() -> {
                TransacaoController transacao = new TransacaoController(conta, transacaoId);
                transacao.run();
            });
        }
        
        executor.shutdown();
        
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Tempo limite excedido. Forcando finalizacao...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        view.mostrarEstatisticas(10, 10, conta.getSaldo());
    }
}