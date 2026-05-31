import java.util.Scanner;

class Node {
    int id, height;
    String name;
    Node left, right;

    Node(int id, String name) {
        this.id = id;
        this.name = name;
        this.height = 1;
    }
}

public class LibraryAVL {

    static Node root;

    static int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    static int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node node, int id, String name) {

        if (node == null)
            return new Node(id, name);

        if (id < node.id)
            node.left = insert(node.left, id, name);
        else if (id > node.id)
            node.right = insert(node.right, id, name);
        else {
            System.out.println("Book ID already exists!");
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && id < node.left.id)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && id > node.right.id)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && id > node.left.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && id < node.right.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    static Node search(Node node, int id) {

        if (node == null || node.id == id)
            return node;

        if (id < node.id)
            return search(node.left, id);

        return search(node.right, id);
    }

    static void inorder(Node node) {

        if (node != null) {
            inorder(node.left);
            System.out.println(node.id + " - " + node.name);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Preloaded books
        root = insert(root, 50, "Java Programming");
        root = insert(root, 30, "Data Structures");
        root = insert(root, 70, "Database Systems");
        root = insert(root, 20, "Operating Systems");
        root = insert(root, 40, "Computer Networks");

        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT USING AVL TREE =====");
            System.out.println("1. Add New Book");
            System.out.println("2. Search Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Name: ");
                    String name = sc.nextLine();

                    root = insert(root, id, name);

                    System.out.println("Book Added Successfully!");
                    break;

                case 2:
                    System.out.print("Enter Book ID to Search: ");
                    int searchId = sc.nextInt();

                    Node result = search(root, searchId);

                    if (result != null) {
                        System.out.println("\nBook Found!");
                        System.out.println("Book ID   : " + result.id);
                        System.out.println("Book Name : " + result.name);
                    } else {
                        System.out.println("\nBook Not Found!");
                    }
                    break;

                case 3:
                    System.out.println("\n===== BOOK LIST (SORTED) =====");
                    inorder(root);
                    break;

                case 4:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}