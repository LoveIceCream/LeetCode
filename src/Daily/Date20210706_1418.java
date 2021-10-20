import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/6 9:36 上午
 */
public class Date20210706_1418 {
    public Date20210706_1418() {
    }

    public static void main(String[] args) {

        Date20210706_1418 demo = new Date20210706_1418();

    }

    /**
     * 展示菜单
     * orders[i]=[customerNamei,tableNumberi,foodItemi] ，
     * 其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
     * <p>
     * 餐按字母顺序，桌号升序
     *
     * @param orders
     * @return
     */
    public List<List<String>> displayTable(List<List<String>> orders) {

        List<List<String>> table = new ArrayList<List<String>>();
        ArrayList<String> tableAndFoodName = new ArrayList<>();
        // 遍历 orders,获取每一条的点餐信息
        for (int i = 0; i < orders.size(); i++) {
            List<String> single_order = orders.get(i);
            String foodItem = single_order.get(2);
            // 将点餐信息加入到 存放结构中
            tableAndFoodName.add(foodItem);
        }
        Collections.sort(tableAndFoodName);
        List<String> firstLine = new ArrayList<>(tableAndFoodName.size() + 1);
        for (int i = 0; i < firstLine.size(); i++) {
            if (i == 0) {
                firstLine.add("Table");
            } else {
                firstLine.add(tableAndFoodName.get(i - 1));
            }
        }

        TreeMap<Integer, List<String>> tableAndFoodCount = new TreeMap<>();
        // 再遍历一遍 获取 桌号和 对应的 餐名
        for (int i = 0; i < orders.size(); i++) {
            List<String> single_order = orders.get(i);
            int tableNumber = Integer.parseInt(single_order.get(1));
            String foodItem = single_order.get(2);
            List<String> temp = new ArrayList<>(tableAndFoodName.size() + 1);
            // 判断 是否原来的记录里已经存在了 当前桌号  存在的话获取 不存在的话 new一个
            if (tableAndFoodCount.containsKey(tableNumber)) {
                temp = tableAndFoodCount.get(tableNumber);
            } else {
                temp.add(0, tableNumber + "");
                for (int j = 1; j < temp.size(); j++) {
                    temp.add(j, 0 + "");
                }
            }

            int index = tableAndFoodName.indexOf(foodItem);
            temp.add(index + 1, (Integer.parseInt(temp.get(index + 1)) + 1) + "");
            tableAndFoodCount.put(tableNumber, temp);

        }

        // 将第一行加入到结果集里
        table.add(firstLine);

        // 将每一桌点的菜加入到结果集里

        Iterator iterator = tableAndFoodCount.keySet().iterator();
        while (iterator.hasNext()) {
            int tableNumber = (Integer) iterator.next();
            List<String> temp = tableAndFoodCount.get(tableNumber);
            table.add(temp);
        }
        return table;
    }


}
