import java.io.*;
import java.util.*;

public class tildes {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] t = br.readLine().split(" ");

        int n = Integer.parseInt(t[0]), q = Integer.parseInt(t[1]);

 class UnionFind {                                              // OOP style
  private ArrayList<Integer> p, rank, setSize;
  private int numSets;

  public UnionFind(int N) {
    p = new ArrayList<>(N);
    rank = new ArrayList<>(N);
    setSize = new ArrayList<>(N);
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
      return ret; } }

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

    UnionFind UF = new UnionFind(n); 
    for (int i = 0; i < q; i++) {
        String[] token = br.readLine().split(" ");
        if(token[0].equals("t")) {
            //pw.println("t");
            int a = Integer.parseInt(token[1]);
            int b = Integer.parseInt(token[2]);
            UF.unionSet(a-1, b-1);
        } else {
            //pw.println("print");
            int a = Integer.parseInt(token[1]);
            pw.println(UF.sizeOfSet(a-1));
        }
    }
        pw.close();
    }
}
