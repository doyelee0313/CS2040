// A0266671W Chaeyeon Lee

import java.util.*;
import java.io.*;

class Vertex{
    String key;
    int height;
    int size;
    Vertex parent;
    Vertex left;
    Vertex right;

    Vertex(String key){
        this.key = key;
        this.height = 0;
        this.size = 1;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}

class AVL{
    Vertex root;

    AVL(){
        this.root = null;
    }

    void insert(String v) {
        root = insert(root, v);
    }

    Vertex insert(Vertex T, String v){
        if(T == null){
            return new Vertex(v);
        }
        if(v.compareTo(T.key)<0){
            T.left = insert(T.left, v);
            T.left.parent = T;
        }else{
            T.right = insert(T.right, v);
            T.right.parent = T;
        }
        updateHeight(T);
        updateSize(T);
        return isRotationNeeded(T);
    }

    int height(Vertex T) {
        if (T == null){
            return -1;
        }else{
            return T.height;
        }
    }

    void updateHeight(Vertex T) {
        if (T != null) {
            T.height = Math.max(height(T.left), height(T.right)) + 1;
        }
    }

    int size(Vertex T) {
        if (T == null){
            return 0;
        }else{
            return T.size;
        }
    }

    void updateSize(Vertex T) {
        if (T != null){
            T.size = size(T.left) + size(T.right);
        }
        if (T != null) {
            T.size = size(T.left) + size(T.right) + 1;
        }
    }


    int balanceFactor(Vertex T) {
        if (T == null){
            return 0;
        }else{
            return height(T.left) - height(T.right);
        }
    }

    Vertex isRotationNeeded(Vertex T){
        if (balanceFactor(T) < -1) {
            if (balanceFactor(T.right) > 0) {
                T.right = rotateRight(T.right);
            }
            T = rotateLeft(T);
        } else if (balanceFactor(T) > 1) {
            if (balanceFactor(T.left) < 0) {
                T.left = rotateLeft(T.left);
            }
            T = rotateRight(T);
        }
        return T;
    }

    Vertex rotateLeft(Vertex T) {
        if (T.right != null) {
            Vertex w = T.right;
            T.right = w.left;
            if (w.left != null) {
                w.left.parent = T;
            }
            w.parent = T.parent;
            if (T.parent == null) {
                this.root = w;
            } else if (T == T.parent.left) {
                T.parent.left = w;
            } else {
                T.parent.right = w;
            }
            w.left = T;
            T.parent = w;

            w.size = T.size;
            updateSize(T);
            updateHeight(T);
            updateHeight(w);
            return w;
        }
        return T;
    }

    Vertex rotateRight(Vertex T) {
        if (T.left != null) {
            Vertex w = T.left;
            T.left = w.right;
            if (w.right != null) {
                w.right.parent = T;
            }
            w.parent = T.parent;
            if (T.parent == null) {
                this.root = w;
            } else if (T == T.parent.right) {
                T.parent.right = w;
            } else {
                T.parent.left = w;
            }
            w.right = T;
            T.parent = w;

            w.size = T.size;
            updateSize(T);
            updateHeight(T);
            updateHeight(w);
            return w;
        }
        return T;
    }


    int isValid(String v) {
        Vertex highestValid = findHighestValid(this.root, v);
        if (highestValid == null) {
            return 0;
        }
        return 1 + checkLeft(highestValid.left, v) + checkRight(highestValid.right, v);
    }

    Vertex findHighestValid(Vertex T, String v) {
        if (T == null) {
            return null;
        }
        String current = T.key;
        if (current.indexOf(v) == 0) {
            return T;
        }
        int compare = v.compareTo(current);
        if (compare < 0) {
            return findHighestValid(T.left, v);
        } else {
            return findHighestValid(T.right, v);
        }
    }

    int checkLeft(Vertex T, String query) {
        if (T == null) {
            return 0;
        }
        String current = T.key;
        if (current.indexOf(query) == 0) {
            return 1 + checkLeft(T.left, query) + size(T.right);
        } else {
            return checkLeft(T.right, query);
        }
    }

    int checkRight(Vertex T, String v) {
        if (T == null) {
            return 0;
        }
        String current = T.key;
        if (current.indexOf(v) == 0) {
            return 1 + checkRight(T.right, v) + size(T.left);
        } else {
            return checkRight(T.left, v);
        }
    }

}

public class Nicknames{
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        int A = Integer.parseInt(br.readLine());

        HashMap<String, Integer> nicknameCount = new HashMap<String, Integer>();
        HashMap<Character, AVL> nicknames = new HashMap<Character, AVL>();

        for (int i=0; i<A; i++) {
            String name = br.readLine();
            char firstChar = name.charAt(0);

            if (nicknames.containsKey(firstChar)){
                nicknames.get(firstChar).insert(name);
            } else {
                AVL nickname = new AVL();
                nickname.insert(name);
                nicknames.put(firstChar, nickname);
            }

            // for (int j=1; j<=name.length(); j++){
            //     String firstNChar = name.substring(0,j);

            //     if (!nicknameCount.containsKey(firstNChar)){
            //         nicknameCount.put(firstNChar, 1);
            //     } else {
            //         nicknameCount.put(firstNChar, nicknameCount.get(firstNChar)+1);
            //     }
            // }
        }

        int B = Integer.parseInt(br.readLine());
        for (int i=0; i<B; i++){
            String nickname = br.readLine();
            int count = 0;

            if (nicknameCount.containsKey(nickname)) {
                count = nicknameCount.get(nickname);
            } else {
                char firstChar = nickname.charAt(0);
                if (nicknames.containsKey(firstChar)) {
                    count = nicknames.get(firstChar).isValid(nickname);
                }
                nicknameCount.put(nickname, count);
            }
            pw.println(count);
        }
        pw.close();
    }
}
