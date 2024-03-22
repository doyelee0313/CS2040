import java.util.*;

public class traveltheskies {

    public static class Pair<A, B> {
            private A first;
            private B second;
        
            public Pair(A first, B second) {
                this.first = first;
                this.second = second;
            }
        
            public A getFirst() {
                return first;
            }
        
            public B getSecond() {
                return second;
            }
        
            public void setFirst(A first) {
                this.first = first;
            }
        
            public void setSecond(B second) {
                this.second = second;
            }
        }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int k, n, m;
        k = sc.nextInt();
        n = sc.nextInt();
        m = sc.nextInt();

        // hashmap of the flight schedule
        Map<Integer, List<Pair<Integer,Pair<Integer, Integer>>>> flights = new HashMap<>();

        // hashmap of the moving schedule of the people
        Map<Integer, List<Pair<Integer, Integer>>> moving = new HashMap<>();

        // inputting the flight schedule
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int day = sc.nextInt();
            int cap = sc.nextInt();
            if (!flights.containsKey(day)) {
                flights.put(day, new ArrayList<>());
            }
            flights.get(day).add(new Pair<>(cap, new Pair<>(start, end)));
        }

        //printing out the value
/*
        for (Map.Entry<Integer, List<Pair<Integer,Pair<Integer, Integer>>>> entry : flights.entrySet()) {
            int key = entry.getKey();
            //System.out.println("Key: " + key);

            List<Pair<Integer,Pair<Integer, Integer>>> valueList = entry.getValue();

            for (Pair<Integer,Pair<Integer, Integer>> pair : valueList) {
                int start = pair.getFirst();
                int end = pair.getSecond().getFirst();
                int cap = pair.getSecond().getSecond();

                //System.out.println("Pair: (" + start + ", " + end + ", " + cap + ")");
            }
        }
*/

        // inputting the moving schedule of the people
        for (int i = 0; i < k * n; i++) {
            int arrive = sc.nextInt();
            int day = sc.nextInt();
            int customer = sc.nextInt();
            if (!moving.containsKey(day)) {
                moving.put(day, new ArrayList<>());
            }
            moving.get(day).add(new Pair<>(arrive, customer));
        }
        //printing out the value
/*
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : moving.entrySet()) {
            Integer key = entry.getKey();
            //System.out.println("Key: " + key);

            List<Pair<Integer, Integer>> valueList = entry.getValue();

            for (Pair<Integer, Integer> pair : valueList) {
                int arrive = pair.getFirst();
                int customer = pair.getSecond();

                //System.out.println("Pair: (" + arrive + ", " + customer + ")");
            }
        }
*/
        // people in each airport
        int[] airport = new int[k];

        boolean b = true; 

        // Simulate each day
        loop1: for (int i = 1; i < n + 1; i++) {

            // arriving
            if (moving.containsKey(i)) {
                for (Pair<Integer, Integer> pair : moving.get(i)) {
                    airport[pair.getFirst() - 1] = airport[pair.getFirst() - 1] + pair.getSecond();
                    //System.out.println("after arriving: " + airport[pair.getFirst() - 1]);
                }
            }

            // moving out + checking capacity
            if (flights.containsKey(i)) {
                for (Pair<Integer, Pair<Integer, Integer>> pair : flights.get(i)) {
                    airport[pair.getSecond().getFirst() - 1] -= pair.getFirst();
                    if (airport[pair.getSecond().getFirst() - 1] < 0) {
                        b = false;
                        break loop1;
                    }
                    //System.out.println("after moving: " + airport[pair.getSecond().getSecond() - 1]);
                }
            }

            // adding in new people
            if (flights.containsKey(i)) {
                for (Pair<Integer, Pair<Integer, Integer>> pair : flights.get(i)) {
                    airport[pair.getSecond().getSecond() - 1] = airport[pair.getSecond().getSecond() - 1] + pair.getFirst();
                    //System.out.println("after moving: " + airport[pair.getSecond().getSecond() - 1]);
                }
            }

            //System.out.println("finished: " + i);
        }

        if (b) {
            System.out.println("optimal");
        } else { 
            System.out.println("suboptimal");
        }
    }
}
