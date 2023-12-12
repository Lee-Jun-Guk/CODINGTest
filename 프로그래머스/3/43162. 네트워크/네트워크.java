import java.util.Stack;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < computers.length; i++) {
            if (visited[i]) {
                continue;
            }
            stack.push(i);
            answer++;
            while(!stack.isEmpty()) {
                int cur = stack.pop();
                if (visited[cur]) {
                    continue;
                }
                visited[cur] = true;
                int[] computer = computers[cur];
                for (int j = 0; j < computer.length; j++) {
                    if (visited[j] || computer[j] == 0){
                        continue;
                    }
                    stack.push(j);
                }
            }
        }
        return answer;
    }
}
