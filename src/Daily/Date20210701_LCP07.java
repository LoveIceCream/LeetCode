import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/1 8:03 ����
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
     * С���� A �ں� ta ��С������洫��Ϣ��Ϸ����Ϸ�������£�
     * <p>
     * �� n ����ң�������ұ�ŷֱ�Ϊ 0 �� n-1������С���� A �ı��Ϊ 0
     * ÿ����Ҷ��й̶������ɸ��ɴ���Ϣ��������ң�Ҳ����û�У�������Ϣ�Ĺ�ϵ�ǵ���ģ����� A ������ B ����Ϣ���� B ������ A ����Ϣ����
     * ÿ����Ϣ������Ҫ���ݸ���һ���ˣ�����Ϣ���ظ�����ͬһ����
     * ����������� n���Լ��� [��ұ��,��Ӧ�ɴ�����ұ��] ��ϵ��ɵĶ�ά���� relation��������Ϣ��С A (��� 0 ) ���� k �ִ��ݵ����Ϊ n-1 ��С��鴦�ķ������������ܵ������ 0��
     * <p>
     * ��̬�滮 ���� ̰��
     * ����ͼ�����������ھ�
     * <p>
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/chuan-di-xin-xi
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     *
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        this.k = k;
        this.n = n;
        // ����һ�� ��Ϣ���ݾ������ x ���� y ������Ϣ ����ֵ ����Ϊ 1
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
        // ����һ�� ��Ϣ���ݾ������ x ���� y ������Ϣ ����ֵ ����Ϊ 1
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
        // �����������
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.offer(0);
        while (!bfsQueue.isEmpty() && deeps < k) {
            deeps++;
            // ��֤��һ�� ֻ ����һ�� deep
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
     * ��̬�滮��˼�� �Ǽ�¼��һ�ֵ����ݣ��Ӷ������ظ�����
     *
     * @param n
     * @param relation
     * @param k
     */
    public void dp(int n, int[][] relation, int k) {
        // ����һ�� ��Ϣ���ݾ������ x ���� y ������Ϣ ����ֵ ����Ϊ 1
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

        // �����¼����
        int[] methods = new int[n];
        // ����ĿǰΪֹ����0 ��ķ�����1��
        methods[0] = 1;

        // ����k�۵���
        for (int i = 0; i < k; i++) {
            int[] temp = new int[n];
            for (int j = 0; j < relation.length; j++) {
                int src = relation[j][0];
                int end = relation[j][1];
                temp[end] += methods[src];
            }
            // ����methods
            methods = temp;
        }
        System.out.println(methods[n - 1]);
//        return methods[n - 1];
    }

}
