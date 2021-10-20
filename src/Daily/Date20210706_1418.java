import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/6 9:36 ����
 */
public class Date20210706_1418 {
    public Date20210706_1418() {
    }

    public static void main(String[] args) {

        Date20210706_1418 demo = new Date20210706_1418();

    }

    /**
     * չʾ�˵�
     * orders[i]=[customerNamei,tableNumberi,foodItemi] ��
     * ���� customerNamei �ǿͻ���������tableNumberi �ǿͻ����ڲ��������ţ��� foodItemi �ǿͻ���Ĳ�Ʒ���ơ�
     * <p>
     * �Ͱ���ĸ˳����������
     *
     * @param orders
     * @return
     */
    public List<List<String>> displayTable(List<List<String>> orders) {

        List<List<String>> table = new ArrayList<List<String>>();
        ArrayList<String> tableAndFoodName = new ArrayList<>();
        // ���� orders,��ȡÿһ���ĵ����Ϣ
        for (int i = 0; i < orders.size(); i++) {
            List<String> single_order = orders.get(i);
            String foodItem = single_order.get(2);
            // �������Ϣ���뵽 ��Žṹ��
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
        // �ٱ���һ�� ��ȡ ���ź� ��Ӧ�� ����
        for (int i = 0; i < orders.size(); i++) {
            List<String> single_order = orders.get(i);
            int tableNumber = Integer.parseInt(single_order.get(1));
            String foodItem = single_order.get(2);
            List<String> temp = new ArrayList<>(tableAndFoodName.size() + 1);
            // �ж� �Ƿ�ԭ���ļ�¼���Ѿ������� ��ǰ����  ���ڵĻ���ȡ �����ڵĻ� newһ��
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

        // ����һ�м��뵽�������
        table.add(firstLine);

        // ��ÿһ����Ĳ˼��뵽�������

        Iterator iterator = tableAndFoodCount.keySet().iterator();
        while (iterator.hasNext()) {
            int tableNumber = (Integer) iterator.next();
            List<String> temp = tableAndFoodCount.get(tableNumber);
            table.add(temp);
        }
        return table;
    }


}
