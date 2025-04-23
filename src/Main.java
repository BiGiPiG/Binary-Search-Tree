import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] keys = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).
                filter(el -> el != 0).toArray();
        BinarySearchTree bst = new BinarySearchTree();

        Arrays.stream(keys).forEach(bst::addElement);

        bst.printTree();
        System.out.println();
        bst.removeElement(3);
        bst.printTree();

    }
}

