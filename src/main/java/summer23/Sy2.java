package summer23;

import java.util.List;

public class Sy2 {

    Integer direction = 0;
    List<MyPair> diffs = List.of(
        new MyPair(0, 1),
        new MyPair(1, 0),
        new MyPair(0, -1),
        new MyPair(-1, 0)
    );
    final int LOOP_THRESHOLD = 10;

    public static void main(String[] args) {
        Sy2 sy2 = new Sy2();
        System.out.println(sy2.solution(new String[]{"...X..", "....XX", "..X..."}));
        System.out.println(sy2.solution(new String[]{"....X..", "X......", ".....X.", "......."}));
        //15 나와야함
    }

    public int solution(String[] R) {
        int[][] visited = new int[R.length][R[0].length()];
        char[][] map = convertToMap(R);

        MyPair current = new MyPair(0, 0);
        int firstLimit = map.length;
        int secondLimit = map[0].length;

        for (int firstIndex = 0; firstIndex < firstLimit; firstIndex++) {
            for (int secondIndex = 0; secondIndex < secondLimit; secondIndex++) {
                visited[firstIndex][secondIndex] += 1;
            }
        }

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            visited[current.first][current.second]++;
            if (visited[current.first][current.second] > LOOP_THRESHOLD + 1) {
                break;
            }
            current = availableNextPos(current, map);
        }
        return cleanedAreaSize(visited);
    }

    private int cleanedAreaSize(int[][] visited) {
        int count = 0;
        for (int[] line : visited) {
            for (int elem : line) {
                if (elem != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private MyPair availableNextPos(MyPair currentPos, char[][] matrix) {

        int firstLimit = matrix.length;
        int secondLimit = matrix[0].length;
        int nextFirst = diffs.get(direction % 4).first + currentPos.first;
        int nextSecond = diffs.get(direction % 4).second + currentPos.second;

        if (rangeAvailable(firstLimit, secondLimit, nextFirst, nextSecond) &&
            movable(nextFirst, nextSecond, matrix)
        ) {
            return new MyPair(nextFirst, nextSecond);
        }

        direction++;
        return currentPos;
    }

    private MyPair availableNextPosV0(MyPair currentPos, char[][] matrix) {

        int firstLimit = matrix.length;
        int secondLimit = matrix[0].length;
        int nextFirst = diffs.get(direction % 4).first + currentPos.first;
        int nextSecond = diffs.get(direction % 4).second + currentPos.second;

        if (((firstLimit > nextFirst) &&
            (secondLimit > nextSecond) &&
            (0 <= nextFirst) &&
            (0 <= nextSecond)) &&
            (matrix[nextFirst][nextSecond] == '.')
        ) {
            return new MyPair(nextFirst, nextSecond);
        }

        direction++;
        return currentPos;
    }

    private boolean movable(int nextFirst, int nextSecond, char[][] matrix) {
        return matrix[nextFirst][nextSecond] == '.';
    }

    private static boolean rangeAvailable(
        int limitFirst, int limitSecond,
        int nextFirst, int nextSecond
    ) {
        return ((limitFirst > nextFirst) &&
            (limitSecond > nextSecond) &&
            (0 <= nextFirst) &&
            (0 <= nextSecond)
        );
    }

    private char[][] convertToMap(String[] r) {
        char[][] map = new char[r.length][r[0].length()];
        for (int i = 0; i < r.length; i++) {
            char[] line = r[i].toCharArray();
            //System.arraycopy(line, 0, map[i], 0, line.length);
            //Arrays.cop
            for (int j = 0; j < line.length; j++) {
                map[i][j] = line[j];
            }
        }
        return map;
    }

    private static class MyPair {

        final int first;
        final int second;

        public MyPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
