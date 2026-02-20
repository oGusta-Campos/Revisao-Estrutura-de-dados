import java.util.Scanner;

public class atv3 {

    static boolean buscar(char[][] lab, boolean[][] vis, int i, int j, int si, int sj) {
        if (i < 0 || j < 0 || i >= lab.length || j >= lab[0].length) return false;
        if (lab[i][j] == '#' || vis[i][j]) return false;
        if (i == si && j == sj) return true;

        vis[i][j] = true;

        if (buscar(lab, vis, i - 1, j, si, sj) ||
            buscar(lab, vis, i + 1, j, si, sj) ||
            buscar(lab, vis, i, j - 1, si, sj) ||
            buscar(lab, vis, i, j + 1, si, sj)) {

            if (lab[i][j] != 'E') lab[i][j] = '*';
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        char[][] lab = new char[n][m];
        boolean[][] vis = new boolean[n][m];

        int ei = 0, ej = 0, si = 0, sj = 0;

        for (int i = 0; i < n; i++) {
            String linha = sc.nextLine();
            for (int j = 0; j < m; j++) {
                lab[i][j] = linha.charAt(j);
                if (lab[i][j] == 'E') {
                    ei = i;
                    ej = j;
                }
                if (lab[i][j] == 'S') {
                    si = i;
                    sj = j;
                }
            }
        }

        boolean achou = buscar(lab, vis, ei, ej, si, sj);

        if (!achou) {
            System.out.println("Labirinto sem saída.");
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(lab[i][j]);
                    if (j < m - 1) System.out.print(" ");
                }
                System.out.println();
            }
        }
    }
}