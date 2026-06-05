
import java.io.PrintStream;

public class lab3 {

    static int N = 4;

    public static boolean isSafe(int[][] board, int row, int col) {

        for (int i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    public static boolean solveNQUtil(int[][] board, int col) {

        if (col >= N)
            return true;

        for (int i = 0; i < N; i++) {

            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQUtil(board, col + 1))
                    return true;

                board[i][col] = 0; // backtrack
            }
        }

        return false;
    }

    public static void solveNQ() {

        int[][] board = new int[N][N];

        if (!solveNQUtil(board, 0)) {
            PrintStream out = System.out;
            out.println("Шийдэл байхгүй");
            return;
        }

        printSolution(board);
    }

    public static void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            PrintStream out = System.out;
            for (int j = 0; j < N; j++)
                out.print(board[i][j] == 1 ? "Q" : ". ");
            out.println();
        }
    }

    public static void main(String[] args) {
        solveNQ();
    }
}