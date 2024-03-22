/*A0255594R
Lee Doye
Ren Weilin
CS2040S*/ 

import java.util.*;
// Good work!
public class jobbyte {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // Number of friends
        int[] jobs = new int[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = sc.nextInt();
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            while (jobs[i] != i + 1) { //while the ith index has the correct number
                int temp = jobs[i];
                jobs[i] = jobs[temp - 1];
                jobs[temp - 1] = temp;

                swaps++;
            }
        }

        System.out.println(swaps);
    }
}
