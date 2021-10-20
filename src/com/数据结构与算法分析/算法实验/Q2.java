package 算法实验;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * 动态规划法
 * 求解问题：最优二叉搜索树相关问题
 * （1）最优二叉搜索树的构造：
 * 给定关键字的排列次序，
 * 给出对应的查找成功和失败的概率，
 * 据此使用基本算法构造最优二叉搜索树；
 * （2）用改进的算法构造最优二叉搜索树，
 * 使构造的时间复杂度由O(n3)由变成O(n2).
 * 设置关键字个数分别为100，1000，5000，对于概率进行如下的设置：
 * （a）全部查找成功，并且关键字等概率；
 * （b）包含关键字查找成功和失败的情形，所有情况是等概率的（即约一半成功一半失败），从理论上分析其查找次数的期望和树高；
 * （c）查找成功的概率为80%，不成功为20%，
 * 在成功的情况下10%的元素的概率为成功的60%（全部概率的0.48），
 * 其余90%的元素概率为成功时的40%（全部概率的0.32），
 * 设定查找成功最高概率元素的概率小于10/n，
 * 并注意元素查找成功概率较高时，其失败时的概率相比其他失败情况的概率也要高.
 * <p>
 * 对元素的分布进行一些假定，构建最优二叉搜索树，统计不同情况下树的高度和查找次数的期望（随机数使用不同的种子，多次执行算法后求平均）。
 * 与查找成功的概率为90%，不成功为10%的情形比较。
 * <p>
 * （3）讨论少量查找概率发生小的变化时最佳搜索树的调整的近似算法。
 * <p>
 * https://blog.csdn.net/z84616995z/article/details/38011391
 *
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/12 3:11 下午
 */
public class Q2 {

    public static int[] N_length = {100, 1000, 5000};

    public static int[] SEEDS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

    public Q2() {
    }

    public class eAndRoot {
        double[][] e;
        int[][] root;
        int h;

        eAndRoot(int n) {
            e = new double[n + 2][];
            for (int i = 0; i < n + 2; i++) {
                e[i] = new double[n + 1];
            }

            root = new int[n + 1][];
            for (int i = 0; i < n + 1; i++) {
                root[i] = new int[n + 1];
            }

            h = 0;
        }

    }

