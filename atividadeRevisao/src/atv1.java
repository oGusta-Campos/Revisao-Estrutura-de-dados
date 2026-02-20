import java.util.Scanner;

class Trabalho {
    int idAluno;
    String nomeArquivo;
    int paginas;

    Trabalho(int id, String nome, int pag) {
        idAluno = id;
        nomeArquivo = nome;
        paginas = pag;
    }
}

class FilaImpressao {
    Trabalho[] fila;
    int inicio;
    int fim;
    int tamanho;

    FilaImpressao(int capacidade) {
        fila = new Trabalho[capacidade];
        inicio = 0;
        fim = 0;
        tamanho = 0;
    }

    void enqueue(Trabalho t) {
        if (tamanho < fila.length) {
            fila[fim] = t;
            fim = (fim + 1) % fila.length;
            tamanho++;
        } else {
            System.out.println("Fila cheia");
        }
    }

    Trabalho dequeue() {
        if (tamanho == 0) {
            return null;
        }
        Trabalho t = fila[inicio];
        inicio = (inicio + 1) % fila.length;
        tamanho--;
        return t;
    }

    boolean vazia() {
        return tamanho == 0;
    }

    void exibir() {
        if (tamanho == 0) {
            System.out.println("Fila vazia");
            return;
        }
        int i = inicio;
        for (int c = 0; c < tamanho; c++) {
            Trabalho t = fila[i];
            System.out.println("ID: " + t.idAluno + " | Arquivo: " + t.nomeArquivo + " | Paginas: " + t.paginas);
            i = (i + 1) % fila.length;
        }
    }
}

public class atv1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FilaImpressao fila = new FilaImpressao(100);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("1 Adicionar trabalho");
            System.out.println("2 Imprimir proximo");
            System.out.println("3 Exibir fila");
            System.out.println("0 Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("ID do aluno: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Nome do arquivo: ");
                String nome = sc.nextLine();
                System.out.print("Numero de paginas: ");
                int pag = sc.nextInt();
                sc.nextLine();

                Trabalho t = new Trabalho(id, nome, pag);
                fila.enqueue(t);
            }

            if (opcao == 2) {
                Trabalho t = fila.dequeue();
                if (t == null) {
                    System.out.println("Fila vazia");
                } else {
                    System.out.println("Imprimindo: " + t.nomeArquivo + " do aluno " + t.idAluno);
                }
            }

            if (opcao == 3) {
                fila.exibir();
            }
        }
    }
}