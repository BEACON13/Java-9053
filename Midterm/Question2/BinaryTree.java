
import personnel.Person;

import java.util.ArrayList;
import java.util.Comparator;

import static personnel.Personnel.readPersonnel;

public class BinaryTree<T> {
    Node<T> root;

    private Comparator<T> comparator;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int compare(T x, T y) {
        if (comparator == null) {
            return ((Comparable<T>) x).compareTo(y);
        } else {
            return comparator.compare(x, y);
        }
    }

    public void add(T value) {
        root = recursiveAdd(root, value);
    }

    private Node<T> recursiveAdd(Node<T> current, T value) {
        if (current == null) {
            return new Node<>(value);
        }

        if (compare(value, current.value) < 0) {
            current.left = recursiveAdd(current.left, value);
        } else if (compare(value, current.value) > 0) {
            current.right = recursiveAdd(current.right, value);
        } else {
            // already exists
            return current;
        }
        return current;
    }

    public Node<T> find(T value) {
        return recursiveFind(root, value);
    }

    private Node<T> recursiveFind(Node<T> current, T value) {
        if (current == null) {
            return null;
        }
        if (compare(value, current.value) == 0) {
            return current;
        }
        return compare(value, current.value) < 0
                ? recursiveFind(current.left, value)
                : recursiveFind(current.right, value);
    }

    public void delete(T value) {
        root = recursiveDelete(root, value);
    }

    private Node<T> recursiveDelete(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (compare(value, current.value) == 0) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }

            T minValue = findMinValue(this.root);
            current.setValue(minValue);
            current.right = recursiveDelete(current.right, minValue);
        }

        if (compare(value, current.value) < 0) {
            current.left = recursiveDelete(current.left, value);
            return current;
        }
        current.right = recursiveDelete(current.right, value);
        return current;
    }

    private T findMinValue(Node<T> current) {
        return current.left == null ? current.value : findMinValue(current.left);
    }


    public String toString() {
        // toString should print out the contents of the
        // binary tree in sorted order
        return inorderTraversal(root, new StringBuilder()).toString();
    }

    private StringBuilder inorderTraversal(Node<T> node, StringBuilder sb) {
        if (node != null) {
            inorderTraversal(node.left, sb);
            sb.append(node.value.toString()).append("\n");
            inorderTraversal(node.right, sb);
        }
        return sb;
    }

    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        b.add(5);
        b.add(2);
        b.add(10);
        b.add(54);
        b.add(20);
        b.add(23);
        b.delete(20);
        b.find(20); // returns the Integer 20
        b.find(999); // returns null
        System.out.println(b.toString());

        ArrayList<Person> personnel = readPersonnel("personnel.csv");
        BinaryTree<Person> personnelTree = new BinaryTree<>(Comparator.comparing(Person::getBirthdate));
        for (Person person : personnel){
            personnelTree.add(person);
        }
        System.out.println(personnelTree);
    }
}
