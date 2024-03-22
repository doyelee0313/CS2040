import java.io.*;
import java.util.*;

public class proof {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());

        HashSet<String> hash = new HashSet<>();

        int lineNum = 1;

        for(int i = 0; i < N; i++) {
            String[] token = br.readLine().split("-> ");
            pw.println(token[0]);

            String[] t = token[0].split(" ");
            boolean correct = true;
            pw.println(t[0]);
            for(int j = 0; j < t.length; j++) {
                pw.println("checking: " + t[j]);
                if(!hash.contains(t[j]) && (t[j].length() > 0)) {
                    correct = false;
                    pw.println("WTF");
                    break;
                }
            }
            if(!correct) {
                pw.println(lineNum);
                System.exit(0); //immediately stop the programme
            } else {
                hash.add(token[1]);
                lineNum++;
            }
        }

        pw.println("correct");
        pw.close();
    }
}
