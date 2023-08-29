package summer23;

import java.util.List;

public class Sy2 {

    public static void main(String[] args) {
        Sy2 sy2 = new Sy2();
        sy2.convertToMap(new String[]{"...X..", "....XX", "..X..."});
    }

    public int solution(String[] R) {
        int[][] vistied = new int[R[0].length()][R.length];
        final int LOOP_THREASHOLD = 7;
        List<Diff> diffs = List.of(
            new Diff(0, 1),
            new Diff(1, 0),
            new Diff(0, -1),
            new Diff(-1, 0)
        );
        char[][] map = convertToMap(R);

//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//
//        }
        return -1;

    }

    private char[][] convertToMap(String[] r) {
        char[][] map = new char[r[0].length()][r.length];
        for (int i = 0; i < r.length; i++) {
            char[] line = r[i].toCharArray();
            System.arraycopy(line, 0, map[i], 0, line.length);
        }
        return map;
    }

    private class Diff {

        final int first;
        final int secode;

        public Diff(int first, int secode) {
            this.first = first;
            this.secode = secode;
        }
    }
}
