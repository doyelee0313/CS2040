import java.util.*;

public class bokforing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        
        Map<Integer, Integer> hash = new HashMap<>(N);
        int defaultValue = 0;

        for(int i = 0; i < Q; i++) {
            String string = sc.next();

            if (string.equals("SET")) {
                int iset = sc.nextInt() - 1;
                int x = sc.nextInt();
                hash.put(iset, x);
                //System.out.println("SET: " + hash.getOrDefault(i, 0));
            } else if (string.equals("PRINT")) {
                int iprint = sc.nextInt() - 1;
                System.out.println(hash.getOrDefault(iprint, defaultValue));
            } else if (string.equals("RESTART")) {
                defaultValue = sc.nextInt();    
                hash.clear();
                // for(int j = 0; j < N; j++) {
                //     hash.put(j, x);
                // }
            }
        }
    }
}
