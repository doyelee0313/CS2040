// Kattis's Quest
// simulation of greedy algorithm (as outlined in the problem description)
// almost complete solution: using TreeMap of <Energy Level to Gold>, will WA if there are two quests with the same energy level
import java.io.*;
import java.util.*;

import javax.swing.tree.TreeCellRenderer;

class queueComparator implements Comparator<Integer> {
    public int compare(Integer int1, Integer int2) {
        return int2 - int1; 
    }
}

class kattissquest {
    public static void main(String[] args) throws Exception {
/*
        PriorityQueue<Integer> descendingPriorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; // Reverse the comparison order for descending order
            }
        });
*/

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int N = Integer.parseInt(br.readLine());

    TreeMap<Integer, PriorityQueue<Integer>> pool = new TreeMap<>(); // praying for no two quests have same energy level E

    while (N-- > 0) {

    String[] token = br.readLine().split(" ");

        if (token[0].equals("add")) {
            int E = Integer.parseInt(token[1]), G = Integer.parseInt(token[2]);
            if (!pool.containsKey(E)) {
                PriorityQueue<Integer> PQ = new PriorityQueue<>(new queueComparator());
                PQ.add(G);
                pool.put(E, PQ);
                //pw.println("adding1: " + G);
            } else {
                pool.get(E).add(G);
                //pw.println("adding2: " + G);
            }
        } else { // if (token[0] == "get")
            int X = Integer.parseInt(token[1]);
            long ans = 0;
            while (X > 0) {
                Map.Entry<Integer, PriorityQueue<Integer>> pos = pool.lowerEntry(X+1); // find largestenergy quest from current pool of quest
                if (pos == null) {
                    //pw.println("break");
                    break;
                }
                int energy = pos.getKey();
                int gold = pos.getValue().poll();
                X -= energy;
                ans += gold;
                if(pos.getValue().poll() == null) {
                    pool.remove(pos.getKey());
                }
            }
            pw.println(ans);
        }
    }
pw.close(); 
    }
}
