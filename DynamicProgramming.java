package dpalgorithms;

public class DynamicProgramming {
    //This is using normal recursion which can lead to high space usage
    public static int fib(int n) {
        if (n <= 2) return 1;
        else
            return fib(n - 1) * n;

    }

    //this was the basic naive approach to solve this problem using dc, this takes a lot of time to process for Big numbers because we have alot of repetitions involved in it.
    public static int rodCut(int n, int[] price) {
        if (n <= 0) return 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int p = price[i] + rodCut(n - i, price);
            if (p > max)
                max = p;
        }
        return max;
    }

    //We can solve this problem using dynamic programming where we save the values so that we don't have to go through repeating computation.
    //this was using memoization
    public static int rodCut(int n, int[] price, int[] arr) {
        if (arr[n] != 0) return arr[n];
        else {
            if (n <= 0) return 0;
            for (int i = 1; i <= n; i++) {
                int p = price[i] + rodCut(n - i, price, arr);
                if (p > arr[n])
                    arr[n] = p;
            }
            return arr[n];
        }
    }

    //Next step is using bottom - to - up
    public static int rodCutDp(int n, int[] price) {
        int[] x = new int[price.length];
        int[] r = new int[price.length];
        x[0] = 0;
        r[0] = 0;
        for (int i = 1; i <= n; i++) {
            int index = 0;
            int max = 0;
            for (int j = 1; j <= i; j++) {
                int t = price[j] + r[i - j];
                if (t > max) {
                    max = t;
                    index = j;
                }
            }
            x[i] = index;
            r[i] = max;
        }
        print(n, x);
        return r[n];
    }

    public static void print(int n, int[] x) {
        while (n > 0) {
            System.out.println(x[n]);
            n = n - x[n];
        }


    }

    public static int exchangeDp(int n, int[] d, int[] t) {
        int[] s = new int[n];
        s[0] = 0;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 1; j < t.length; j++) {
                if (i > d[j]) {
                    int p = t[j] + s[i - d[j]];
                    if (p > max)
                        max = p;
                }
            }
            s[i] = max;
        }
        return s[n];
    }


    public static int fib(int[] arr, int n) {
        int result = 0;
        if (arr[n] != 0) {
            return arr[n];
        }
        if (n <= 2) {
            result = 1;
        } else
            arr[n] = fib(n - 1) + fib(n - 2);
        result = arr[n];
        return result;

    }


    public static void main(String[] args) {
        int[] prices = {0, 100, 10, 120};
        int[] weights = {0, 20, 10, 30};
        int W = 50;

        System.out.println(knapsack01_dp(prices, weights, 40));

    }


    public static int knapsack_dp(int[] prices, int[] weights, int W) {
        int[] M = new int[W + 1];
        M[0] = 0;

        for (int i = 0; i <= W; i++) {
            int max = 0;
            for (int j = 0; j < prices.length; j++) {
                if (weights[j] <= i) {
                    int p = prices[j] + M[i - weights[j]];
                    if (p > max)
                        max = p;
                }
            }
            M[i] = max;
        }

        return M[W];

    }


    private static int[][] M;
    private static Boolean[][] X; //this array is used for storing indices

    public static int knapsack01_dp(int[] prices, int[] weights, int W) {
        //base cases
        M = new int[prices.length][W + 1];
        X = new Boolean[prices.length][W + 1];
        for (int i = 0; i <= W; i++) {
            M[0][i] = 0;
        }
        for (int j = 0; j < prices.length; j++) {
            M[j][0] = 0;
        }

        for (int i = 1; i < prices.length; i++) {

            for (int j = 1; j <= W; j++) {
                M[i][j] = M[i - 1][j];
                X[i][j] = false;
                if (weights[i] <= j) {
                    int p = prices[i] + M[i - 1][j - weights[i]];
                    if (M[i][j] < p) {
                        X[i][j] = true;
                        M[i][j] = p;
                    }
                }
            }

        }

        return M[prices.length - 1][W];

    }


    public static String printPath(int n, int W, int weight[]) {
        if (n <= 0 || W <= 0)
            return "";
        if (X[n][W]) {
            W = W - weight[n];
            return n + ":" + printPath(n - 1, W, weight);
        } else return printPath(n - 1,
                W, weight);
    }


    public static int knapsack01_dc(int[] prices, int[] weights, int W, int N) {
        int q = 0;
        if (N >= 0) {
            int a = knapsack01_dc(prices, weights, W, N - 1);
            if (W >= weights[N]) {
                int p = prices[N] + knapsack01_dc(prices, weights, W - weights[N], N - 1);
                if (a < p)
                    a = p;
            }
            q = a;
        }
        return q;
    }


    public static int knapsack_dc(int[] prices, int[] weights, int W) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (weights[i] <= W) {
                int p = prices[i] + knapsack_dc(prices, weights, W - weights[i]);
                if (p > max)
                    max = p;
            }
        }
        return max;
    }

}
