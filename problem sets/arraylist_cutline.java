import java.io.*;
import java.util.ArrayList;

public class cutline {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter pw = new PrintWriter(System.out);

        String l = br.readLine();
        int length = Integer.parseInt(l);

        //System.out.println("length: " + length);

        ArrayList<String> line = new ArrayList<String>();

        //making a new line
        for(int i = 0; i < length; i++){
            line.add(br.readLine());
        }

        //printing out the line
        for (int i = 0; i < length; i++) {
            System.out.println(line.get(i));
        }

        //getting number of line cutting
        String cut = br.readLine();
        int cutline = Integer.parseInt(cut);

        //changing the line
        for(int j = 0; j < cutline; j++) {
            String hi = (br.readLine());
            String[] parts = hi.split(" ");
        
            if (parts[0].equals("cut")) { //dont use '==' for string comparing!!
                int a = line.indexOf(parts[2]);
                line.add(a, parts[1]);
            } else {
                line.remove(parts[1]);
            }
        }

        //printing out the line
        for (int i = 0; i < line.length(); i++) {
            System.out.println(line.get(i));
        }

    }
}