import java.io.*;
import java.util.*;
class coursescheduling {

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
        
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine());

        //name, module, frequency
        Map<String, Pair<String, Integer>> hash = new HashMap<>();
//course를 먼먼저  넣넣어어야야함
        while (N-- > 0) {
            String[] token = br.readLine().split(" ");
            String firstName = token[0];
            String secondName = token[1];
            String module = token[2];
            String fullname = (token[0] + token[1]);

            System.out.println(firstName + secondName + module + fullname);

            if (hash.get(fullname) != null && (hash.get(fullname).getSecond() == null)) {
                Pair<String, Integer> pair = hash.get(fullname);
                pair.setSecond(pair.getSecond() + 1);
                hash.put(fullname, pair);
            } else if (hash.get(fullname) == null) {
                Pair<String, Integer> pair = new Pair<>(module, 1);
                hash.put(fullname, pair);
            } else ((hash.get(fullname) != null) && (hash.get(fullname).getSecond() != null)) {
                
            }
        }

        List<Map.Entry<String, Pair<String, Integer>>> entryList = new ArrayList<>(hash.entrySet());

        for (Map.Entry<String, Pair<String, Integer>> entry : entryList) {
            //System.out.println("Result: Key " + entry.getKey() + ", Value: " + entry.getValue().getSecond());
            System.out.println(entry.getValue().getFirst() + " " + entry.getValue().getSecond());
        }

        pw.println();
        pw.close();
    }

}



