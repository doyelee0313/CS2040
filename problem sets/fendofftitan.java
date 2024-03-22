import java.io.*;
import java.util.*;

class IntegerQuad implements Comparable<IntegerQuad> {
    Integer _first, _second, _third, _fourth;

    public IntegerQuad(Integer f, Integer s, Integer t, Integer fourth) {
        _first = f;
        _second = s;
        _third = t;
        _fourth = fourth;
    }

    public int compareTo(IntegerQuad o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else if (!this.second().equals(o.second()))
            return this.second() - o.second();
        else if (!this.third().equals(o.third()))
            return this.third() - o.third();
        else
            return this.fourth() - o.fourth();
    }

    Integer first() { return _first; }
    Integer second() { return _second; }
    Integer third() { return _third; }
    Integer fourth() { return _fourth; }

    public String toString() {
        return first() + " " + second() + " " + third() + " " + fourth();
    }
}

class IntegerTriple implements Comparable<IntegerTriple> {
    Integer _first, _second, _third;
    
    public IntegerTriple(Integer f, Integer s, Integer t) {
        _first = f;
        _second = s;
        _third = t;
        }
    
        public int compareTo(IntegerTriple o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else if (!this.second().equals(o.second()))
            return this.second() - o.second();
        else
            return this.third() - o.third();
        }
    
        Integer first() { return _first; }
        Integer second() { return _second; }
        Integer third() { return _third; }
    
        public String toString() { return first() + " " + second() + " " + third(); }
}

class IntegerPair implements Comparable<IntegerPair> {
    Integer _first, _second;
    
    public IntegerPair(Integer f, Integer s) {
        _first = f;
        _second = s;
    }
    
    public int compareTo(IntegerPair o) {
        if (!this.first().equals(o.first()))
        return this.first() - o.first();
        else
        return this.second() - o.second();
    }
    
    Integer first() { return _first; }
    Integer second() { return _second; }
}  

public class fendofftitan {
    public static final int INF = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]), M = Integer.parseInt(token[1]), X = Integer.parseInt(token[2]) - 1, Y = Integer.parseInt(token[3]) - 1;

        ArrayList<ArrayList<IntegerTriple>> AL = new ArrayList<>();
        for (int u = 0; u < N; u++) {
            ArrayList<IntegerTriple> neighbors = new ArrayList<>();
            AL.add(neighbors);
        }
        while (M-- > 0) {
            token = br.readLine().split(" ");
            int u = Integer.parseInt(token[0]) - 1, v = Integer.parseInt(token[1]) - 1, w = Integer.parseInt(token[2]), c = Integer.parseInt(token[3]);
            AL.get(u).add(new IntegerTriple(v, w, c));
            AL.get(v).add(new IntegerTriple(u, w, c));
        }
        
        //t,s,d
        ArrayList<IntegerTriple> dist = new ArrayList<>(Collections.nCopies(N, new IntegerTriple(INF, INF, INF))); 
        dist.set(X, new IntegerTriple(0, 0, 0)); // INF = 1e9 here

        //t,s,d,u
        PriorityQueue<IntegerQuad> pq = new PriorityQueue<IntegerQuad>();
        pq.offer(new IntegerQuad(0,0, 0, X)); 

        // sort the pairs by non-decreasing distance from s.
        while (!pq.isEmpty()) {                      // main loop
        IntegerQuad top = pq.poll();
        int t = top.first(), s = top.second(), d = top.third(), u = top.fourth();     // shortest unvisited u
        if (d > dist.get(u).third()) continue;             // a very important check
        for (IntegerTriple v_w : AL.get(u)) {        // all edges from u
            int v = v_w.first(), w = v_w.second();
            if (dist.get(u).third()+w >= dist.get(v).third()) continue; // not improving, skip
            dist.set(v, new IntegerTriple(dist.get(u).first() + t, dist.get(u).second() + s, dist.get(u).third()+w));              // relax operation
            //System.out.println("relaxed new dist: " + v + " " + dist.get(u)+w);
            pq.offer(new IntegerQuad(dist.get(u).first() + t, dist.get(u).second() + s, dist.get(u).third()+w, v)); // enqueue better pair
            //System.out.println("new pair: " + dist.get(v) + " " + v);
        }
        }
    
        for (int u = 0; u < N; ++u)
        System.out.printf("SSSP(%d, %d) = %d %d %d\n", X, u, dist.get(u).first(), dist.get(u).second(), dist.get(u).third());
        pw.close();
    }
}