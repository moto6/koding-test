package goormton;

import java.util.HashMap;
import java.util.Scanner;

public class GameJam {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] goormPos = {scanner.nextInt() - 1, scanner.nextInt() - 1};
        boolean[][] goormVisited = new boolean[N][N];
        int[] playerPos = {scanner.nextInt() - 1, scanner.nextInt() - 1};
        boolean[][] playerVisited = new boolean[N][N];

        String[][] board = new String[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = scanner.next();
            }
        }

        int goormScore = move(goormPos, goormVisited, 1, board, N);
        int playerScore = move(playerPos, playerVisited, 1, board, N);

        if (goormScore > playerScore) {
            System.out.println("goorm " + goormScore);
        } else if (goormScore < playerScore) {
            System.out.println("player " + playerScore);
        }
        scanner.close();
    }


    public static int set_Pos(int a, int N) {
        if (a == -1) {
            return N - 1;
        }
        if (a == N) {
            return 0;
        }
        return a;
    }

    static HashMap<String, int[]> directions = new HashMap<String, int[]>() {
        {
            put("U", new int[]{-1, 0});
            put("D", new int[]{1, 0});
            put("L", new int[]{0, -1});
            put("R", new int[]{0, 1});
        }
    };

    public static int move(int[] pos, boolean[][] visited, int score, String[][] board, int N) {
        int x = pos[0];
        int y = pos[1];
        visited[x][y] = true;
        boolean flag = true;

        while (flag) {
            String command = board[x][y];
            int distance = Integer.parseInt(command.substring(0, command.length() - 1));
            String direction = command.substring(command.length() - 1);

            for (int i = 0; i < distance; i++) {
                x += directions.get(direction)[0];
                y += directions.get(direction)[1];
                x = set_Pos(x, N);
                y = set_Pos(y, N);

                if (!visited[x][y]) {
                    visited[x][y] = true;
                    score += 1;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return score;
    }

    private record jPair(int first, int second) {

    }
}

