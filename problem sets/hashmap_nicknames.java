import java.io.*;
import java.util.*;

public class nicknames {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());

/*
        ArrayList<String> names = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            String name = br.readLine();
            names.add(name);
        }

        int n = Integer.parseInt(br.readLine());

        for(int j = 0; j < n; j++) {
            String nickName = br.readLine();
            int ans = 0;
            for(int i = 0; i < N; i++) { 
                if (names.get(i).charAt(0) == nickName.charAt(0)) {
                    ans++;
                }
            }
            pw.println(ans);
        }
*/
         
        Map<String, Integer> hash = new LinkedHashMap<>(N);

        ArrayList<String> names = new ArrayList<>(N);

        for(int i = 0; i < N; i++) {
            String name = br.readLine();
            //String result = name.substring(0, 1);
            //names.add(result);  
            names.add(name);
        }

        int NN = Integer.parseInt(br.readLine());

        for(int i = 0; i < NN; i++) {
            String nickName = br.readLine();
            hash.put(nickName, 0);
        }

        for(int i = 0; i < N; i++) {
            String theName = names.get(i);
            if(hash.containsKey(theName)) {
                hash.compute(nickName, (key, value) -> value + 1);
            }
        }

        List<Map.Entry<String, Integer>> hashList = new ArrayList<>(hash.entrySet());

        for (Map.Entry<String, Integer> entry : hashList) {
            //pw.println(i + entry.getKey() + " is: " + entry.getValue());
            pw.println(entry.getValue());
        }

        pw.close();
    }


}
