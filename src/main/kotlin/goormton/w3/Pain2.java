package goormton.w3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Pain2 {

    public static void main(String[] args) {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String input = br.

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i <= N; i++) {
            if (isBestWay(A, i, dp)) {
                dp[i] = Math.min(dp[i], dp[i - A] + 1);
            }
            if (isBestWay(B, i, dp)) {
                dp[i] = Math.min(dp[i], dp[i - B] + 1);
            }
        }
        System.out.println(dp[N] != Integer.MAX_VALUE ? dp[N] : -1);
    }

    private static boolean isBestWay(int diff, int i, int[] dp) {
        return ((i - diff >= 0) &&
            (dp[i - diff] != Integer.MAX_VALUE)
        );
    }
}
