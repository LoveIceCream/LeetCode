import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/2 10:22 ����
 */
public class Date20210702_1833 {
    public Date20210702_1833() {
    }

    /**
     * �������ף�С�к� Tony ����һЩѩ�������
     * <p>
     * �̵����µ� n ֧ѩ�⣬�ó���Ϊ n ������ costs ��ʾѩ��Ķ��ۣ����� costs[i] ��ʾ�� i ֧ѩ����ֽ�۸�Tony һ���� coins �ֽ�����������ѣ�����Ҫ�򾡿��ܶ��ѩ�⡣
     * <p>
     * ����۸����� costs ���ֽ��� coins ��������㲢���� Tony �� coins �ֽ��ܹ��򵽵�ѩ��� ������� ��
     * <p>
     * ע�⣺Tony ���԰�����˳����ѩ�⡣
     * <p>
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/maximum-ice-cream-bars
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     * <p>
     * ���룺costs = [1,3,2,4,1], coins = 7
     * �����4
     * ���ͣ�Tony �������±�Ϊ 0��1��2��4 ��ѩ�⣬�ܼ�Ϊ 1 + 3 + 2 + 1 = 7
     * <p>
     * ���룺costs = [10,6,8,7,7,8], coins = 5
     * �����0
     * ���ͣ�Tony û���㹻��Ǯ���κ�һ֧ѩ�⡣
     * <p>
     * ���룺costs = [1,6,3,1,2,5], coins = 20
     * �����6
     * ���ͣ�Tony �����������е�ѩ�⣬�ܼ�Ϊ 1 + 6 + 3 + 1 + 2 + 5 = 18 ��
     *
     * @param args
     */
    public static void main(String[] args) {

        Date20210702_1833 demo = new Date20210702_1833();
//        int[] costs = {1, 3, 2, 4, 1};int coins = 7;
//        int[] costs = {10, 6, 8, 7, 7, 8};int coins = 5;
        int[] costs = {1, 6, 3, 1, 2, 5};int coins = 20;

        System.out.println(demo.maxIceCream(costs, coins));
        // 100000
    }

    /**
     * �������������ٸ�
     * ���costs������ģ���һ��hashmap�� key�Ǽ۸� ��value�������� Ȼ����� 1��coins��
     *
     * @param costs
     * @param coins
     * @return
     */
    public int maxIceCream(int[] costs, int coins) {
        // ֻ��Ҫ��¼ �۸���� ӵ�е�Ǯ�ҵ�����
        TreeMap<Integer, Integer> coustsAndCount = new TreeMap<>();
        for (int i = 0; i < costs.length; i++) {
            int cost = costs[i];
            if (cost <= coins) {
                if (coustsAndCount.containsKey(cost)) {
                    coustsAndCount.put(cost, coustsAndCount.get(cost) + 1);
                } else {
                    coustsAndCount.put(cost, 1);
                }
            }
        }

        int lastCoins = coins;
        int count = 0;
        Iterator<Integer> iterator =coustsAndCount.keySet().iterator();
        while (iterator.hasNext()){
            int tempCoins = iterator.next();
            if (coustsAndCount.containsKey(tempCoins)) {
                // �ж�����
                int maxCount = coustsAndCount.get(tempCoins);
                int canBuyCount = lastCoins / tempCoins;
                if (maxCount <= canBuyCount) {
                    canBuyCount = maxCount;
                }
                count += canBuyCount;
                lastCoins = lastCoins - canBuyCount * tempCoins;
                if (lastCoins == 0) {
                    break;
                }
            }
        }
        return count;
    }
}
