import java.util.*;

public class tenis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String firstName = sc.next();
        String secondName = sc.next();
        sc.nextLine();
        int matches = sc.nextInt();
        sc.nextLine();
        
        //ArrayList<String> result = new ArrayList<>(matches);
        
        for (int i = 0; i < matches ; i++){
        
            String line = sc.nextLine();
            String[] match = line.split(" ");
        
            //System.out.println(match[0]);
            // Nitpick: camelCase for Java
            int FirstWin = 0;
            int SecondWin = 0;
            int wrong = 0;
            
            for (int j = 0; j < match.length; j++) {
                
                // if (match.length > 3) {
                //     break;
                // }
                
                String[] parts = match[j].split(":"); 
                String FirstScoreString = parts[0]; 
                String SecondScoreString = parts[1];
        
                int FirstScore = Integer.parseInt(FirstScoreString);
                int SecondScore = Integer.parseInt(SecondScoreString);
    
                //System.out.println(FirstScore);
                //System.out.println(SecondScore);
                
                if ((FirstScore == 6 && SecondScore <= 4) || (j == 2 && FirstScore > 6 && FirstScore-2 == SecondScore) 
                || (j < 2 && FirstScore == 7 && SecondScore == 6) || (FirstScore == 7 && SecondScore ==5)) {
                    FirstWin++;
                    //System.out.println("firstWin is: " + FirstWin);
                    if (FirstWin == 2 && SecondWin == 0 && match.length == 3) {
                        wrong = 1;
                        break;
                    }
                } else if ((SecondScore == 6 && FirstScore <= 4) || (j == 2 && SecondScore > 6 && SecondScore-2 == FirstScore) 
                || (j < 2 && SecondScore == 7 && FirstScore == 6) || (SecondScore == 7 && FirstScore ==5)) {
                    SecondWin++;
                    //System.out.println("secondWin is: " + SecondWin);
                    if (SecondWin == 2 && FirstWin == 0 && match.length == 3) {
                        wrong = 1;
                        break;
                    }
                } else {
                    wrong = 1;
                    break;
                }
                
                // else if (j == 2 && FirstScore > 6 && FirstScore-2 == SecondScore) {
                //     FirstWin++;
                //     break;
                // } else if ((j == 2 && SecondScore > 6 && SecondScore-2 == FirstScore)) {
                //     SecondWin++;
                //     break;
                // } 
                
            }
            
            //System.out.println("FirstWin is: " + FirstWin);
            //System.out.println("SecondWin is: " + SecondWin);
            
            if ((firstName.equals("federer") && SecondWin > 0) || (secondName.equals("federer") && FirstWin > 0)) {
                System.out.println("ne");
            } else if ((FirstWin == 2 || SecondWin == 2) && (FirstWin + SecondWin <= 3) && (wrong == 0)) {
                System.out.println("da");
            } else {
                System.out.println("ne");
            }
        }
    }
}