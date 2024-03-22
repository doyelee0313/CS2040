import java.util.*;

public class kaploeb {

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
        int l = sc.nextInt(); // number of lap time recorded
        int k = sc.nextInt(); // number of lab
        int s = sc.nextInt(); // how many number was handed out
        
        Map<Integer, Pair<Integer,Integer>> hash = new HashMap<>();
        //Map<Integer, Integer> times = new HashMap<>();
        
        for (int i = 0; i < l; i++) {
            int p = sc.nextInt();
            String t = sc.next();
            String[] parts = t.split("\\.");
            int min = Integer.parseInt(parts[0]);
            int sec = Integer.parseInt(parts[1]);
            int time = min * 60 + sec;

            //updating the values in the hashmap
            if (hash.get(p) != null) {
                Pair<Integer, Integer> pair = hash.get(p);
                //System.out.println("original: " + pair.getFirst() + " " + pair.getSecond());
                pair.setFirst(pair.getFirst() + 1);
                pair.setSecond(pair.getSecond() + time);
                //System.out.println("changed: " + pair.getFirst() + " " + pair.getSecond());
                hash.put(p, pair);
            } else {
                Pair<Integer, Integer> pair = new Pair<>(1 , time);
                hash.put(p, pair);
            }
        }

        Iterator<Map.Entry<Integer, Pair<Integer, Integer>>> iterator = hash.entrySet().iterator();
            while (iterator.hasNext()) {
            Map.Entry<Integer, Pair<Integer, Integer>> entry = iterator.next();
            Pair<Integer, Integer> pair = entry.getValue();
            int number = pair.getFirst();
            int time = pair.getSecond();
            //System.out.println("Key: " + entry.getKey() + ", Value: " + time);
            if (number < k) {
                //System.out.println("Key: " + entry.getKey() + ", Value: " + time + " is removed");
                iterator.remove();
            }
        }

        List<Map.Entry<Integer, Pair<Integer, Integer>>> entryList = new ArrayList<>(hash.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Pair<Integer, Integer>>>() {
            public int compare(Map.Entry<Integer, Pair<Integer, Integer>> o1, Map.Entry<Integer, Pair<Integer, Integer>> o2) {

                int compare1 = o1.getValue().getSecond().compareTo(o2.getValue().getSecond());

                int compare2 = o1.getKey().compareTo(o2.getKey());

                return (compare1 == 0) ? compare2 : compare1;
            }
        });

        for (Map.Entry<Integer, Pair<Integer, Integer>> entry : entryList) {
            //System.out.println("Result: Key " + entry.getKey() + ", Value: " + entry.getValue().getSecond());
            System.out.println(entry.getKey());
        }
  
    }
}
