import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/1 8:03 下午
 */
public class Date20210701_LCP07 {
    public Date20210701_LCP07() {
    }

    public static void main(String[] args) {
        Date20210701_LCP07 demo = new Date20210701_LCP07();
        int n = 5, k = 3;
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        demo.dp(n, relation, k);
    }

    int k = 0;
    int n = 0;
    int methods = 0;
    int[][] matrix = null;

    /**
     * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     * <p>
     * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
     * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
     * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
     * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     * <p>
     * 动态规划 或者 贪心
     * 有向图搜索，几跳邻居
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        this.k = k;
        this.n = n;
        // 构建一个 消息传递矩阵，如果 x 能向 y 传递消息 则将其值 设置为 1
        matrix = new int[n][n];
        for (int i = 0; i < relation.length; i++) {
            int start = relation[i][0];
            int end = relation[i][1];
            matrix[start][end] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        dfs(0, 0);
        return methods;
    }

    public void dfs(int src, int deeps) {
        if (deeps == k) {
            if (src == n - 1) {
                methods++;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (matrix[src][i] == 1) {
                dfs(i, deeps + 1);
            }
        }

    }

    public void bfs(int n, int[][] relation, int k) {
        // 构建一个 消息传递矩阵，如果 x 能向 y 传递消息 则将其值 设置为 1
        matrix = new int[n][n];
        for (int i = 0; i < relation.length; i++) {
            int start = relation[i][0];
            int end = relation[i][1];
            matrix[start][end] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        int deeps = 0;
        // 广度优先搜索
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.offer(0);
        while (!bfsQueue.isEmpty() && deeps < k) {
            deeps++;
            // 保证这一层 只 增加一次 deep
            for (int m = 0; m < bfsQueue.size(); m++) {
                int temp = bfsQueue.poll();
                for (int i = 0; i < n; i++) {
                    if (matrix[temp][i] == 1) {
                        bfsQueue.offer(i);
                    }
                }
            }
        }

        if (deeps == k) {
            while (!bfsQueue.isEmpty()) {
                int temp = bfsQueue.poll();
                if (temp == n - 1) {
                    methods++;
                }
            }
        }

        System.out.println(methods);
    }

    public void bfs2(int n, int[][] relation, int k) {
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] edge : relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }

        int steps = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        while (!queue.isEmpty() && steps < k) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                List<Integer> list = edges.get(index);
                for (int nextIndex : list) {
                    queue.offer(nextIndex);
                }
            }
        }

        int ways = 0;
        if (steps == k) {
            while (!queue.isEmpty()) {
                if (queue.poll() == n - 1) {
                    ways++;
                }
            }
        }
        System.out.println(ways);
    }

    /**
     * 动态规划的思想 是记录上一轮的内容，从而减少重复计算
     *
     * @param n
     * @param relation
     * @param k
     */
    public void dp(int n, int[][] relation, int k) {
        // 构建一个 消息传递矩阵，如果 x 能向 y 传递消息 则将其值 设置为 1
        matrix = new int[n][n];
        for (int i = 0; i < relation.length; i++) {
            int start = relation[i][0];
            int end = relation[i][1];
            matrix[start][end] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        // 结果记录数组
        int[] methods = new int[n];
        // 代表到目前为止，到0 点的方法有1种
        methods[0] = 1;

        // 经过k论迭代
        for (int i = 0; i < k; i++) {
            int[] temp = new int[n];
            for (int j = 0; j < relation.length; j++) {
                int src = relation[j][0];
                int end = relation[j][1];
                temp[end] += methods[src];
            }
            // 更新methods
            methods = temp;
        }
        System.out.println(methods[n - 1]);
//        return methods[n - 1];
    }

}
