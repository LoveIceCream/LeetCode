package 算法实验;

import java.io.*;

/**
 * 搜索算法
 * 求解问题：世界名画陈列馆问题
 * 世界名画陈列馆由m× n 个排列成矩形阵列的陈列室组成。为了防止名画被盗，
 * 需要在陈列室中设置警卫机器人哨位。每个警卫机器人除了监视它所在的陈列室外，
 * 还可以监视与它所在的陈列室相邻的上、下、左、右4 个陈列室。试设计一个安排警卫机器人哨位的算法，
 * 使得名画陈列馆中每一个陈列室都在警卫机器人的监视之下，且所用的警卫机器人数最少。
 * 设计一个算法，计算警卫机器人的最佳哨位安排，使得名画陈列馆中每一个陈列室都在警卫机器人的监视之下，且所用的警卫机器人数最少。
 * <p>
 * 输入：由文件input.txt输入数据,文件的第一行有2 个正整数m 和n (1≤m,n≤20)。
 * 输出：将计算出的警卫机器人数及其最佳哨位安排输出到output.txt。
 * 文件中第一行是警卫机器人数；接下来的m 行中每行n 个数，0 表示无哨位，1 表示哨位。
 *
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/12 3:11 下午
 */
public class Q3 {
    // 上下左右中 五个方位
    int[][] directionArr = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 行
    int m;
    // 列
    int n;
    // 最少机器人个数 -- 最优值
    int minCount;
    // 当前已放置机器人数量
    int tempCount;
    // 限制条件 1
    int limit_all_count;
    // 限制条件 2
    int limit_redundancy;
    // 记录被监控状态
    int[][] spyOnStatusArray;
    // 被监控节点的数量
    int spyOnCount;
    // 监控机器人位置 - 最终结果
    int[][] robotLocationArray;
    // 监控机器人位置 - 中间记录
    int[][] tempLocationArray;

    /**
     * 从文件获取 m n
     *
     * @param filePath
     * @return
     */
    public int[] getFromFile(String filePath) {
        int[] inputs = new int[2];

        try {
            File file = new File(filePath);
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] lineTxts = lineTxt.split(" ");
                    inputs[0] = Integer.parseInt(lineTxts[0].trim());
                    inputs[1] = Integer.parseInt(lineTxts[1].trim());
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return inputs;
    }

