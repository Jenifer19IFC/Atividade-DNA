package FrameworkExecutorAtividade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessadorDNA implements Runnable {
    
    private final String caminhoArq;

    public ProcessadorDNA(String caminhoArq) {
        this.caminhoArq = caminhoArq;
    }

    @Override
    public void run() {
        //System.out.printf("Iniciando leitura...");
        this.processaArq();
    }

    private void processaArq() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArq));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArq + "COMP"))) {
            String linha;
            int contFitasOrig = 0;
            int contFitasValidas = 0;
            int contFitasInvalidas = 0;

            while ((linha = leitor.readLine()) != null) {
                boolean valida = eValida(linha);
                if (valida) {
                	String fitaComplementar = gerarFitaComplementar(linha);
                    escritor.write(fitaComplementar + "\n");
                    contFitasValidas++;
                } else {
                    escritor.write("****FITA INVÁLIDA - " + linha + "\n");
                    contFitasInvalidas++;
                }
                contFitasOrig++;
            }

            System.out.println("CAMINHO: " + caminhoArq);
            System.out.println("Total de fitas do arquivo original: " +  contFitasOrig);
            System.out.println("Total de fitas válidas: " + contFitasValidas);
            System.out.println("Total de fitas inválidas: " + contFitasInvalidas);
            System.out.println("/n/n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String gerarFitaComplementar(String fitaOriginal) {
        StringBuilder fitaComplementar = new StringBuilder();
        for (char nucleotideo : fitaOriginal.toCharArray()) {
            switch (nucleotideo) {
                case 'A':
                    fitaComplementar.append('T');
                    break;
                case 'T':
                    fitaComplementar.append('A');
                    break;
                case 'C':
                    fitaComplementar.append('G');
                    break;
                case 'G':
                    fitaComplementar.append('C');
                    break;
                default:
                    break;
            }
        }
        return fitaComplementar.toString();
    }
    
    public static boolean eValida(String fitaOriginal) {
        for (char nucleotideo : fitaOriginal.toCharArray()) {
            if (nucleotideo != 'A' && nucleotideo != 'T' && nucleotideo != 'C' && nucleotideo != 'G') {
                return false; 
            }
        }
        return true; 
    }
}
