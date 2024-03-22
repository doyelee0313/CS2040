import java.io.*;
import java.util.*;


public class universityzoning {
    
    public static void main(String[] args) throws IOException{
        
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String hi = (br.readLine());
        String[] parts = hi.split(" ");

        long R = Integer.parseInt(parts[0]), C = Integer.parseInt(parts[1]);
        int F = Integer.parseInt(parts[2]), S = Integer.parseInt(parts[3]), G = Integer.parseInt(parts[4]);

        @SuppressWarnings("unchecked")
        ArrayList<Pair<Integer, Integer>>[] ideal = new ArrayList[F];

        for (int i = 0; i < F; i++) {
            ideal[i] = new ArrayList<>();
        }

        for(int i = 0; i < F; i++) {
            String s = br.readLine();
            String[] string = s.split(" ");
            int numOfStu = Integer.parseInt(string[0]);

            for (int j = 1; j < (2 * numOfStu + 1); j++) {
                if(j % 2 != 0) {
                int row = Integer.parseInt(string[j]);
                int column = Integer.parseInt(string[j+1]);
                Pair<Integer, Integer> pair = new Pair<>(row, column);
                ideal[i].add(pair);
                }
            }

        }

        //sorting ideal
        for (int i = 0; i < F; i++) {
            Collections.sort(ideal[i], (pair1, pair2) -> {
                // compare based on the first element
                int compareFirst = pair1.getrow().compareTo(pair2.getrow());

                // compare based on the second element 
                if (compareFirst == 0) {
                    return pair1.getcolumn().compareTo(pair2.getcolumn());
                }

                return compareFirst;
            });
        }

        // //printing ideal arraylist
        // for (int i = 0; i < F; i++) {
        //     for(int j = 0; j < ideal[i].size(); j++) {
        //         System.out.println("ideal " + i + ": " + ideal[i].get(j).getrow() + " " + ideal[i].get(j).getcolumn());
        //     }
        // }

        @SuppressWarnings("unchecked")
        ArrayList<Pair<Integer, Pair<Integer, Integer>>>[] current = new ArrayList[F];

        for (int i = 0; i < F; i++) {
            current[i] = new ArrayList<>();
        }

        for(int i = 0; i < S; i++) {
            String f = br.readLine();
            String[] fa = f.split(" ");
            int row = Integer.parseInt(fa[0]), column = Integer.parseInt(fa[1]), 
            id = Integer.parseInt(fa[2]), faculty = Integer.parseInt(fa[3]); 

            Pair<Integer, Pair<Integer, Integer>> pair = new Pair<>(id, new Pair<>(row, column));
            current[faculty-1].add(pair);
        }

        //sorting current
        for (int i = 0; i < F; i++) {
            Collections.sort(current[i], new Comparator<Pair<Integer, Pair<Integer, Integer>>>() {
                @Override
                public int compare(Pair<Integer, Pair<Integer, Integer>> pair1, Pair<Integer, Pair<Integer, Integer>> pair2) {
                    return pair1.getrow().compareTo(pair2.getrow());
                }
            });
        }

        // //printing ideal arraylist
        // for (int i = 0; i < F; i++) {
        //     for(int j = 0; j < current[i].size(); j++) {
        //         System.out.println("current " + i + ": " + current[i].get(j).getcolumn().getrow() + " " + current[i].get(j).getcolumn().getcolumn());
        //     }
        // }

        //inputting the last line
        int[] min = new int[F];

        String fin = br.readLine();
        String[] fina = fin.split(" ");

        for(int m = 0 ; m < F; m++) {
            min[m] = Integer.parseInt(fina[m]);
        }

        // //chekcing min[m]
        // for (int h = 0; h < F; h++) {
        //     System.out.print(min[h]);
        // }

        // //checking student ID
        // for (int y = 0; y < S; y++) {
        //     System.out.print(studentId[y]);
        // }

        int tempResult;

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] minStep = new ArrayList[F];

        for (int i = 0; i < F; i++) {
            minStep[i] = new ArrayList<>();
        }

        for (int i = 0; i < F; i++) {
            for(int j = 0; j < current[i].size(); j++) {
            tempResult = Math.abs(current[i].get(j).getcolumn().getrow() - ideal[i].get(j).getrow()) 
            + Math.abs(current[i].get(j).getcolumn().getcolumn() - ideal[i].get(j).getcolumn());
            //System.out.println("ideal row: " + ideal[i].get(j).getrow());
            //System.out.println("ideal column: " + ideal[i].get(j).getcolumn());
            //System.out.println("current row: " + current[i].get(j).getcolumn().getrow());
            //System.out.println("current column: " + current[i].get(j).getcolumn().getcolumn());
            //System.out.println(tempResult);
            //ideal[i].remove(0);
            //current[i].remove(0);
            //System.out.println(ideal[studentId[i]-1].get(0));
            minStep[i].add(tempResult);
            }
        }

        //printing out the arraylist of minimal steps for students in each faculty
        for (int i = 0; i < F; i++) {
            Collections.sort(minStep[i]);
            //System.out.println("minStep " + i + ": " + minStep[i]);
        }

        long s = 0;
        ArrayList<Long> sum = new ArrayList<>();
        
        //making the minimal step array, adding minimal students in each faculty
        for(int i = 0; i < F; i++) {
            s = 0;
            for(int j = 0; j < min[i] ; j++) {
                s = s + minStep[i].get(j);
                //System.out.println("adding" + s);
            }
            sum.add(s);
        }

        Collections.sort(sum);
        //System.out.println(sum);

        long finalResult = 0;

        //sum up the minimal step to comply G
        for(int i = 0; i < G; i++) {
            finalResult = finalResult + sum.get(i);
        }

        System.out.println(finalResult);


    }

    public static class Pair<A, B> {
        private A row;
        private B column;

        public Pair(A row, B column) {
            this.row = row;
            this.column = column;
        }

        public A getrow() {
            return row;
        }

        public B getcolumn() {
            return column;
        }

    }
}