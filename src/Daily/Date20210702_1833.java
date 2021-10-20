import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/2 10:22 上午
 */
public class Date20210702_1833 {
    public Date20210702_1833() {
    }

    /**
     * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
     * <p>
     * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
     * <p>
     * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
     * <p>
     * 注意：Tony 可以按任意顺序购买雪糕。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 输入：costs = [1,3,2,4,1], coins = 7
     * 输出：4
     * 解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
     * <p>
     * 输入：costs = [10,6,8,7,7,8], coins = 5
     * 输出：0
     * 解释：Tony 没有足够的钱买任何一支雪糕。
     * <p>
     * 输入：costs = [1,6,3,1,2,5], coins = 20
     * 输出：6
     * 解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
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
     * 计算最多能买多少个
     * 想把costs数组更改，做一个hashmap？ key是价格 ，value是数量， 然后遍历 1，coins？
     *
     * @param costs
     * @param coins
     * @return
     */
    public int maxIceCream(int[] costs, int coins) {
        // 只需要记录 价格低于 拥有的钱币的数量
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
                // 判断数量
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
