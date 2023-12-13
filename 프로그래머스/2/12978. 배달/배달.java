import java.util.*;

class Solution {
    static final int INF = 500001;
    boolean[] visited;
    int[] costs;
    int[] path;

    public int[] findPath(int N, int[][] graph) {
        visited = new boolean[N];
        costs = new int[N];
        path = new int[N];

        Arrays.fill(costs, INF);

        int startNode = 0;
        costs[startNode] = 0;

        for (int i = 0; i < N - 1; i++) {
            int minNode = findMinNode(costs, visited);

            visited[minNode] = true;

            for (int[] edge : graph) {
                int from;
                int to;
                int cost;
                from = 0;
                to=0;
                cost=0;

                if(minNode == edge[0]-1) {
                    from = edge[0]-1;
                    to = edge[1]-1;
                    cost = edge[2];
                } else if(minNode == edge[1]-1) {
                    from = edge[1]-1;
                    to = edge[0]-1;
                    cost = edge[2];
                }


                if (!visited[to] && from == minNode && costs[from] != INF && costs[from] + cost < costs[to]) {
                    costs[to] = costs[from] + cost;
                    path[to] = from;
                }
            }
        }
        return costs;
    }
    private int findMinNode(int[] costs, boolean[] visited) {
        int minCost = INF;
        int minNode = 0;

        for (int i = 0; i < costs.length; i++) {
            if (!visited[i] && costs[i] < minCost) {
                minCost = (int)costs[i];
                minNode = i;
            }
        }

        return minNode;
    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] result = findPath(N, road);

        for(int i=0; i< result.length; i++) {
            if(result[i] <= K) {
                answer = answer + 1;
            }
        }

        return answer;
    }
}