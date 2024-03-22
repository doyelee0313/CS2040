import java.io.*;
import java.util.*;
// Well done!
public class sim {

    class Node {
        char data;
        Node next;
        Node previous;

        Node(char ch) {
            this.data = ch;
            this.next = null;
            this.previous = null;
        }
    }

    class MyLinkedList {
        private Node head;
        private Node cursor;
        private Node tail;

        MyLinkedList() {
            Node empty = new Node('\0');
                head = empty;
                cursor = empty;
                tail = empty;
        }
        
            void insert(char ch) {
                Node newNode = new Node(ch); 
                if (cursor == tail) {
                    tail = newNode;
                }
                newNode.next = cursor.next; //linking newNode to cursor next
                cursor.next = newNode; //linking cursor to newNode
                newNode.previous = cursor; //linking newNode and cursor
                cursor = newNode; //shifting cursor
            }
        
            void backspace() {
                if (cursor == head) { //do nothing
                    return;
                }
                cursor.previous.next = cursor.next; //linking pre to next 
                if (cursor == tail) { 
                    tail = cursor.previous; //shift tail, no need to link next to pre since its null
                } else {
                    cursor.next.previous = cursor.previous; //linking next to pre 
                }
                cursor = cursor.previous; //shifting cursor to back
            }

            void home() {
                cursor = head;
            }
        
            void end() {
                cursor = tail;
            }

        public String getText() {
            ArrayList<Character> charList = new ArrayList<>();
            Node current = head.next;

            while (current != null) {
                charList.add(current.data);
                current = current.next;
            }
            char[] charArray = new char[charList.size()];
            for (int i = 0; i < charList.size(); i++) {
                charArray[i] = charList.get(i);
            }
        
            return new String(charArray);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(br.readLine());

        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        sim sim = new sim();

        for (int i = 0; i < numCases; i++) {
            String input = br.readLine();
            MyLinkedList list = sim.new MyLinkedList();
                for (char ch : input.toCharArray()) {
                    if (ch == '<') {
                        list.backspace();
                    } else if (ch == '[') {
                        list.home();
                    } else if (ch == ']') {
                        list.end();
                    } else {
                        list.insert(ch);
                    }
                //System.out.println(i + 1 + "th try: " + list.getText());
                }
                //System.out.println();
                writer.println(list.getText());
            }

        br.close();
        writer.close();
    }
}
