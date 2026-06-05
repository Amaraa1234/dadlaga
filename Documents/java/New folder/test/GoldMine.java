public class GoldMine {
    public static int getMaxGold(int[][] gold) {
        int n = gold.length;
        int m = gold[0].length;
        int[][] dp = new int[n][m];

        for (int j = m - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                int right = (j == m - 1) ? 0 : dp[i][j + 1];
                int rightUp = (i == 0 || j == m - 1) ? 0 : dp[i - 1][j + 1];
                int rightDown = (i == n - 1 || j == m - 1) ? 0 : dp[i + 1][j + 1];

                dp[i][j] = gold[i][j] + Math.max(right, Math.max(rightUp, rightDown));
            }
        }

        int res = dp[0][0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    // Энэ хэсгийг заавал нэмж байж код ажиллана:
    public static void main(String[] args) {
        int[][] gold = {
                { 1, 3, 1, 5 },
                { 2, 2, 4, 1 },
                { 5, 0, 2, 3 },
                { 0, 6, 1, 2 }
        };
        System.out.println("Max Gold: " + getMaxGold(gold));
    }
}