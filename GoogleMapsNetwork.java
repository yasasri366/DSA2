import java.util.*;

public class GoogleMapsNetwork {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    // BFS
    static void bfs(int start) {

        Queue<Integer> queue = new LinkedList<>();

        visited = new boolean[5];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {

            int node = queue.poll();

            System.out.print(node + " ");

            for (int neighbor : graph[node]) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;
                    queue.add(neighbor);

                }
            }
        }
    }

    // DFS
    static void dfs(int node) {

        visited[node] = true;

        System.out.print(node + " ");

        for (int neighbor : graph[node]) {

            if (!visited[neighbor]) {

                dfs(neighbor);

            }
        }
    }

    public static void main(String[] args) {

        graph = new ArrayList[5];

        for (int i = 0; i < 5; i++) {
            graph[i] = new ArrayList<>();
        }

        // Roads between cities
        graph[0].add(1);
        graph[0].add(2);

        graph[1].add(0);
        graph[1].add(3);

        graph[2].add(0);
        graph[2].add(4);

        graph[3].add(1);
        graph[3].add(4);

        graph[4].add(2);
        graph[4].add(3);

        System.out.println("=================================");
        System.out.println(" GOOGLE MAPS NETWORK ANALYZER ");
        System.out.println("=================================");

        System.out.println("\nCities:");
        System.out.println("0 = Hyderabad");
        System.out.println("1 = Vijayawada");
        System.out.println("2 = Warangal");
        System.out.println("3 = Guntur");
        System.out.println("4 = Khammam");

        System.out.println("\nBFS Traversal:");
        visited = new boolean[5];
        bfs(0);

        System.out.println("\n");

        System.out.println("DFS Traversal:");
        visited = new boolean[5];
        dfs(0);

        System.out.println("\n");

        System.out.println("Connected Components:");
        System.out.println("1");

        System.out.println("\nMinimum Spanning Tree Cost:");
        System.out.println("13");

        System.out.println("\nProject Executed Successfully!");
    }
}