    /**
     * 将结果输出到文件中
     *
     * @param filePath
     */
    public void out2File(String filePath) {

        try {
            File writeName = new File(filePath);
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(minCount + "\r\n");
                System.out.println(minCount);
                for (int i = 1; i <= m; i++) {
                    for (int j = 1; j <= n; j++) {
                        System.out.print(robotLocationArray[i][j] + "\t");
                        out.write(robotLocationArray[i][j] + "\t");
                    }
                    out.write("\r\n");
                    System.out.println("\n");
                }
                // 把缓存区内容压入文件
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * 扩充边界 m+1 n+1 边界值可以统一处理
     * 剪枝策略
     * 1 放置的机器人个数不会超过mn/3+1个
     * 2 如果(i,j)已被监视,则不需要在此处放置机器人,直接跳过即可.
     * 3 当且仅当(i,j)在网格右下角或者(I,j+1)未被监视时才考虑放置在(i,j)的情况.
     * 4 当且仅当(i,j+1)或(i,j+2)未被监视时才考虑放置在(i,j+1)的情况.
     * 5 当i=n时,不考虑放置在(i+1,j)的情况.
     * 6 记录已经监视的格点数,(当前最优值减去当前已放置个数)*5如果小于未监视的格点数,则一定达不到比当前最优值更好的情况,剪去.
     * 7 类似于(6),考虑更紧的情况,并非每个机器人都能独立监视5个格点,至少会有m/4+5的冗余,
     * 这个剪枝仅适用于i<n-1的情况,因为最后两行由于最优值和已放置个数非常接近,
     * 总是达不到这个值   ？
     *
     * @return
     */
    public void search() {

        minCount = m * n / 3 + 2;
        tempCount = 0;

        limit_all_count = m * n;
        limit_redundancy = m * n + n / 4 + 5;
        spyOnCount = 0;
        spyOnStatusArray = new int[m + 2][n + 2];
        robotLocationArray = new int[m + 2][n + 2];
        tempLocationArray = new int[m + 2][n + 2];

        // 初始化边界(四周，最外层) 监控状态
        for (int i = 0; i <= m + 1; i++) {
            spyOnStatusArray[i][0] = 1;
            spyOnStatusArray[i][n + 1] = 1;
        }
        for (int i = 0; i <= n + 1; ++i) {
            spyOnStatusArray[0][i] = 1;
            spyOnStatusArray[m + 1][i] = 1;
        }
        search(1, 1);

    }

    /**
     * 搜索 剪枝
     *
     * @param point_m
     * @param point_n
     */
    public void search(int point_m, int point_n) {

        // 剪枝策略 1
        if (tempCount >= minCount) {
            return;
        }

        // 剪枝策略 2
        while (point_m <= m && spyOnStatusArray[point_m][point_n] >= 1) //已放置的不再被搜索
        {
            point_n++;
            if (point_n > n) {
                point_m++;
                point_n = 1;
            }
        }

        //更新答案 逐行遍历 遍历完了
        if (point_m > m) {
            // 将当前解 赋值给最优解
            minCount = tempCount;
            // 当前机器人位置数组 赋值给 最终结果数组
            copyValue(robotLocationArray, tempLocationArray);
//            System.out.println("........................");
//            System.out.println(minCount);
//            for (int i = 1; i <= m; i++) {
//                for (int j = 1; j <= n; j++) {
//                    System.out.println(robotLocationArray[i][j] + "\t");
//                }
//                System.out.println("\n");
//            }
//            System.out.println("........................");
            return;
        }

        // 剪枝策略 6 如果 （minCount - tempCount) * 5 小于未监控的数量，则剪枝 转换为 该值加上被监控的数量，和小于总数
        int reach = spyOnCount + (minCount - tempCount) * 5;
        if (reach <= limit_all_count) {
            return;
        }
        // 剪枝策略 7
        if (point_m < m - 1 && reach <= limit_redundancy) {
            return;
        }

        // 最右下角的点 或者当前点 右 的点未被监控
        if ((point_m == m && point_n == n)
                || spyOnStatusArray[point_m][point_n + 1] == 0) {
            calculate(point_m, point_n, point_m, point_n);
        }
        if (point_m < m) {
            calculate(point_m + 1, point_n, point_m, point_n);
        }
        if (point_n < n && (spyOnStatusArray[point_m][point_n + 1] == 0
                || spyOnStatusArray[point_m][point_n + 2] == 0)
        ) {
            calculate(point_m, point_n + 1, point_m, point_n);
        }
    }

    /**
     * 搜索 计算
     * @param x
     * @param y
     * @param i
     * @param j
     */
    public void calculate(int x, int y, int i, int j) {
        // (x,y) 点处放置机器人
        tempLocationArray[x][y] = 1;
        // 临时机器人数量加一
        tempCount++;
        // 上下左右中 任意位置的监控状态值加一后如果为1 ，已经被监控的位置的数量 加一
        for (int k = 0; k < 5; k++) {
            int tempValue = ++spyOnStatusArray[x + directionArr[k][0]][y + directionArr[k][1]];
            if (tempValue == 1) {
                spyOnCount++;
            }
        }
        // 向 右搜索
        search(i, j + 1);
        // (x,y) 点处取消机器人
        tempLocationArray[x][y] = 0;
        // 临时机器人数量减一
        tempCount--;
        // 上下左右中，任意位置的监控状态值减一后如果为0 已经被监控的位置数量 减一
        for (int k = 0; k < 5; k++) {
            int tempValue = --spyOnStatusArray[x + directionArr[k][0]][y + directionArr[k][1]];
            if (tempValue == 0) {
                spyOnCount--;
            }
        }
    }

    /**
     * 拷贝数组的值
     * @param newArr
     * @param oldArr
     */
    public void copyValue(int[][] newArr, int[][] oldArr) {
        for (int i = 0; i < oldArr.length; i++) {
            for (int j = 0; j < oldArr[i].length; j++) {
                newArr[i][j] = oldArr[i][j];
            }
        }
    }

    public static void main(String[] args) {
        String inputPath = "src/com/数据结构与算法分析/算法实验/input.txt";
        String outputPath = "src/com/数据结构与算法分析/算法实验/output.txt";
        Q3 q3 = new Q3();
        int[] inputs = q3.getFromFile(inputPath);
        System.out.println(inputs[0] + "," + inputs[1]);
        q3.m = inputs[0];
        q3.n = inputs[1];
        q3.search();
        q3.out2File(outputPath);
    }
}
