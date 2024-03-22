import java.util.*;
import java.io.*;

public class fendofftitanfin {
    public static final int INF = 1000000000;

    static class Village implements Comparable<Village> {
        int number;
        int titans;
        int shamans;
        int distance;

        Village(int number, int titans, int shamans, int distance) {
            this.number = number;
            this.titans = titans;
            this.shamans = shamans;
            this.distance = distance;
        }      
    

    public int compareTo(Village v) {
        if (!this.titans().equals(v.titans()))
        return this.titans() - v.titans();
        else if (!this.shamans().equals(v.shamans()))
        return this.shamans() - v.shamans();
        else
        return this.distance() - v.distance();
    }


    Integer number() { return number; }
    Integer titans() { return titans; }
    Integer shamans() { return shamans; }
    Integer distance() { return distance; }

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]), M = Integer.parseInt(token[1]), X = Integer.parseInt(token[2]) - 1, Y = Integer.parseInt(token[3]) - 1;

        ArrayList<ArrayList<Village>> AL = new ArrayList<>();
        for (int u = 0; u < N; ++u) {
            ArrayList<Village> Neighbor = new ArrayList<>();
            AL.add(Neighbor);
        }

        while (M-- > 0) {
            token = br.readLine().split(" ");
            int u = Integer.parseInt(token[0]) - 1, v = Integer.parseInt(token[1]) - 1, w = Integer.parseInt(token[2]), c = Integer.parseInt(token[3]);
            AL.get(u).add(new Village(v, c == 2 ? 1 : 0, c == 1 ? 1 : 0, w));
            AL.get(v).add(new Village(u, c == 2 ? 1 : 0, c == 1 ? 1 : 0, w));
        }

        ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(V, INF)); dist.set(s, 0); // INF = 1e9 here

        PriorityQueue<Village> pq = new PriorityQueue<>();

        pq.offer(new Village(X, 0, 0, 0));

        int titantoprint = 0;
        int shamantoprint = 0;
        int distancetoprint = 0;

        boolean work = false;

        while (!pq.isEmpty()) {                      // main loop
            Village top = pq.pollFirst();
            int u = top.first();                      // shortest unvisited u
            for (Village v_w : AL.get(u)) {        // all edges from u
              int v = v_w.first(), w = v_w.distance();
              if (dist.get(u)+w >= dist.get(v)) continue; // not improving, skip
              pq.remove(new Village(dist.get(v), Village.get(u).titans, Village.get(u).shamans, v)); // erase old pair
              dist.set(v, dist.get(u)+w);              // relax operation
              pq.add(new Village(dist.get(v), v)); // enqueue better pair
            }
        }
        
        if(work) {
            System.out.println(distancetoprint + " " + shamantoprint + " " + titantoprint);
        } else{
            System.out.println("IMPOSSIBLE");
        }
    }
}
