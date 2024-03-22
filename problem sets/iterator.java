import java.util.ArrayList;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("Apple");
        myList.add("Banana");
        myList.add("Cherry");
    }
}

Iterator<String> iterator = myList.iterator();

while (iterator.hasNext()) {
    String element = iterator.next();
    System.out.println(element);
}

Iterator<String> iterator = myList.iterator();
while (iterator.hasNext()) {
    String element = iterator.next();
    if (element.equals("Banana")) {
        iterator.remove(); // Removes the element "Banana" from the list
    }
}
