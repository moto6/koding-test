package summer23;

import java.util.Arrays;

public class CsSol2 {

    public static void main(String[] args) {
        CsSol2 s = new CsSol2();
        //tc1
        int solution = s.solution(new int[]{10, 40, 30, 20}, 20);
        System.out.println("answer = 1");
        System.out.printf("your = %d \n",solution);


        //tc2
        int solution2 = s.solution(new int[]{3,7,2,8,6,4,5,1}, 3);
        System.out.println("answer = 2");
        System.out.printf("your = %d \n",solution2);

    }

    public int solution(int[] numbers, int k) {
        final int N = numbers.length;
        int swapCount = 0;

        ValuePair[] valuePairs = new ValuePair[N];
        IndexPair[] indexPairs = new IndexPair[N];

        for (int i = 0; i < N; i++) {
            valuePairs[i] = new ValuePair(numbers[i], i);
        }
        Arrays.sort(valuePairs);
        for(int i=0 ; i<N ; i++ ) {
            indexPairs[i] = new IndexPair(numbers[i], i);
        }
        Arrays.sort(indexPairs);



        for (int i = 0; i < N ; i++) {
            if(isSatisfactionTermCondition(numbers,k)) {
                break;
            }
            IndexPair i2 = indexPairs[N - (i + 1)];

            swapCount++;
        }
        return swapCount;
    }

    private boolean isSatisfactionTermCondition(int[] numbers, int k) {
        return true;
    }

    static class ValuePair implements Comparable<ValuePair> {
        int value;
        int index;

        public ValuePair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(ValuePair other) {
            return Integer.compare(this.value, other.value);
        }
    }
    static class IndexPair implements Comparable<IndexPair> {
        int value;
        int index;

        public IndexPair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(IndexPair other) {
            return Integer.compare(this.index, other.index);
        }
    }
}
