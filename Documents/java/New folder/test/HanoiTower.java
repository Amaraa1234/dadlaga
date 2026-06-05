public class HanoiTower {
    public static void solveHanoi(int n, char from, char to, char aux) {
        if (n == 1) {
            System.out.println("Disk 1: " + from + " -> " + to);
            return;
        }
        solveHanoi(n - 1, from, aux, to);
        System.out.println("Disk " + n + ": " + from + " -> " + to);
        solveHanoi(n - 1, aux, to, from);
    }

    public static void main(String[] args) {
        int n = 3;
        solveHanoi(n, 'A', 'C', 'B');
    }
}