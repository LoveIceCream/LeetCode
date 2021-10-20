import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhen
 * @version 1.0
 * @description 螺旋矩阵 顺时针旋转矩阵，输出 List<Integer>
 * @date 2021/6/30 11:00 下午
 */
public class Q54 {
    public Q54() {
    }

    public static void main(String[] args) {

//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix = {{3}, {2}};
        List<Integer> result = spiralOrder(matrix);
        System.out.println(result.toString());
    }

    /**
     * 基本思路，建立一个[m+2][n+2] 大小的矩阵数组标记访问情况
     * 遇到被访问过的点，顺时针转换方向
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        System.out.println(m);
        System.out.println(n);
        // 1 上 2 下 3 左 4 右
        int direction = 0;
        // 遍历过的节点的数量
        int visitedCount = 0;
        List<Integer> result = new ArrayList<>(m * n);
        int[][] flagMatrix = new int[m + 2][n + 2];

        m += 2;
        n += 2;
        // 对第0列 和n-1列设置值为1
        for (int i = 0; i < m; i++) {
            flagMatrix[i][0] = 1;
            flagMatrix[i][n - 1] = 1;
        }
        // 对第0行和 m-1 行设置值为1
        for (int i = 0; i < n; i++) {
            flagMatrix[0][i] = 1;
            flagMatrix[m - 1][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(flagMatrix[i][j] + "\t");
            }
            System.out.println("\t");
        }

        System.out.println("----------------");
        int x = 0;
        int y = 0;
        m -= 2;
        n -= 2;
        // 当访问过的节点数量小于传入矩阵的节点数量的时候，继续访问
        while (visitedCount < m * n) {
            visitedCount++;
            switch (direction) {
                case 0:
                    result.add(matrix[x][y]);
                    flagMatrix[x + 1][y + 1] = 1;
                    direction = 4;
                    if ( flagMatrix[x + 1][y + 2] == 1){
                        direction = 2;
                    }
                    break;
                case 1:
                    x--;
                    result.add(matrix[x][y]);
                    flagMatrix[x + 1][y + 1] = 1;
                    if (flagMatrix[x][y + 1] == 1) {
                        direction = 4;
                    }
                    break;
                case 2:
                    x++;
                    result.add(matrix[x][y]);
                    flagMatrix[x + 1][y + 1] = 1;
                    if (flagMatrix[x + 2][y + 1] == 1) {
                        direction = 3;
                    }
                    break;
                case 3:
                    y--;
                    result.add(matrix[x][y]);
                    flagMatrix[x + 1][y + 1] = 1;
                    if (flagMatrix[x + 1][y] == 1) {
                        direction = 1;
                    }
                    break;
                case 4:
                    y++;
                    result.add(matrix[x][y]);
                    flagMatrix[x + 1][y + 1] = 1;
                    if (flagMatrix[x + 1][y + 2] == 1) {
                        direction = 2;
                    }
                    break;
                default:
                    break;
            }

            for (int i = 0; i < m + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    System.out.print(flagMatrix[i][j] + "\t");
                }
                System.out.println("\t");
            }

            System.out.println(result.toString());

        }
        return result;
    }
}