    public static void main(String[] args) {
        Q2 q2 = new Q2();
//
////        q2.run_test_15_5_2();;
//
//        System.out.println("执行第一个小问题的代码");
//        q2.run_1();
//
//        System.out.println("执行第二个小问题的代码");
//        for (int i = 0; i < N_length.length; i++) {
//            System.out.println("第" + (i + 1) + "轮,当前关键词个数为：" + N_length[i]);
//            q2.run_2_a(N_length[i]);
//
//            q2.run_2_b(N_length[i]);
//
//            q2.run_2_c(N_length[i], 1);
//        }
//
//        System.out.println("执行 统计不同情况下树的高度和查找次数的期望，关键词数量相同，随机种子不同 ");
//        double ave_h = 0.00;
//        double ave_e = 0.00;
//        for (int i = 0; i < SEEDS.length; i++) {
//            eAndRoot e_oot = q2.run_3(1000, SEEDS[i]);
//            ave_h += e_oot.h;
//            ave_e += e_oot.e[1][1000];
//            System.out.println("seed = " + SEEDS[i]);
//            System.out.println("最优二叉搜索树代价:" + e_oot.e[1][1000]);
//            System.out.println("最优二叉搜索树树高:" + e_oot.h);
//        }
//        ave_h = ave_h / SEEDS.length;
//        ave_e = ave_e / SEEDS.length;
//
//        System.out.println("经统计，平均树高为" + ave_h);
//        System.out.println("经统计，平均代价期望为" + ave_e);
//
//
//        System.out.println("执行 统计不同情况下树的高度和查找次数的期望 ，关键词数量相同，随机种子不同");
        double ave_h_4 = 0.00;
        double ave_e_4 = 0.00;
//        for (int i = 0; i < SEEDS.length; i++) {
//            eAndRoot e_oot = q2.run_4(1000, SEEDS[i]);
//            ave_h_4 += e_oot.h;
//            ave_e_4 += e_oot.e[1][1000];
//            System.out.println("seed = " + SEEDS[i]);
//            System.out.println("最优二叉搜索树代价:" + e_oot.e[1][1000]);
//            System.out.println("最优二叉搜索树树高:" + e_oot.h);
//        }
//        ave_h_4 = ave_h_4 / SEEDS.length;
//        ave_e_4 = ave_e_4 / SEEDS.length;
//
//        System.out.println("经统计，平均树高为" + ave_h_4);
//        System.out.println("经统计，平均代价期望为" + ave_e_4);
//


        System.out.println("执行 统计不同情况下树的高度和查找次数的期望 ，关键词数量不同，随机种子相同");
         ave_h_4 = 0.00;
         ave_e_4 = 0.00;
        for (int i = 0; i < N_length.length; i++) {
            eAndRoot e_oot = q2.run_3(N_length[i], 1);
            ave_h_4 += e_oot.h;
            ave_e_4 += e_oot.e[1][N_length[i]];
            System.out.println("seed = " + 1);
            System.out.println("最优二叉搜索树代价:" + e_oot.e[1][N_length[i]]);
            System.out.println("最优二叉搜索树树高:" + e_oot.h);
        }
        ave_h_4 = ave_h_4 / N_length.length;
        ave_e_4 = ave_e_4 / N_length.length;

        System.out.println("经统计，平均树高为" + ave_h_4);
        System.out.println("经统计，平均代价期望为" + ave_e_4);



        System.out.println("执行 统计不同情况下树的高度和查找次数的期望  ，关键词数量不同，随机种子相同");
         ave_h_4 = 0.00;
         ave_e_4 = 0.00;
        for (int i = 0; i < N_length.length; i++) {
            eAndRoot e_oot = q2.run_4(N_length[i], 1);
            ave_h_4 += e_oot.h;
            ave_e_4 += e_oot.e[1][N_length[i]];
            System.out.println("seed = " + 1);
            System.out.println("最优二叉搜索树代价:" + e_oot.e[1][N_length[i]]);
            System.out.println("最优二叉搜索树树高:" + e_oot.h);
        }
        ave_h_4 = ave_h_4 / N_length.length;
        ave_e_4 = ave_e_4 / N_length.length;

        System.out.println("经统计，平均树高为" + ave_h_4);
        System.out.println("经统计，平均代价期望为" + ave_e_4);

    }


    public void run_test_15_5_2() {
        int n = 5;

        // 给定概率（排序）的数组 可以查找到 注意数组p的第一个位置不放任何数据。
//        double[] p = {0, 0.15, 0.10, 0.05, 0.10, 0.20};
        // 查找失败的
//        double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
        // 算法导论 15.5-2题数据
        double[] p = {0, 0.04, 0.06, 0.08, 0.02, 0.10, 0.12, 0.14};
        double[] q = {0.06, 0.06, 0.06, 0.06, 0.05, 0.05, 0.05, 0.05};
        n = p.length - 1;

        Q2 q2 = new Q2();
        eAndRoot e_root_basic = q2.optimalBSTBasic(p, q, n);
        System.out.println("最优二叉搜索树结构:");
        q2.PRINT_OPTIMAL_BST(e_root_basic, 1, n, n, 1);
        System.out.println("最优二叉搜索树代价:" + e_root_basic.e[1][n]);
        System.out.println("最优二叉搜索树树高:" + e_root_basic.h);

        eAndRoot e_root_improve = q2.optimalBSTImprove(p, q, n);
        System.out.println("最优二叉搜索树结构:");
        q2.PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("最优二叉搜索树代价:" + e_root_improve.e[1][n]);
        System.out.println("最优二叉搜索树树高:" + e_root_improve.h);

    }

