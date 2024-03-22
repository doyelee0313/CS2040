import java.io.*;
import java.util.*;

public class format {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");

        int N = Integer.parseInt(token[0]), M = Integer.parseInt(token[1]);

        ArrayList<ArrayList<Integer>> AL = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            ArrayList<Integer> Neighbor = new ArrayList<>();
            AL.add(Neighbor);
        }

        String[] t = br.readLine().split(" ");
        
        for(int j = 0; j < M ; j++) {

            int start = Integer.parseInt(token[0]), end = Integer.parseInt(token[1]);

            AL.get(start).add(end);
            AL.get(end).add(start); //bidirection
        }

        

        pw.close();
    }
}
