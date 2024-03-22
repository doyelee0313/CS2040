import java.io.*;
import java.util.*;

public class teque {
    public static void main(String[] arg) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            String s = input[0];
            int m = Integer.parseInt(input[1]);

            if (s.equals("push_back")) {
                list.add(list.size(), m);
            } else if (s.equals("push_front")) {
                list.add(0, m);
            } else if (s.equals("push_middle")) {
                list.add((list.size()+1)/2, m);
            } else {
                pw.println(list.get(m)); 
            }
        }

        pw.close();
    }
}