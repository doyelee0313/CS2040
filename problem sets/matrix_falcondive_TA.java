import java.io.*;
import java.util.*;

public class falcondive {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        // Nitpick: Use more descriptive name
        String hi = (br.readLine());
        String[] parts = hi.split(" ");
        //System.out.println("parts is" + parts[1]);
        
        int row = Integer.parseInt(parts[0]);
        int column = Integer.parseInt(parts[1]);
        // Nitpick: Use more descriptive name
        String s = parts[2];
        
        char falcon = s.charAt(1);
        //change falcon from string to char
        //System.out.println("row is" + row + "column is " + column + falcon);
        
        char[][] picture1 = new char[row][column];
        char[][] picture2 = new char[row][column];
        char[][] result = new char[row][column];
        char[][] background = new char[row][column];
        // Nitpick: strings imply plural, its easy to mistake it for an array or arraylist
        String strings = br.readLine();
        //String line = br.readLine();
        //System.out.println(strings);
        
        // for (int i = 0; i < 2 * row - 1 ; i++) {
        //     strings = strings + sc.next();
        //     line = sc.nextLine();
        //     //System.out.println("string: " + strings);
        //     //System.out.println("line: " + line);
        // }

        for (int i = 0; i < 2 * row; i++) {
            strings = strings + br.readLine();
        }
        
        //System.out.println("string: " + strings);
        
        int pic1num = 0;
        int pic2num = row * column;
        
        //inputting the picture
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                picture1[i][j] = strings.charAt(pic1num++);
                picture2[i][j] = strings.charAt(pic2num++);
            }
        }

        //duplicate background
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                    background[i][j] = picture1[i][j];
            }
        }

        for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if (background[i][j] == falcon) {
                    background[i][j] = picture2[i][j];
                }
            }
        }

        // //printing out the background
        //  for (int i = 0; i < h; i++) {
        //     for(int j = 0; j < v; j++) {
        //         System.out.print(background[i][j]);
        //     }
        //     System.out.println();
        // }
        
        // //printing out the picture1
        // for (int i = 0; i < h; i++) {
        //     for(int j = 0; j < v; j++) {
        //         System.out.print(picture1[i][j]);
        //     }
        //     System.out.println();
        // }
        
        // //printing out the picture2
        //  for (int i = 0; i < h; i++) {
        //     for(int j = 0; j < v; j++) {
        //         System.out.print(picture2[i][j]);
        //     }
        //     System.out.println();
        // }
        
        int pic1i = 0;
        int pic1j = 0;
        int pic2i = 0;
        int pic2j = 0;

        //first falcon in pic1
        loop1: for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if (picture1[i][j] == falcon) {
                    pic1i = i;
                    pic1j = j;
                    //System.out.println("pic1" + pic1i);
                    //System.out.println("pic1" + pic1j);
                    break loop1;
                }
            }
        }
        
        //System.out.println(pic1i);
        //System.out.println(pic1j);

        //first falcon in pic2
        loop2: for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if (picture2[i][j] == falcon) {
                    pic2i = i;
                    pic2j = j;
                    //System.out.println("pic2" + pic2i);
                    //System.out.println("pic2" + pic2j);
                    break loop2;
                }
            }
        }
        
        //System.out.println(pic2i);
        //System.out.println(pic2j);
        
        //calculate how much to move the pic
        int imove = pic2i - pic1i;
        int jmove = pic2j - pic1j;

        //System.out.println(imove);
        //System.out.println(jmove);

        result = background;
        
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if (picture2[i][j] == falcon) {
                    if((0 <= i+imove) && (i+imove < row) && (0 <= j+jmove) && (j+jmove < column)) {
                    result[i+imove][j+jmove] = falcon;   
                    }
                }
            }
        }
        
        // for (int i = 0; i < h; i++) {
        //     for(int j = 0; j < v; j++) {
        //         if (picture1[i][j] != falcon) {
        //             result[i][j] = picture1[i][j];
        //         }
        //     }
        // }
        // nitpick: redundant comment
        //printing out the result
         for (int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                writer.print(result[i][j]);
            }
            writer.println();
        }

        writer.close();
    }
}