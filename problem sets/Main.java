import java.util.*;

class Main {
    static class Pair {
        double first, second;

        Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Edge implements Comparable<Edge> {
        int n1, n2;
        double d;

        Edge(int n1, int n2, double d) {
            this.n1 = n1;
            this.n2 = n2;
            this.d = d;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.d, other.d);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        // Read in pairs
        Pair[] v = new Pair[n];
        for (int i = 0; i < n; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            v[i] = new Pair(x, y);
        }

        // Build disjoint set
        int[] d = new int[n];
        Arrays.fill(d, -1);

        // Join the first m together
        for (int i = 1; i < m; i++) {
            d[i] = 0;
        }

        for (int i = 0; i < k; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();
            join(d, n1 - 1, n2 - 1);
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Edge e = new Edge(i, j, dist(v[i], v[j]));
                edges.add(e);
            }
        }

        Collections.sort(edges);

        double total = 0;
        for (Edge edge : edges) {
            // if not joined, join
            if (find(d, edge.n1) != find(d, edge.n2)) {
                join(d, edge.n1, edge.n2);
                total += edge.d;
            }
        }

        System.out.printf("%.6f\n", total);
    }

    static int find(int[] d, int a) {
        if (d[a] == -1) return a;
        return d[a] = find(d, d[a]);
    }

    static void join(int[] d, int a, int b) {
        a = find(d, a);
        b = find(d, b);
        if (a == b) return;
        d[a] = b;
    }

    static double dist(Pair p1, Pair p2) {
        double d1 = Math.pow(p1.first - p2.first, 2);
        double d2 = Math.pow(p1.second - p2.second, 2);
        return Math.sqrt(d1 + d2);
    }
}
