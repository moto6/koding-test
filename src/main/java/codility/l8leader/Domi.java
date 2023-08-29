package codility.l8leader;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Domi {

    public static void main(String[] args) {
        Domi domi = new Domi();
        int solution = domi.solution(new int[]{3, 4, 3, 2, 3, -1, 3, 3});
        //int solution = domi.solution(new int[]{3,4,3,2,3,-1,3,3});
        System.out.println(solution);

    }

    public int solution(int[] A) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int key : A) {
            counts.put(key, counts.getOrDefault(key, 0) + 1);
        }

        Map<Integer, Integer> swapMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            swapMap.put(entry.getValue(), entry.getKey());
        }
        int mostCount = swapMap.keySet().stream().max(Comparator.naturalOrder())
            .orElseGet(() -> 0);
        if ((mostCount * 2) > A.length) {
            return getFirstIndexOf(swapMap.get(mostCount),A);
        }
        return -1;
    }

    private int getFirstIndexOf(Integer value,int[] A) {
        for(int i=0 ; i<A.length ; i++) {
            if(A[i] == value) {
                return i;
            }
        }
        throw new RuntimeException();
    }

}
