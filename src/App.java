import java.util.Random;

public class App {
    static int[] tamanhosTesteGrande = {500_000, 1_000_000, 2_000_000, 3_000_000, 5_000_000, 10_000_000};
    static int[] tamanhosTesteMedio = {12_500, 25_000, 50_000, 100_000, 200_000};
    static int[] tamanhosTestePequeno = {3, 6, 12, 24, 48};
    static Random aleatorio = new Random(42);

    static int codigo1(int[] vetor) {
        System.out.printf("Executando Código 1 com vetor de tamanho %d%n", vetor.length);
        long inicio = System.nanoTime();
        int resposta = 0;
        int operacoes = 0;

        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i] % 2;
            operacoes++;
        }

        long tempoExecucao = System.nanoTime() - inicio;
        System.out.printf("Código 1 - Operações: %d | Tempo: %.6f ms%n%n", operacoes, tempoExecucao / 1e6);
        return resposta;
    }

    static int codigo2(int[] vetor) {
        System.out.printf("Executando Código 2 com vetor de tamanho %d%n", vetor.length);
        long inicio = System.nanoTime();
        int contador = 0;
        int operacoes = 0;

        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                contador++;
                operacoes++;
            }
        }

        long tempoExecucao = System.nanoTime() - inicio;
        System.out.printf("Código 2 - Operações: %d | Tempo: %.6f ms%n%n", operacoes, tempoExecucao / 1e6);
        return contador;
    }

    static void codigo3(int[] vetor) {
        System.out.printf("Executando Código 3 com vetor de tamanho %d%n", vetor.length);
        long inicio = System.nanoTime();
        int operacoes = 0;

        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
            operacoes++;
        }

        long tempoExecucao = System.nanoTime() - inicio;
        System.out.printf("Código 3 - Operações: %d | Tempo: %.6f ms%n%n", operacoes, tempoExecucao / 1e6);
    }

    static int codigo4(int n) {
        System.out.printf("Executando Código 4 com n = %d%n", n);
        long inicio = System.nanoTime();
        int[] operacoes = {0};

        int resultado = codigo4Aux(n, operacoes);

        long tempoExecucao = System.nanoTime() - inicio;
        System.out.printf("Código 4 - Operações: %d | Tempo: %.6f ms%n%n", operacoes[0], tempoExecucao / 1e6);
        return resultado;
    }

    static int codigo4Aux(int n, int[] operacoes) {
        operacoes[0]++;
        if (n <= 2)
            return 1;
        else
            return codigo4Aux(n - 1, operacoes) + codigo4Aux(n - 2, operacoes);
    }

    static int[] gerarVetor(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho / 2);
        }
        return vetor;
    }

    public static void main(String[] args) {
        System.out.println("\n===== Iniciando Testes =====\n");

        for (int tamanho : tamanhosTesteGrande) {
            int[] vetorTeste = gerarVetor(tamanho);
            codigo1(vetorTeste);
            codigo2(vetorTeste);
        }

        for (int tamanho : tamanhosTesteMedio) {
            int[] vetorTeste = gerarVetor(tamanho);
            codigo3(vetorTeste);
        }

        for (int tamanho : tamanhosTestePequeno) {
            codigo4(tamanho);
        }
    }
}
