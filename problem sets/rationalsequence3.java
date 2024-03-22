import java.io.*;
import java.util.*;

public class rationalsequence3 {

    private static int p(int i) { return i>>1; } // i/2
    private static int l(int i) { return i<<1; } // i*2
    private static int r(int i) { return (i<<1)+1; } // i*2+1
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {

            Stack<Character> s = new Stack<>();

            String[] token = br.readLine().split(" ");
            int a = Integer.parseInt(token[0]);
            int b = Integer.parseInt(token[1]);

            while(b > 1) {
                if(l(p(b)) == b) {
                    s.push('L');
                    //pw.println("L");
                } else {
                    s.push('R');
                    //pw.println("R");
                }

                b = p(b);
            }

            int p = 1, q = 1;
            while(!s.isEmpty()) {
                if(s.peek() == 'L') {
                    p = p + q;
                } else {
                    q = q + p;
                }

                s.peek();
            }

            System.out.println(a + " " + p + "\\" + q);

        }        
        pw.close();
    }
}