import java.util.*;
import java.io.*;

public class swaptosort {
    public static void main(String[] args) throws IOException {

        class UnionFind {
        private ArrayList<Integer> p;
      
        public UnionFind(int N) {
          p = new ArrayList<>(N);
          for (int i = 0; i < N; i++) {
            p.add(i);
          }
        }

        public int findSet(int i) { 
            if (p.get(i) == i) return i;
            else {
                int ret = findSet(p.get(i)); 
                p.set(i, ret);
                return ret; 
            } 
        }
    
        public void unionSet(int i, int j) { 
            if (findSet(i) != findSet(j)) { 
                int x = findSet(i), y = findSet(j);
                p.set(x, y);
            }
        }

        public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
    }

        // Scanner sc = new Scanner(System.in);
        // int N = sc.nextInt();
        // int K = sc.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        UnionFind UF = new UnionFind(N); 

        // //initializing the array
        // for (int i = 0; i < N; i++) {
        //     array[i] = i;
        // }

        for (int i = 0; i < K; i++) {
            // int a = sc.nextInt() - 1;
            // int b = sc.nextInt() - 1;
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            //System.out.println(a + " " + b);
            UF.unionSet(a, b);
        }

        //boolean to varify the result
        boolean pass = true;

        for (int i = 0; i < (N/2 + 1); i++) {
            if (UF.findSet(i) != UF.findSet((N - i) - 1)) {
                pass = false;
                break;
            }
        }

        if (pass == true) 
            System.out.println("Yes");
        else 
            System.out.println("No");
    }
}