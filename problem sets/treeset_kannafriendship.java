import java.io.*;
import java.util.*;

public class kannafriendship {

    public static class IntPair {
        private int first;
        private int second;

        public IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n, q;
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        q = Integer.parseInt(input[1]);
        int count = 0;

        TreeSet<IntPair> pairSet = new TreeSet<>(new Comparator<IntPair>() {
            public int compare(IntPair o1, IntPair o2) {
                int compare1 = Integer.compare(o1.getFirst(), o2.getFirst());
                //return compare1;
                int compare2 = Integer.compare(o1.getSecond(), o2.getSecond());
                return (compare1 == 0) ? compare2 : compare1;
            }
        });

        for(int i = 0; i < q; i++) {

            String[] query = br.readLine().split(" ");
            int quest = Integer.parseInt(query[0]);

            if (quest == 1) {
                int start = Integer.parseInt(query[1]);
                int end = Integer.parseInt(query[2]);
                IntPair newPair = new IntPair(start, end);

                if (pairSet.isEmpty()) {
                    pairSet.add(newPair);
                }

                Iterator<IntPair> iterator = pairSet.iterator(); 
                while (iterator.hasNext()) {

                    IntPair pair = iterator.next();

                    if (pair.second < newPair.first) {
                        // No overlap, move to the next pair
                        continue;
                    }
        
                    if (pair.first > newPair.second) {
                        // No more overlaps, insert the newPair here
                        pairSet.add(newPair);
                        break;
                    }
        
                    // Merge overlapping pairs
                    newPair.first = Math.min(newPair.first, pair.first);
                    newPair.second = Math.max(newPair.second, pair.second);
        
                    // Remove the old pair
                    iterator.remove();
                }

                if (!pairSet.contains(newPair)) {
                    pairSet.add(newPair);
                }

                for (IntPair pair : pairSet) {
                    System.out.println("(" + pair.getFirst() + ", " + pair.getSecond() + ")");
                    count += pair.getSecond() - pair.getFirst() + 1;
                    
                }

            } else {
                pw.println(count); 
                count = 0;
            }
        }
        pw.close();
    }
}   
