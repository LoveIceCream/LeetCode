package �㷨ʵ��;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * ��̬�滮��
 * ������⣺���Ŷ����������������
 * ��1�����Ŷ����������Ĺ��죺
 * �����ؼ��ֵ����д���
 * ������Ӧ�Ĳ��ҳɹ���ʧ�ܵĸ��ʣ�
 * �ݴ�ʹ�û����㷨�������Ŷ�����������
 * ��2���øĽ����㷨�������Ŷ�����������
 * ʹ�����ʱ�临�Ӷ���O(n3)�ɱ��O(n2).
 * ���ùؼ��ָ����ֱ�Ϊ100��1000��5000�����ڸ��ʽ������µ����ã�
 * ��a��ȫ�����ҳɹ������ҹؼ��ֵȸ��ʣ�
 * ��b�������ؼ��ֲ��ҳɹ���ʧ�ܵ����Σ���������ǵȸ��ʵģ���Լһ��ɹ�һ��ʧ�ܣ����������Ϸ�������Ҵ��������������ߣ�
 * ��c�����ҳɹ��ĸ���Ϊ80%�����ɹ�Ϊ20%��
 * �ڳɹ��������10%��Ԫ�صĸ���Ϊ�ɹ���60%��ȫ�����ʵ�0.48����
 * ����90%��Ԫ�ظ���Ϊ�ɹ�ʱ��40%��ȫ�����ʵ�0.32����
 * �趨���ҳɹ���߸���Ԫ�صĸ���С��10/n��
 * ��ע��Ԫ�ز��ҳɹ����ʽϸ�ʱ����ʧ��ʱ�ĸ����������ʧ������ĸ���ҲҪ��.
 * <p>
 * ��Ԫ�صķֲ�����һЩ�ٶ����������Ŷ�����������ͳ�Ʋ�ͬ��������ĸ߶ȺͲ��Ҵ����������������ʹ�ò�ͬ�����ӣ����ִ���㷨����ƽ������
 * ����ҳɹ��ĸ���Ϊ90%�����ɹ�Ϊ10%�����αȽϡ�
 * <p>
 * ��3�������������Ҹ��ʷ���С�ı仯ʱ����������ĵ����Ľ����㷨��
 * <p>
 * https://blog.csdn.net/z84616995z/article/details/38011391
 *
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/12 3:11 ����
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
//        System.out.println("ִ�е�һ��С����Ĵ���");
//        q2.run_1();
//
//        System.out.println("ִ�еڶ���С����Ĵ���");
//        for (int i = 0; i < N_length.length; i++) {
//            System.out.println("��" + (i + 1) + "��,��ǰ�ؼ��ʸ���Ϊ��" + N_length[i]);
//            q2.run_2_a(N_length[i]);
//
//            q2.run_2_b(N_length[i]);
//
//            q2.run_2_c(N_length[i], 1);
//        }
//
//        System.out.println("ִ�� ͳ�Ʋ�ͬ��������ĸ߶ȺͲ��Ҵ������������ؼ���������ͬ��������Ӳ�ͬ ");
//        double ave_h = 0.00;
//        double ave_e = 0.00;
//        for (int i = 0; i < SEEDS.length; i++) {
//            eAndRoot e_oot = q2.run_3(1000, SEEDS[i]);
//            ave_h += e_oot.h;
//            ave_e += e_oot.e[1][1000];
//            System.out.println("seed = " + SEEDS[i]);
//            System.out.println("���Ŷ�������������:" + e_oot.e[1][1000]);
//            System.out.println("���Ŷ�������������:" + e_oot.h);
//        }
//        ave_h = ave_h / SEEDS.length;
//        ave_e = ave_e / SEEDS.length;
//
//        System.out.println("��ͳ�ƣ�ƽ������Ϊ" + ave_h);
//        System.out.println("��ͳ�ƣ�ƽ����������Ϊ" + ave_e);
//
//
//        System.out.println("ִ�� ͳ�Ʋ�ͬ��������ĸ߶ȺͲ��Ҵ��������� ���ؼ���������ͬ��������Ӳ�ͬ");
        double ave_h_4 = 0.00;
        double ave_e_4 = 0.00;
