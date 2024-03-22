import java.io.*;
import java.util.*;

class UnionFind {                                              // OOP style
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;
  
    public UnionFind(int N) {
      p = new ArrayList<Integer>(N);
      rank = new ArrayList<Integer>(N);
      setSize = new ArrayList<Integer>(N);
      numSets = N;
      for (int i = 0; i < N; i++) {
        p.add(i);
        rank.add(0);
        setSize.add(1);
      }
    }
  
    public int findSet(int i) { 
      if (p.get(i) == i) return i;
      else {
        int ret = findSet(p.get(i)); p.set(i, ret);
        return ret;
      }
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
      if (!isSameSet(i, j)) { numSets--; 
      int x = findSet(i), y = findSet(j);
      // rank is used to keep the tree short
      if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
      else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                                       if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
    public int numDisjointSets() { return numSets; }
    public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}

public class treehouses {

    static class Pair {
        double first, second;

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }
    }

    static class IntegerTriple implements Comparable<IntegerTriple> {
        Double _first;
        Integer _second, _third;
        
        public IntegerTriple(Double f, Integer s, Integer t) {
            _first = f;
            _second = s;
            _third = t;
        }
        
        public int compareTo(IntegerTriple o) {
            int compareResult = Double.compare(this.first(), o.first());
            if (compareResult != 0) {
                return compareResult;
            }
            compareResult = Double.compare(this.second(), o.second());
            if (compareResult != 0) {
                return compareResult;
            }
            return Double.compare(this.third(), o.third());
        }

        public Double first() { return _first; }
        public Integer second() { return _second; }
        public Integer third() { return _third; }

        public String toString() { return first() + " " + second() + " " + third(); }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]), e = Integer.parseInt(token[1]), p = Integer.parseInt(token[2]);

        double mst_cost = 0;
        int num_taken = 0;             // no edge has been taken

        UnionFind UF = new UnionFind(n);             // all V are disjoint sets

        Pair[] v = new Pair[n];
        for (int i = 0; i < n; ++i) {
            String[] t = br.readLine().split(" ");
            double x = Double.parseDouble(t[0]), y = Double.parseDouble(t[1]);
            v[i] = new Pair(x, y);       
        }
/* 
        for (int i = 1; i < e; i++) {
            UF.unionSet(i, i + 1);
            System.err.println("This is union set: "+ i);
        }

        for (int i = 0; i < p; i++) {
            String[] tok = br.readLine().split(" ");
            int n1 = Integer.parseInt(tok[0]), n2 = Integer.parseInt(tok[1]);
            UF.unionSet(n1 - 1, n2 - 1);
            System.err.println("This is union set2: "+ n1 + " " + n2);
        }
*/

        int[] d = new int[n];
        Arrays.fill(d, -1);

        //first e edges connected
        for (int i = 1; i < e; i++) {
            d[i] = 0;
        }

        //edged that are connected
        for (int i = 0; i < p; i++) {
            String[] tok = br.readLine().split(" ");
            int n1 = Integer.parseInt(tok[0]), n2 = Integer.parseInt(tok[1]);
            join(d, n1 - 1, n2 - 1);
        }

        // reorder as (w, u, v)
        ArrayList<IntegerTriple> EL = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Pair p1 = v[i];
                Pair p2 = v[j];
                IntegerTriple triple = new IntegerTriple((Math.sqrt(Math.pow(p1.first - p2.first, 2) + Math.pow(p1.second - p2.second, 2))), i, j);
                EL.add(triple);
            }
        }

        Collections.sort(EL);
/*
        for (int i = 0; i < n; ++i) {                // up to O(E)
            IntegerTriple front = EL.get(i);
            if (UF.isSameSet(front.second(), front.third())) continue; // check
            mst_cost += front.first();                 // add w of this edge
            UF.unionSet(front.second(), front.third()); // link them
            ++num_taken;                               // 1 more edge is taken
            if (num_taken == n-1) break;               // optimization
        }
*/

    double total = 0;
    for (IntegerTriple edge : EL) {
        // if not joined, join
        if (find(d, edge.second()) != find(d, edge.third())) {
            join(d, edge.second(), edge.third());
            total += edge.first();
        }
    }

        System.out.printf("%.6f\n", total);
        
        pw.close();
    }

    static int find(int[] d, int a) {
        if (d[a] == -1) return a;
        return d[a] = find(d, d[a]);
    }

    static void join(int[] d, int a, int b) {
        a = find(d, a);
        b = find(d, b);
        if (a == b) return;
        d[a] = b;
    }
}