package FrameworkExecutorAtividade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {

    public static void main(String[] args) throws InterruptedException {
     
        String[] caminhosArq = {
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-0.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-1.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-2.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-3.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-4.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-5.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-6.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-7.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-8.txt",
            "/home/jenifer/eclipse-workspace/pda/src/FrameworkExecutorAtividade/dna-9.txt"
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (String caminhoArq : caminhosArq) {
            executorService.execute(new ProcessadorDNA(caminhoArq));
        }
        
        executorService.shutdown();

        // Aguarda a conclusão de todas as tarefas
        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }

        System.out.println("\nTodas as tarefas foram concluídas.");
    }
}
