import java.util.*;

class Book {
    String name;
    long pages;

    public Book(String name, long pages) {
        this.name = name;
        this.pages = pages;
    }
}

// Suggestion: Have Book class implement Comparable<Book> instead
class BookNameComparator implements Comparator<Book> {

    public int compare(Book book1, Book book2) {
        return book1.name.compareTo(book2.name);
    }

}

class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class janeeyre {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long m = sc.nextLong();
        long janePage = sc.nextLong(); sc.nextLine();
        //System.out.println("n is: " + n + " m is: " + m + " janeTime is:" + janeTime);

        PriorityQueue<Book> books = new PriorityQueue<>(new BookNameComparator());

        for (int i = 0; i < n; i++) {
            String[] book = sc.nextLine().split("\"");
            String name = book[1];
            long pages = Long.parseLong(book[2].trim());
            //System.out.println("name is: " + name + " pages is: " + pages);
            books.add(new Book(name, pages));
        }
        
        //should sort the books from friends in ascending order
        // Suggestion: You can have the arrival time be a field in the Book class and not use a Pair
        ArrayList<Pair<Long, Book>> futureBooks = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] book = sc.nextLine().split("\"");
            Long arrivingTime = Long.parseLong(book[0].trim());
            String name = book[1];
            long pages = Long.parseLong(book[2].trim());
            //System.out.println("name is: " + name + " pages is: " + pages);
            //add the book to the queue directly if arrivingTime is 0
            if (arrivingTime == 0 ) {
                books.add(new Book(name, pages));
                //System.out.println("arrival time is 0. arriving time is: " + arrivingTime + " name is: " + name + " pages is: " + pages);
            } if (name.equals("Jane Eyre")) {
                janePage = pages;
                //System.out.println("updated");
            } else if (name.compareTo("Jane Eyre") < 0) { //adding only the books that will be read to the arraylist
                futureBooks.add(new Pair<>(arrivingTime, new Book(name, pages)));
                //System.out.println("adding new. arriving time is: " + arrivingTime + " name is: " + name + " pages is: " + pages);
            }
            //System.out.println("arriving time is: " + arrivingTime + " name is: " + name + " pages is: " + pages);
        }

        books.add(new Book("Jane Eyre", janePage));
        
        //sorting the books that friends gave in order of the arrivingTime
        futureBooks.sort(Comparator.comparingLong(pair -> pair.getKey()));

        long time = 0;
        int a = 0;
        Book currentBook = null;

        //read until there is no book in the pile, or read Jane Eyre
        while (currentBook == null || !currentBook.name.equals("Jane Eyre")) {

            currentBook = books.poll();
            //System.out.println("current book: " + currentBook.name + " book reading time: " + currentBook.pages);
            time = time + currentBook.pages;
            //System.out.println("current time: " + time);

            //if there is a book in the futureBook arraylist
            if (a < futureBooks.size()) {
                //if the book arrived before current time
                if (time >= futureBooks.get(a).getKey()) {
                    books.add(futureBooks.get(a).getValue());
                    a++;
                }
            }
        }
        System.out.println(time);
        
    }
}
