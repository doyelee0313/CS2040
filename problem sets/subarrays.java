// 6% VA OQ will be easier than this

import java.io.*;
import java.util.*;

public class subarrays {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");

        int N = Integer.parseInt(token[0]), K = Integer.parseInt(token[1]), B = Integer.parseInt(token[2]);

        boolean work = false;

        List<Integer> list = new ArrayList<>();

        String[] t = br.readLine().split(" ");

        for(int i = 0; i < N; i++) {
            list.add(Integer.parseInt(t[i]));
        }

        //pw.println(list);

        loop1: for(int i = 1; i < list.size() - 1 ; i++) { //space
            for(int j = 0; j < list.size() - i ; j++) { //index
                //pw.print(j + " " + i);
                List<Integer> l = list.subList(j, j + i + 1);   
                //pw.println(l);  
                long count = 0;
                for(int m = 0; m < l.size(); m++) {
                    count += l.get(m);
                    //pw.println(count);
                }
                if(K == 0) {
                    if (B == count){
                    //pw.println("this works : " + l);
                    //pw.println(l);
                    pw.print(j + " " + (j + i));
                    work = true;
                    break loop1;
                }
                }
                if (B == count - K * l.size()){
                    //pw.println("this works : " + l);
                    //pw.println(l);
                    pw.print(j + " " + (j + i));
                    work = true;
                    break loop1;
                }
            }
        }

        if(!work) {
            pw.println(-1);
        }

        pw.close();
    }
}