    public void run_1() {
        int n = 5;
        // 算法导论 15.5-2题数据
        // 给定概率（排序）的数组 可以查找到 注意数组p的第一个位置不放任何数据。
        double[] p = {0, 0.04, 0.06, 0.08, 0.02, 0.10, 0.12, 0.14};
        // 查找失败的
        double[] q = {0.06, 0.06, 0.06, 0.06, 0.05, 0.05, 0.05, 0.05};
        n = p.length - 1;

        eAndRoot e_root_basic = optimalBSTBasic(p, q, n);
        System.out.println("最优二叉搜索树结构:");
        PRINT_OPTIMAL_BST(e_root_basic, 1, n, n, 1);
        System.out.println("最优二叉搜索树代价:" + e_root_basic.e[1][n]);
        System.out.println("最优二叉搜索树树高:" + e_root_basic.h);
    }

    public void run_2_a(int n) {
        System.out.println("---------------------- start ---------------------------");
        System.out.println(" n = " + n + "; case A :");
        double[] p = new double[n + 1];
        for (int i = 1; i < p.length; i++) {
            p[i] = 1.00 / n;
        }
        double[] q = new double[n + 1];
        eAndRoot e_root_improve = optimalBSTBasic(p, q, n);
        System.out.println("最优二叉搜索树结构:");
        PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("最优二叉搜索树代价:" + e_root_improve.e[1][n]);
        System.out.println("最优二叉搜索树树高:" + e_root_improve.h);
        System.out.println("---------------------- end ---------------------------");
    }

    public void run_2_b(int n) {

        System.out.println("---------------------- start ---------------------------");


        System.out.println(" n = " + n + "; case B :");
        double[] p = new double[n + 1];
        for (int i = 1; i < p.length; i++) {
            p[i] = 1.00 / n / 2;
        }
        double[] q = new double[n + 1];
        for (int i = 1; i < p.length; i++) {
            q[i] = 1.00 / n / 2;
        }
        eAndRoot e_root_improve = optimalBSTImprove(p, q, n);
        System.out.println("最优二叉搜索树结构:");
        PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("最优二叉搜索树代价:" + e_root_improve.e[1][n]);
        System.out.println("最优二叉搜索树树高:" + e_root_improve.h);
        System.out.println("---------------------- end ---------------------------");
    }

