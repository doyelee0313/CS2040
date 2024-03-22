import java.util.*;

public class hermits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] street = new int[n];

        for(int i = 0; i < n; i ++) {
            street[i] = sc.nextInt();
        }

        int m = sc.nextInt();

        int[] streetSum = street;

        for(int i = 0; i < m; i ++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            //System.out.println(street[start - 1] + " , and " + street[end - 1] + " sum is: " + streetSum[i]);

            int s = street[start - 1];
            int e = street[end - 1];

            streetSum[end - 1] += s;
            streetSum[start - 1] += e;

            //System.out.println(street[start - 1] + " , and " + street[end - 1] + " sum is: " + streetSum[i]);
        }

        for(int i = 0; i < n; i ++) {
            //System.out.println(i + ": " + streetSum[i]);
        }

        int minValue = street[0];
        int answer = 1;

        for (int i = 0; i < n; i++) {
            if (street[i] < minValue) {
                minValue = street[i];
                answer = i + 1;
            }
        }
        //System.out.println(minValue + ", " + answer);
        System.out.println(answer);
    }
}
