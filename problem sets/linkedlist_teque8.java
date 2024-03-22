import java.io.*;

public class teque8 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());

        Deque left = new Deque();
        Deque right = new Deque();

        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            String command = parts[0];

            if (command.equals("get")) {
                int x = Integer.parseInt(parts[1]);
                if (x < left.size()) {
                    writer.write(left.get(x) + "\n");
                } else {
                    writer.write(right.get(x - left.size()) + "\n");
                }
            } else {
                int x = Integer.parseInt(parts[1]);
                if (command.equals("push_back")) {
                    right.addLast(x);
                } else if (command.equals("push_front")) {
                    left.addFirst(x);
                } else if (command.equals("push_middle")) {
                    if (left.size() >= right.size()) {
                        left.addLast(x);
                    } else {
                        left.addLast(right.removeFirst());
                        right.addFirst(x);
                    }
                }
                left.rearrange(right);
            }
        }

        writer.flush();
        writer.close();
        reader.close();
    }
}

class Deque {
    int start;
    int end;
    int size = 0;
    int[] arr;

    public Deque() {
        this.arr = new int[1000000];
        this.start = this.arr.length / 2;
        this.end = this.arr.length / 2;
    }

    public void addFirst(int value) {
        if (this.start == this.end) {
            this.end++;
        }
        this.arr[this.start] = value;
        this.start--;
        this.size++;
    }

    public void addLast(int value) {
        if (this.start == this.end) {
            this.start--;
        }
        this.arr[this.end] = value;
        this.end++;
        this.size++;
    }

    public int removeFirst() {
        int temp = this.arr[this.start + 1];
        this.start++;
        this.size--;
        return temp;
    }

    public int removeLast() {
        int temp = this.arr[this.end - 1];
        this.end--;
        this.size--;
        return temp;
    }

    public int get(int index) {
        return this.arr[this.start + 1 + index];
    }

    public int size() {
        return this.size;
    }

    public void rearrange(Deque other) {
        if (this.size() - other.size() >= 2) {
            other.addFirst(this.removeLast());
        } else if (other.size() - this.size() >= 2) {
            this.addLast(other.removeFirst());
        }
    }
}