    public void run_2_c(int n, int seed) {
        System.out.println("---------------------- start ---------------------------");
//  （c）查找成功的概率为80%，不成功为20%，
//  在成功的情况下10%的元素的概率为成功的60%（全部概率的0.48），
        //      对于100个关键词而言 有10个关键词，其查找成功概率p的和为0.48
        //      单个最高为0.1
        //      90个关键词 其成功查找的概率和为 0.32
//  其余90%的元素概率为成功时的40%（全部概率的0.32），
//  设定查找成功最高概率元素的概率小于10/n，
//  并注意元素查找成功概率较高时，其失败时的概率相比其他失败情况的概率也要高. 对于pi而言，其概率高时，qi和q（i-1） 的概率也相应增加

//       对于 p 数组，成功的概率，先初始化为 0.32/(n*0.9) 剩余（n*0.1)个关键字，随机获取位置，不平等分 0.48 - 0.32/(n*0.9)*(n*0.1)
        // 每随机到一个位置的 对q相应的 i 和i-1 位置的概率进行增加，
        // 增加多少呢 按比例增加吧
        // 对于q数组 失败的概率同样初始化为  0.08/((n+1)*0.9) 然后根据q的i位置增加的概率在其剩余概率部分中的占比，
        // 按照同样的比例 从 q的剩余概率中 获取数值，平分给i 和 i-1
        System.out.println(" n = " + n + "; case C :");
        // 成功查找
        double[] p = new double[n + 1]; // total 0.8 ; 0.1*n 0.48
        for (int i = 1; i < n + 1; i++) {
            p[i] = 0.32 / (n * 0.9);
        }
        // 伪关键字
        double[] q = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            q[i] = 0.08 / ((n + 1) * 0.9);
        }
        // 随机开始了
        randomProb(p, q, 0.48 - 0.32 / (n * 0.9) * (n * 0.1), 0.12 - 0.08 / ((n + 1) * 0.9) * (n + 1) * 0.1, seed);
        eAndRoot e_root_improve = optimalBSTImprove(p, q, n);
        System.out.println("最优二叉搜索树结构:");
        PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("最优二叉搜索树代价:" + e_root_improve.e[1][n]);
        System.out.println("最优二叉搜索树树高:" + e_root_improve.h);
        System.out.println("---------------------- end ---------------------------");
    }

    public eAndRoot run_3(int n, int seed) {
        System.out.println("---------------------- start ---------------------------");
        System.out.println(" n = " + n + ";  run_3 采用 第二题case c 的相同的代码 :");
        // 成功查找
        double[] p = new double[n + 1]; // total 0.8 ; 0.1*n 0.48
        for (int i = 1; i < n + 1; i++) {
            p[i] = 0.32 / (n * 0.9);
        }
        // 伪关键字
        double[] q = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            q[i] = 0.08 / ((n + 1) * 0.9);
        }
        // 随机开始了
        randomProb(p, q, 0.48 - 0.32 / (n * 0.9) * (n * 0.1), 0.12 - 0.08 / ((n + 1) * 0.9) * (n + 1) * 0.1, seed);
        eAndRoot e_root_improve = optimalBSTImprove(p, q, n);
        getTreeDeep(e_root_improve, 1, n, n, 1);
        return e_root_improve;
    }


    public eAndRoot run_4(int n, int seed) {
        System.out.println("---------------------- start ---------------------------");
        System.out.println(" n = " + n + ";  run_4 采用 第二题case c 的类似的代码，其成功概率改为90 :");
        // 成功查找
        double[] p = new double[n + 1];
        for (int i = 1; i < n + 1; i++) {
            p[i] = 0.36 / (n * 0.9);
        }
        // 伪关键字
        double[] q = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            q[i] = 0.04 / ((n + 1) * 0.9);
        }
        // 随机开始了
        randomProb(p, q, 0.54 - 0.36 / (n * 0.9) * (n * 0.1), 0.06 - 0.04 / ((n + 1) * 0.9) * (n + 1) * 0.1, seed);
        eAndRoot e_root_improve = optimalBSTImprove(p, q, n);
        getTreeDeep(e_root_improve, 1, n, n, 1);
        return e_root_improve;
    }

    /**
     * 通过给定的 已排序的关键字数组 构建bst
     *
     * @param sortedKeyWords
     */
    public static void bst_baaic(int[] sortedKeyWords, int[] searchKeyWords) {

    }

    //---------UTIL-----------------

    /**
     * 获取随机生成的升序排列的整型数组
     *
     * @param length
     * @return
     */
    public static ArrayList<Integer> getRandomSortNums(int seed, int length) {
        ArrayList<Integer> randomNumsArrList = new ArrayList<>();

        Random random = new Random(seed);
        for (int i = 0; i < length; i++) {
            randomNumsArrList.add(random.nextInt(length));
        }
        randomNumsArrList.sort(Comparator.naturalOrder());
        return randomNumsArrList;
    }


    public void PRINT_OPTIMAL_BST(eAndRoot e_root, int i, int j, int n, int deep) {
        int Root = e_root.root[i][j];//当前根结点
        if (i == 1 && j == n) {
            deep++;
            System.out.println("k" + Root + "为根");
        }
        if (e_root.h < deep) {
            e_root.h = deep;
        }
        if (i == Root) {//如果左子树是叶子
            System.out.println("d" + (i - 1) + "为k" + Root + "的左子树");
        } else {
            System.out.println("k" + e_root.root[i][Root - 1] + "为k" + Root + "的左子树");
            PRINT_OPTIMAL_BST(e_root, i, Root - 1, n, deep + 1);
        }
        if (j == Root) {//如果右子树是叶子
            System.out.println("d" + j + "为k" + Root + "的右子树");
        } else {
            System.out.println("k" + e_root.root[Root + 1][j] + "为k" + Root + "的右子树");
            PRINT_OPTIMAL_BST(e_root, Root + 1, j, n, deep + 1);
        }
    }

    public void getTreeDeep(eAndRoot e_root, int i, int j, int n, int deep) {
        int Root = e_root.root[i][j];//当前根结点
        if (i == 1 && j == n) {
            deep++;
        }
        if (e_root.h < deep) {
            e_root.h = deep;
        }
        if (i != Root) {//如果左子树是叶子
            getTreeDeep(e_root, i, Root - 1, n, deep + 1);
        }
        if (j != Root) {//如果右子树是叶子
            getTreeDeep(e_root, Root + 1, j, n, deep + 1);
        }
    }


    public void randomProb(double[] p, double[] q, double p_prob, double q_prob, int seed) {
        Random random = new Random(seed);
        int n = p.length - 1;
        double maxProb = 10.00 / n;
        double remindProb_p = p_prob;
        double remindProb_q = q_prob;
        n = n / 10;
        for (int i = 0; i < n && remindProb_p > 0; i++) {
            // 随机获取位置
            int index = random.nextInt(n) + 1;
            // 随机获取 概率大小
            if (i == n - 1) {
                // 最后一次遍历 将剩余的直接分了 需要前边保证最好一次不会超 10/n
                p[index] += remindProb_p;
                q[index] += remindProb_q / 2;
                q[index - 1] += remindProb_q / 2;
                break;
            }
            double tempProb = 0.00;
            // 当 tempProb 小于一个阈值的时候 重新随机，知道第五次或者找到了大的
            int random_times_flag = 0;
            // 通过minProb 保证最后一次剩余的概率不会超界限
            double minProb = (p_prob - (10 / (p.length - 1))) / (n - 1);
            while (tempProb <= minProb) {
                if (random_times_flag >= 5) {
                    tempProb = minProb;
                    break;
                }
                random_times_flag++;
                tempProb = p_prob * random.nextInt(n) / n;
            }

            if (tempProb > remindProb_p) {
                tempProb = remindProb_p;
            }
            // check 应该check 全部 是否超过了 10/n
            if (p[index] + tempProb > maxProb) {
                tempProb = maxProb - p[index];
            }
            // p 数组赋值
            p[index] += tempProb;
            // 计算剩余概率
            remindProb_p -= tempProb;
            // 更新 q
            if (remindProb_p == 0) {
                q[index] += remindProb_q / 2;
                q[index - 1] += remindProb_q / 2;
                remindProb_q = 0;
            } else {
                // 计算 给q[index] 和 q[index-1] 赋值的概率
                double tempProbQ = q_prob * tempProb / p_prob;
                remindProb_q -= tempProbQ;
                q[index] += tempProbQ / 2;
                q[index - 1] += tempProbQ / 2;
            }

        }
    }


    //---------BASIC-----------------

    /**
     * 问题1 basic
     */
    public eAndRoot optimalBSTBasic(double[] p, double[] q, int n) {
        eAndRoot e_root = new eAndRoot(n);
        double[][] w = new double[n + 2][n + 1];
        for (int i = 1; i <= n + 1; i++) {
            e_root.e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                e_root.e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                for (int r = i; r <= j; r++) {
                    double t = e_root.e[i][r - 1] + e_root.e[r + 1][j] + w[i][j];
                    if (t < e_root.e[i][j]) {
                        e_root.e[i][j] = t;
                        e_root.root[i][j] = r;
                    }
                }
            }
        }
        return e_root;
    }

    //---------IMPROVE-----------------

    /**
     * 问题2 improve
     */
    public eAndRoot optimalBSTImprove(double[] p, double[] q, int n) {
        eAndRoot e_root = new eAndRoot(n);
        double[][] w = new double[n + 2][n + 1];
        for (int i = 1; i <= n + 1; i++) {
            e_root.e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                e_root.e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                if (i == j)//当最优二叉搜索子树只有一个元素时
                {
                    e_root.root[i][j] = j;
                    e_root.e[i][j] = e_root.e[i][j - 1] + e_root.e[j + 1][j] + w[i][j];
                } else//不止一个元素时
                {//我们不用从i起一直检查到j,只需检查区间T.root[i][j-1]到T.root[i+1][j](根据题目给的不等式)
                    for (int r = e_root.root[i][j - 1]; r <= e_root.root[i + 1][j]; r++) {
                        double t = e_root.e[i][r - 1] + e_root.e[r + 1][j] + w[i][j];
                        if (t < e_root.e[i][j]) {
                            e_root.e[i][j] = t;
                            e_root.root[i][j] = r;
                        }
                    }
                }
            }
        }
        return e_root;
    }
}



