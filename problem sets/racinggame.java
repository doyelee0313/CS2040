// 6% VA OQ will be easier than this

import java.io.*;
import java.util.*;

public class racinggame {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());

        //Queue<Integer> queue = new Queue<Integer>();

        List<Pair<Integer, List>> list = new ArrayList();

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

        for(int i = 0 ; i < N; i++) {
            String[] token = br.readLine().split(" ");

            int quest = Integer.parseInt(token[0]), num = Integer.parseInt(token[1]);

            if(quest == 1) {

            List<Integer> sumList = new ArrayList();

            list.add(num, sumList);

                // list.add(num);
                // Collections.sort(list); 
            } else if (quest == 2) {

                hash.putall((a,b) -> b.add(num));

                // queue.add(num);
                // count ++;

                // count += num;
                // cal = true;
                // for(int j = 0; j < list.size() ; j++) {
                //     list.set(j , list.get(j) + num);
                // }

            } else {
                // int result = list.get(num - 1);
                // if(cal) {
                //     result += count;
                //     cal = false;
                //     //count = 0;
                // }
                // int s = 0;
                // for(int i = 0 ; i < count; i++) {
                //     s += queue.poll();
                // }

                Iterator<Map<Integer, List>> iterator = hash.iterator(); 
                while (iterator.hasNext()) {

                    Map<Integer, List> set = iterator.next();

                    

                    while(List.isEmpty()) {
                        List.get
                    }
                }
                Collections.sort(list); 
                pw.println(result);
            }
        }



        pw.close();
    }
}
