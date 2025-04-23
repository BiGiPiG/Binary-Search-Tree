import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class BinarySearchTree {
    public static class Node {
        int key;
        int level;
        Node parent = null;
        Node left = null;
        Node right = null;

        public Node(int key, int level) {
            this.key = key;
            this.level = level;
        }

        public Node(int key, int level, Node parent) {
            this.key = key;
            this.level = level;
            this.parent = parent;
        }
    }

    private Node root;
    private int bstLevel;

    public BinarySearchTree() {
    }

    //добавление элемента
    public boolean addElement(int key) {
        if (root == null) {
            root = new Node(key, 1);
            return true;
        }
        return addElement(root, key);
    }

    private boolean addElement(Node node, int key) {
        if (key < node.key) {
            if (node.left == null) {
                node.left = new Node(key, node.level + 1, node);
                bstLevel = Math.max(bstLevel, node.level + 1);
                return true;
            } else {
                return addElement(node.left, key);
            }
        } else if (key > node.key) {
            if (node.right == null) {
                node.right = new Node(key, node.level + 1, node);
                bstLevel = Math.max(bstLevel, node.level + 1);
                return true;
            } else {
                return addElement(node.right, key);
            }
        }
        return false;
    }

    //получение глубины элемента
    public int findHeightOfElement(int key) {
        if (root == null) {
            return -1;
        }
        return findHeightOfElement(root, key);
    }

    private int findHeightOfElement(Node node, int key) {
        if (node == null) {
            return -1;
        }
        if (node.key == key) {
            return node.level;
        }
        if (key < node.key) {
            return findHeightOfElement(node.left, key);
        } else {
            return findHeightOfElement(node.right, key);
        }
    }

    //поиск второго по величине элемента
    public int secondMax() {
        return secondMax(root);
    }

    private int secondMax(Node node) {
        // Случай 1: есть правый ребенок
        if (node.right != null) {
            // Если у правого ребенка нет детей, то текущий узел - второй максимум
            if (node.right.right == null && node.right.left == null) {
                return node.key;
            }
            // Иначе продолжаем искать в правом поддереве
            return secondMax(node.right);
        }
        // Случай 2: нет правого ребенка, но есть левый
        if (node.left != null) {
            return findMax(node.left);
        }
        // Случай 3: узел - лист
        return node.parent.key;
    }

    //поиск максимума
    private int findMax(Node node) {
        if (node.right == null) {
            return node.key;
        }
        return findMax(node.right);
    }

    //вывод дерева
    public void printTree() {
        printTree(root);
    }


    private void printTree(Node curNode) {
        if (curNode == null) {
            return;
        }

        // Сначала печатаем левое поддерево
        printTree(curNode.left);

        // Затем печатаем текущий узел
        System.out.print(curNode.key + " ");

        // Затем печатаем правое поддерево
        printTree(curNode.right);
    }

    //вывод всех листьев дерева
    public void printLists() {
        printLists(root);
    }

    private void printLists(Node curNode) {
        if (curNode == null) {
            return;
        }

        if (curNode.left == null && curNode.right == null) {
            System.out.println(curNode.key);
            return;
        }

        printLists(curNode.left);

        printLists(curNode.right);
    }

    //вывод всех узлов с двумя детьми
    public void printForks() {
        printForks(root);
    }

    private void printForks(Node curNode) {
        if (curNode == null) {
            return;
        }

        printForks(curNode.left);

        if (curNode.left != null && curNode.right != null) {
            System.out.println(curNode.key);
        }

        printForks(curNode.right);
    }

    //удаление элемента по ключу
    public void removeElement(int key) {
        root = removeElement(root, key);
    }

    private Node removeElement(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = removeElement(node.left, key);
        } else if (key > node.key) {
            node.right = removeElement(node.right, key);
        } else {
            // Узел с одним или без детей
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Узел с двумя детьми: находим минимальный в правом поддереве
            node.key = findMin(node.right).key;

            // Удаляем минимальный узел в правом поддереве
            node.right = removeElement(node.right, node.key);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}