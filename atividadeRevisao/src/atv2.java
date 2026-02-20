import java.util.Scanner;

class Operacao {
    String tipo;
    String texto;
    int quantidade;

    Operacao(String t, String txt, int q) {
        tipo = t;
        texto = txt;
        quantidade = q;
    }
}

class Pilha {
    Operacao[] dados;
    int topo;

    Pilha(int capacidade) {
        dados = new Operacao[capacidade];
        topo = -1;
    }

    void push(Operacao op) {
        if (topo < dados.length - 1) {
            topo++;
            dados[topo] = op;
        }
    }

    Operacao pop() {
        if (topo == -1) return null;
        Operacao op = dados[topo];
        topo--;
        return op;
    }

    Operacao peek() {
        if (topo == -1) return null;
        return dados[topo];
    }

    boolean is_empty() {
        return topo == -1;
    }
}

public class atv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pilha desfazer = new Pilha(100);
        Pilha refazer = new Pilha(100);
        String texto = "";

        while (true) {
            String linha = sc.nextLine();

            if (linha.startsWith("INSERIR ")) {
                String add = linha.substring(8);
                texto = texto + add;
                desfazer.push(new Operacao("INSERIR", add, 0));
                refazer = new Pilha(100);
            }

            else if (linha.startsWith("REMOVER ")) {
                int n = Integer.parseInt(linha.substring(8));
                if (n > texto.length()) n = texto.length();
                String removido = texto.substring(texto.length() - n);
                texto = texto.substring(0, texto.length() - n);
                desfazer.push(new Operacao("REMOVER", removido, n));
                refazer = new Pilha(100);
            }

            else if (linha.equals("DESFAZER")) {
                if (!desfazer.is_empty()) {
                    Operacao op = desfazer.pop();
                    if (op.tipo.equals("INSERIR")) {
                        texto = texto.substring(0, texto.length() - op.texto.length());
                        refazer.push(op);
                    } else if (op.tipo.equals("REMOVER")) {
                        texto = texto + op.texto;
                        refazer.push(op);
                    }
                }
            }

            else if (linha.equals("REFAZER")) {
                if (!refazer.is_empty()) {
                    Operacao op = refazer.pop();
                    if (op.tipo.equals("INSERIR")) {
                        texto = texto + op.texto;
                        desfazer.push(op);
                    } else if (op.tipo.equals("REMOVER")) {
                        texto = texto.substring(0, texto.length() - op.texto.length());
                        desfazer.push(op);
                    }
                }
            }

            else if (linha.equals("IMPRIMIR")) {
                System.out.println(">> " + texto);
            }
        }
    }
}