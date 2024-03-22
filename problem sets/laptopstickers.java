import java.io.*;
import java.util.*;

public class laptopstickers {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String[] token = br.readLine().split(" ");

        int L = Integer.parseInt(token[0]), H = Integer.parseInt(token[1]), K = Integer.parseInt(token[2]);
        
        char[][] matrix = new char[H][L];

        for(int i = 0 ; i < H ; i++) {
            for(int j = 0 ; j < L ; j++) {
                matrix[i][j] = '_';
            }
        }

        for(int m = 0 ; m < K ; m++) {
            String[] sticker = br.readLine().split(" ");

            int l = Integer.parseInt(sticker[0]), h = Integer.parseInt(sticker[1]), a = Integer.parseInt(sticker[2]), b = Integer.parseInt(sticker[3]);

            for(int i = b ; i < h + b ; i++) {
                if (i > H) break;
                for(int j = a ; j < l + a ; j++) {
                    if (j > L) break;
                    matrix[i][j] = (char)('a' + m);
                }
            }
        }

        for(int i = 0 ; i < H ; i++) {
            for(int j = 0 ; j < L ; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        pw.close();
    }
}