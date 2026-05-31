import java.util.*;

class BPlusNode {
    boolean isLeaf;
    ArrayList<Integer> keys;
    ArrayList<BPlusNode> children;
    BPlusNode next;

    BPlusNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        children = new ArrayList<>();
        next = null;
    }
}

public class NetflixBPlusTree {

    static final int ORDER = 3;
    static BPlusNode root = new BPlusNode(true);
    static Scanner sc = new Scanner(System.in);

    static void insert(int key) {

        BPlusNode leaf = findLeaf(root, key);

        int pos = 0;
        while (pos < leaf.keys.size() && leaf.keys.get(pos) < key)
            pos++;

        leaf.keys.add(pos, key);

        if (leaf.keys.size() >= ORDER) {
            splitLeaf(leaf);
        }
    }

    static BPlusNode findLeaf(BPlusNode node, int key) {

        if (node.isLeaf)
            return node;

        int i = 0;

        while (i < node.keys.size() && key >= node.keys.get(i))
            i++;

        return findLeaf(node.children.get(i), key);
    }

    static void splitLeaf(BPlusNode leaf) {

        int mid = leaf.keys.size() / 2;

        BPlusNode newLeaf = new BPlusNode(true);

        while (leaf.keys.size() > mid) {
            newLeaf.keys.add(leaf.keys.remove(mid));
        }

        newLeaf.next = leaf.next;
        leaf.next = newLeaf;

        if (leaf == root) {

            BPlusNode newRoot = new BPlusNode(false);

            newRoot.keys.add(newLeaf.keys.get(0));

            newRoot.children.add(leaf);
            newRoot.children.add(newLeaf);

            root = newRoot;

        } else {

            System.out.println("Leaf Split Occurred");
        }
    }

    static boolean search(int key) {

        BPlusNode leaf = findLeaf(root, key);

        return leaf.keys.contains(key);
    }

    static void displayLeaves() {

        BPlusNode current = root;

        while (!current.isLeaf)
            current = current.children.get(0);

        System.out.println("\nMovie IDs in Leaf Nodes:");

        while (current != null) {

            System.out.print("[ ");

            for (int key : current.keys)
                System.out.print(key + " ");

            System.out.print("] -> ");

            current = current.next;
        }

        System.out.println("NULL\n");
    }

    static void displayRoot() {

        System.out.println("\nCurrent Root Keys:");

        for (int key : root.keys)
            System.out.print(key + " ");

        System.out.println("\n");
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("===== Netflix Movie Indexing Using B+ Tree =====");
            System.out.println("1. Insert Movie ID");
            System.out.println("2. Search Movie ID");
            System.out.println("3. Display Leaf Nodes");
            System.out.println("4. Display Root");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Movie ID: ");
                    int id = sc.nextInt();

                    insert(id);

                    System.out.println("Movie Inserted Successfully\n");
                    break;

                case 2:

                    System.out.print("Enter Movie ID to Search: ");
                    int searchId = sc.nextInt();

                    if (search(searchId))
                        System.out.println("Movie Found\n");
                    else
                        System.out.println("Movie Not Found\n");

                    break;

                case 3:

                    displayLeaves();
                    break;

                case 4:

                    displayRoot();
                    break;

                case 5:

                    System.out.println("Thank You");
                    break;

                default:

                    System.out.println("Invalid Choice\n");
            }

        } while (choice != 5);
    }
}