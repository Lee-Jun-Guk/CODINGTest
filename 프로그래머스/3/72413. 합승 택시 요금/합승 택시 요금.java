class Solution {

    public int solution(int pointCnt, int startPoint, int destA, int destB, int[][] fares) {
        int[][] routeArr = initRouteArr(fares, fares.length, pointCnt);
        int index, cost, minCost = 20000000;
        boolean[] isCheck;
        for (int i = 0; i < pointCnt; i++) {
            isCheck = new boolean[pointCnt];
            isCheck[i] = true;
            for (int j = 0; j < pointCnt; j++) {
                index = nextIdx(routeArr, i, pointCnt, isCheck);
                cost = routeArr[i][index];
                for (int k = 0; k < pointCnt; k++) {
                    if (routeArr[k][index] + cost < routeArr[i][k]) {
                        routeArr[i][k] = routeArr[k][index] + cost;
                    }
                }
            }
        }
        for (int i = 0; i < pointCnt; i++) {
            cost = routeArr[startPoint - 1][i] + routeArr[i][destA - 1] + routeArr[i][destB - 1];
            if (cost < minCost) {
                minCost = cost;
            }
        }
        return minCost;
    }
    private int[][] initRouteArr(int[][] farse, int farseLen, int pointCnt) {
        int[][] routeArr = new int[pointCnt][pointCnt];
        for (int i = 0; i < farseLen; i++) {
            routeArr[farse[i][0] - 1][farse[i][1] - 1] = routeArr[farse[i][1] - 1][farse[i][0] - 1] = farse[i][2];
        }
        for (int j = 0; j < pointCnt; j++) {
            for (int k = 0; k < pointCnt; k++) {
                if (j != k && routeArr[j][k] == 0) {
                    routeArr[j][k] = 20000000;
                }
            }
        }
        return routeArr;
    }
    private int nextIdx(int[][] routeArr, int point, int runCnt, boolean[] isCheck) {
        int minVal = 20000000;
        int minIdx = runCnt - 1;
        for (int i = 0; i < runCnt; i++) {
            if (!isCheck[i] && minVal > routeArr[point][i]) {
                minVal = routeArr[point][i];
                minIdx = i;
            }
        }
        isCheck[minIdx] = true;
      return minIdx;
    }
}