//        for (int i = 0; i < SEEDS.length; i++) {
//            eAndRoot e_oot = q2.run_4(1000, SEEDS[i]);
//            ave_h_4 += e_oot.h;
//            ave_e_4 += e_oot.e[1][1000];
//            System.out.println("seed = " + SEEDS[i]);
//            System.out.println("���Ŷ�������������:" + e_oot.e[1][1000]);
//            System.out.println("���Ŷ�������������:" + e_oot.h);
//        }
//        ave_h_4 = ave_h_4 / SEEDS.length;
//        ave_e_4 = ave_e_4 / SEEDS.length;
//
//        System.out.println("��ͳ�ƣ�ƽ������Ϊ" + ave_h_4);
//        System.out.println("��ͳ�ƣ�ƽ����������Ϊ" + ave_e_4);
//


        System.out.println("ִ�� ͳ�Ʋ�ͬ��������ĸ߶ȺͲ��Ҵ��������� ���ؼ���������ͬ�����������ͬ");
         ave_h_4 = 0.00;
         ave_e_4 = 0.00;
        for (int i = 0; i < N_length.length; i++) {
            eAndRoot e_oot = q2.run_3(N_length[i], 1);
            ave_h_4 += e_oot.h;
            ave_e_4 += e_oot.e[1][N_length[i]];
            System.out.println("seed = " + 1);
            System.out.println("���Ŷ�������������:" + e_oot.e[1][N_length[i]]);
            System.out.println("���Ŷ�������������:" + e_oot.h);
        }
        ave_h_4 = ave_h_4 / N_length.length;
        ave_e_4 = ave_e_4 / N_length.length;

        System.out.println("��ͳ�ƣ�ƽ������Ϊ" + ave_h_4);
        System.out.println("��ͳ�ƣ�ƽ����������Ϊ" + ave_e_4);



        System.out.println("ִ�� ͳ�Ʋ�ͬ��������ĸ߶ȺͲ��Ҵ���������  ���ؼ���������ͬ�����������ͬ");
         ave_h_4 = 0.00;
         ave_e_4 = 0.00;
        for (int i = 0; i < N_length.length; i++) {
            eAndRoot e_oot = q2.run_4(N_length[i], 1);
            ave_h_4 += e_oot.h;
            ave_e_4 += e_oot.e[1][N_length[i]];
            System.out.println("seed = " + 1);
            System.out.println("���Ŷ�������������:" + e_oot.e[1][N_length[i]]);
            System.out.println("���Ŷ�������������:" + e_oot.h);
        }
        ave_h_4 = ave_h_4 / N_length.length;
        ave_e_4 = ave_e_4 / N_length.length;

        System.out.println("��ͳ�ƣ�ƽ������Ϊ" + ave_h_4);
        System.out.println("��ͳ�ƣ�ƽ����������Ϊ" + ave_e_4);

    }


    public void run_test_15_5_2() {
        int n = 5;

        // �������ʣ����򣩵����� ���Բ��ҵ� ע������p�ĵ�һ��λ�ò����κ����ݡ�
//        double[] p = {0, 0.15, 0.10, 0.05, 0.10, 0.20};
        // ����ʧ�ܵ�
//        double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
        // �㷨���� 15.5-2������
        double[] p = {0, 0.04, 0.06, 0.08, 0.02, 0.10, 0.12, 0.14};
        double[] q = {0.06, 0.06, 0.06, 0.06, 0.05, 0.05, 0.05, 0.05};
        n = p.length - 1;

        Q2 q2 = new Q2();
        eAndRoot e_root_basic = q2.optimalBSTBasic(p, q, n);
        System.out.println("���Ŷ����������ṹ:");
        q2.PRINT_OPTIMAL_BST(e_root_basic, 1, n, n, 1);
        System.out.println("���Ŷ�������������:" + e_root_basic.e[1][n]);
        System.out.println("���Ŷ�������������:" + e_root_basic.h);

        eAndRoot e_root_improve = q2.optimalBSTImprove(p, q, n);
        System.out.println("���Ŷ����������ṹ:");
        q2.PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("���Ŷ�������������:" + e_root_improve.e[1][n]);
        System.out.println("���Ŷ�������������:" + e_root_improve.h);

    }

    public void run_1() {
        int n = 5;
        // �㷨���� 15.5-2������
        // �������ʣ����򣩵����� ���Բ��ҵ� ע������p�ĵ�һ��λ�ò����κ����ݡ�
        double[] p = {0, 0.04, 0.06, 0.08, 0.02, 0.10, 0.12, 0.14};
        // ����ʧ�ܵ�
        double[] q = {0.06, 0.06, 0.06, 0.06, 0.05, 0.05, 0.05, 0.05};
        n = p.length - 1;

        eAndRoot e_root_basic = optimalBSTBasic(p, q, n);
        System.out.println("���Ŷ����������ṹ:");
        PRINT_OPTIMAL_BST(e_root_basic, 1, n, n, 1);
        System.out.println("���Ŷ�������������:" + e_root_basic.e[1][n]);
        System.out.println("���Ŷ�������������:" + e_root_basic.h);
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
        System.out.println("���Ŷ����������ṹ:");
        PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("���Ŷ�������������:" + e_root_improve.e[1][n]);
        System.out.println("���Ŷ�������������:" + e_root_improve.h);
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
        System.out.println("���Ŷ����������ṹ:");
        PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("���Ŷ�������������:" + e_root_improve.e[1][n]);
        System.out.println("���Ŷ�������������:" + e_root_improve.h);
        System.out.println("---------------------- end ---------------------------");
    }

    public void run_2_c(int n, int seed) {
        System.out.println("---------------------- start ---------------------------");
//  ��c�����ҳɹ��ĸ���Ϊ80%�����ɹ�Ϊ20%��
//  �ڳɹ��������10%��Ԫ�صĸ���Ϊ�ɹ���60%��ȫ�����ʵ�0.48����
        //      ����100���ؼ��ʶ��� ��10���ؼ��ʣ�����ҳɹ�����p�ĺ�Ϊ0.48
        //      �������Ϊ0.1
        //      90���ؼ��� ��ɹ����ҵĸ��ʺ�Ϊ 0.32
//  ����90%��Ԫ�ظ���Ϊ�ɹ�ʱ��40%��ȫ�����ʵ�0.32����
//  �趨���ҳɹ���߸���Ԫ�صĸ���С��10/n��
//  ��ע��Ԫ�ز��ҳɹ����ʽϸ�ʱ����ʧ��ʱ�ĸ����������ʧ������ĸ���ҲҪ��. ����pi���ԣ�����ʸ�ʱ��qi��q��i-1�� �ĸ���Ҳ��Ӧ����

//       ���� p ���飬�ɹ��ĸ��ʣ��ȳ�ʼ��Ϊ 0.32/(n*0.9) ʣ�ࣨn*0.1)���ؼ��֣������ȡλ�ã���ƽ�ȷ� 0.48 - 0.32/(n*0.9)*(n*0.1)
        // ÿ�����һ��λ�õ� ��q��Ӧ�� i ��i-1 λ�õĸ��ʽ������ӣ�
        // ���Ӷ����� ���������Ӱ�
        // ����q���� ʧ�ܵĸ���ͬ����ʼ��Ϊ  0.08/((n+1)*0.9) Ȼ�����q��iλ�����ӵĸ�������ʣ����ʲ����е�ռ�ȣ�
        // ����ͬ���ı��� �� q��ʣ������� ��ȡ��ֵ��ƽ�ָ�i �� i-1
        System.out.println(" n = " + n + "; case C :");
        // �ɹ�����
        double[] p = new double[n + 1]; // total 0.8 ; 0.1*n 0.48
        for (int i = 1; i < n + 1; i++) {
            p[i] = 0.32 / (n * 0.9);
        }
        // α�ؼ���
        double[] q = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            q[i] = 0.08 / ((n + 1) * 0.9);
        }
        // �����ʼ��
        randomProb(p, q, 0.48 - 0.32 / (n * 0.9) * (n * 0.1), 0.12 - 0.08 / ((n + 1) * 0.9) * (n + 1) * 0.1, seed);
        eAndRoot e_root_improve = optimalBSTImprove(p, q, n);
        System.out.println("���Ŷ����������ṹ:");
        PRINT_OPTIMAL_BST(e_root_improve, 1, n, n, 1);
        System.out.println("���Ŷ�������������:" + e_root_improve.e[1][n]);
        System.out.println("���Ŷ�������������:" + e_root_improve.h);
        System.out.println("---------------------- end ---------------------------");
    }

    public eAndRoot run_3(int n, int seed) {
        System.out.println("---------------------- start ---------------------------");
        System.out.println(" n = " + n + ";  run_3 ���� �ڶ���case c ����ͬ�Ĵ��� :");
        // �ɹ�����
        double[] p = new double[n + 1]; // total 0.8 ; 0.1*n 0.48
        for (int i = 1; i < n + 1; i++) {
            p[i] = 0.32 / (n * 0.9);
        }
        // α�ؼ���
        double[] q = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            q[i] = 0.08 / ((n + 1) * 0.9);
        }
        // �����ʼ��
        randomProb(p, q, 0.48 - 0.32 / (n * 0.9) * (n * 0.1), 0.12 - 0.08 / ((n + 1) * 0.9) * (n + 1) * 0.1, seed);
        eAndRoot e_root_improve = optimalBSTImprove(p, q, n);
        getTreeDeep(e_root_improve, 1, n, n, 1);
        return e_root_improve;
    }


    public eAndRoot run_4(int n, int seed) {
        System.out.println("---------------------- start ---------------------------");
        System.out.println(" n = " + n + ";  run_4 ���� �ڶ���case c �����ƵĴ��룬��ɹ����ʸ�Ϊ90 :");
        // �ɹ�����
        double[] p = new double[n + 1];
        for (int i = 1; i < n + 1; i++) {
            p[i] = 0.36 / (n * 0.9);
        }
        // α�ؼ���
        double[] q = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            q[i] = 0.04 / ((n + 1) * 0.9);
        }
        // �����ʼ��
        randomProb(p, q, 0.54 - 0.36 / (n * 0.9) * (n * 0.1), 0.06 - 0.04 / ((n + 1) * 0.9) * (n + 1) * 0.1, seed);
        eAndRoot e_root_improve = optimalBSTImprove(p, q, n);
        getTreeDeep(e_root_improve, 1, n, n, 1);
        return e_root_improve;
    }

    /**
     * ͨ�������� ������Ĺؼ������� ����bst
     *
     * @param sortedKeyWords
     */
    public static void bst_baaic(int[] sortedKeyWords, int[] searchKeyWords) {

    }

    //---------UTIL-----------------

    /**
     * ��ȡ������ɵ��������е���������
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
        int Root = e_root.root[i][j];//��ǰ�����
        if (i == 1 && j == n) {
            deep++;
            System.out.println("k" + Root + "Ϊ��");
        }
        if (e_root.h < deep) {
            e_root.h = deep;
        }
        if (i == Root) {//�����������Ҷ��
            System.out.println("d" + (i - 1) + "Ϊk" + Root + "��������");
        } else {
            System.out.println("k" + e_root.root[i][Root - 1] + "Ϊk" + Root + "��������");
            PRINT_OPTIMAL_BST(e_root, i, Root - 1, n, deep + 1);
        }
        if (j == Root) {//�����������Ҷ��
            System.out.println("d" + j + "Ϊk" + Root + "��������");
        } else {
            System.out.println("k" + e_root.root[Root + 1][j] + "Ϊk" + Root + "��������");
            PRINT_OPTIMAL_BST(e_root, Root + 1, j, n, deep + 1);
        }
    }

    public void getTreeDeep(eAndRoot e_root, int i, int j, int n, int deep) {
        int Root = e_root.root[i][j];//��ǰ�����
        if (i == 1 && j == n) {
            deep++;
        }
        if (e_root.h < deep) {
            e_root.h = deep;
        }
        if (i != Root) {//�����������Ҷ��
            getTreeDeep(e_root, i, Root - 1, n, deep + 1);
        }
        if (j != Root) {//�����������Ҷ��
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
            // �����ȡλ��
            int index = random.nextInt(n) + 1;
            // �����ȡ ���ʴ�С
            if (i == n - 1) {
                // ���һ�α��� ��ʣ���ֱ�ӷ��� ��Ҫǰ�߱�֤���һ�β��ᳬ 10/n
                p[index] += remindProb_p;
                q[index] += remindProb_q / 2;
                q[index - 1] += remindProb_q / 2;
                break;
            }
            double tempProb = 0.00;
            // �� tempProb С��һ����ֵ��ʱ�� ���������֪������λ����ҵ��˴��
            int random_times_flag = 0;
            // ͨ��minProb ��֤���һ��ʣ��ĸ��ʲ��ᳬ����
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
            // check Ӧ��check ȫ�� �Ƿ񳬹��� 10/n
            if (p[index] + tempProb > maxProb) {
                tempProb = maxProb - p[index];
            }
            // p ���鸳ֵ
            p[index] += tempProb;
            // ����ʣ�����
            remindProb_p -= tempProb;
            // ���� q
            if (remindProb_p == 0) {
                q[index] += remindProb_q / 2;
                q[index - 1] += remindProb_q / 2;
                remindProb_q = 0;
            } else {
                // ���� ��q[index] �� q[index-1] ��ֵ�ĸ���
                double tempProbQ = q_prob * tempProb / p_prob;
                remindProb_q -= tempProbQ;
                q[index] += tempProbQ / 2;
                q[index - 1] += tempProbQ / 2;
            }

        }
    }


    //---------BASIC-----------------

    /**
     * ����1 basic
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
     * ����2 improve
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
                if (i == j)//�����Ŷ�����������ֻ��һ��Ԫ��ʱ
                {
                    e_root.root[i][j] = j;
                    e_root.e[i][j] = e_root.e[i][j - 1] + e_root.e[j + 1][j] + w[i][j];
                } else//��ֹһ��Ԫ��ʱ
                {//���ǲ��ô�i��һֱ��鵽j,ֻ��������T.root[i][j-1]��T.root[i+1][j](������Ŀ���Ĳ���ʽ)
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



