import java.io.*;
import java.util.*;

public class joinstrings {

    class Node {
        int data;
        Node next;
    
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] arg) {

        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        sc.nextLine();
        
        Node head = new Node(sc.next()); sc.nextLine();
        Node current = head;

        for (int i = 2; i <= n; i++) {
            current.next = new Node(sc.next()); sc.nextLine();
            current = current.next;
        } 

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");

        //java strings are immutable, runtime would be O(m+n)

        // for(int i = 0; i < n-1; i++) {
        //     int a = sc.nextInt(); int b = sc.nextInt(); sc.nexLine();
        //     // String temp = list.get(b);
        //     // String result = list.get(a);
        //     // result = result + temp;
        //     // list.remove(b);
        //     // list.add(b, " ");
        // }
        
    }
}