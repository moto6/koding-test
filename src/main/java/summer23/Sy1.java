package summer23;

public class Sy1 {

    public static void main(String[] args) {
        Sy1 s = new Sy1();
        System.out.println(s.solution(5, 2));
        System.out.println(s.solution(8, 3));
        System.out.println(s.solution(3, 2));
        System.out.println(s.solution(5, 26));
    }

    public String solution(int N, int K) {
        // Implement your solution here
        String half = makeHalf(N/2,K);
        StringBuffer buffer = new StringBuffer(half);
        if(N%2==0) {
            return half.concat(buffer.reverse().toString());
        }
        else if (isEdgeCase(N, K)) {
            return half.concat(Character.toString((char) (97 + (K - 1))))
                .concat(buffer.reverse().toString());
        }
        return half.concat("a").concat(buffer.reverse().toString());
    }

    private static boolean isEdgeCase(int N, int K) {
        return (N / 2) < K;
    }

    private String makeHalf(int lenght, int numberOfCase) {
        StringBuilder builder = new StringBuilder();
        for(int i=0 ;i<lenght ; i++) {
            char nextChar = (char) (97 + (i % numberOfCase));
            builder.append(nextChar);
        }
        return builder.toString();
    }
}
