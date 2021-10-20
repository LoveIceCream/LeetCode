import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/10 4:51 ����
 */
public class Date20210710_981 {


    /**
     * ����һ������ʱ��ļ�ֵ�洢��?TimeMap����֧����������������
     * <p>
     * 1. set(string key, string value, int timestamp)
     * <p>
     * �洢��?key��ֵ?value���Լ�������ʱ���?timestamp��
     * 2. get(string key, int timestamp)
     * <p>
     * ������ǰ����?set(key, value, timestamp_prev)?���洢��ֵ������?timestamp_prev <= timestamp��
     * ����ж��������ֵ���򷵻ض�Ӧ����??timestamp_prev?���Ǹ�ֵ��
     * ���û��ֵ���򷵻ؿ��ַ�����""����
     * ?
     * <p>
     * ʾ�� 1��
     * <p>
     * ���룺inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
     * �����[null,null,"bar","bar",null,"bar2","bar2"]
     * ���ͣ�?
     * TimeMap kv; ?
     * kv.set("foo", "bar", 1); // �洢�� "foo" ��ֵ "bar" �Լ�ʱ��� timestamp = 1 ?
     * kv.get("foo", 1);  // ��� "bar" ?
     * kv.get("foo", 3); // ��� "bar" ��Ϊ��ʱ��� 3 ��ʱ��� 2 ��û�ж�Ӧ "foo" ��ֵ������Ψһ��ֵλ��ʱ��� 1 ������ "bar"�� ?
     * kv.set("foo", "bar2", 4); ?
     * kv.get("foo", 4); // ��� "bar2" ?
     * kv.get("foo", 5); // ��� "bar2" ?
     * <p>
     * ʾ�� 2��
     * <p>
     * ���룺inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
     * �����[null,null,null,"","high","high","low","low"]
     * ?
     * <p>
     * ��ʾ��
     * <p>
     * ���еļ�/ֵ�ַ�������Сд�ġ�
     * ���еļ�/ֵ�ַ������ȶ���?[1, 100]?��Χ�ڡ�
     * ����?TimeMap.set?�����е�ʱ���?timestamps �����ϸ�����ġ�
     * 1 <= timestamp <= 10^7
     * TimeMap.set ��?TimeMap.get?������ÿ�����������н�����ϣ������ܼ�?120000 �Ρ�
     */
    public Date20210710_981() {
    }

    /**
     * Initialize your data structure here.
     */
//    public TimeMap() {
    // ���ȣ�����һ�� key value �Ĵ洢�ṹ
    // ������ͨ��key ��ȡָ����valuevalue ֵ
    // �ڴ˻����ϣ�key �洢ʱ����ʱ�����¼��get��ʱ���ȡ С�ڵ���ָ��ʱ�����¼��Ӧ�����ֵ�����ڷ���ֵ������ ���ؿ��ַ��� ����
    // ���Լ��룬 ���ֱ��һ�� hashmap Ҳ����Ҫ�洢��o(1) ��������
    // �ڲ���Ҫ�洢 ʱ�����value
    // set ʱ��ʱ������ϸ������ ���Բ����� ͬһ��ʱ�����Ӧ���ֵ
    // ���value ��һ��  LinkHashHashMap

    //
//    }

    class Node {
        int timestamp;
        String value;
    }

    private static HashMap<String, LinkedHashMap<Integer, String>> timeStampMap = new HashMap<>();


    public void set2(String key, String value, int timestamp) {
        if (timeStampMap.containsKey(key)) {
            LinkedHashMap<Integer, String> temp = timeStampMap.get(key);
            temp.put(timestamp, value);

        } else {
            LinkedHashMap<Integer, String> temp = new LinkedHashMap<>();
            temp.put(timestamp, value);
            timeStampMap.put(key, temp);
        }
    }

    public String get2(String key, int timestamp) {
        String value = "";
        LinkedHashMap<Integer, String> temp = timeStampMap.get(key);
        if (temp.containsKey(timestamp)) {
            value = temp.get(timestamp);
        } else {
            // �� ��  timestamp  С �� ��һ�� ֵ
            // ����̫������ ��һ������ķ�ʽ
            //
            int timeStamp_key = -1;
            Iterator iterator = temp.keySet().iterator();
            while (iterator.hasNext()) {
                int temp_timeStamp = (int) iterator.next();
                if (temp_timeStamp > timestamp) {
                    break;
                }
                timeStamp_key = temp_timeStamp;
            }
            if (timeStamp_key != -1) {
                value = temp.get(timeStamp_key);
            }
        }

        return value;
    }


    private static HashMap<String, List<Node>> nodeListHashMap = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        if (nodeListHashMap.containsKey(key)) {
            List<Node> temp = nodeListHashMap.get(key);
            Node node = new Node();
            node.timestamp = timestamp;
            node.value = value;
            temp.add(node);

        } else {
            List<Node> temp = new ArrayList<>();
            Node node = new Node();
            node.timestamp = timestamp;
            node.value = value;
            temp.add(node);
            nodeListHashMap.put(key, temp);
        }
    }

    public String get(String key, int timestamp) {
        String value = "";

        if (nodeListHashMap.containsKey(key)) {
            List<Node> nodeList = nodeListHashMap.get(key);
            int s = nodeList.size();
            if (s == 0) {
                return "";
            }
            int low = 0;
            int high = s - 1;
            while (low < high) {
                int mid = (low + high + 1) / 2;
                if (nodeList.get(mid).timestamp <= timestamp) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            if (nodeList.get(high).timestamp <= timestamp) {
                value = nodeList.get(high).value;
            }
        }

        return value;
    }


    public static void main(String[] args) {
        Date20210710_981 demo = new Date20210710_981();
        demo.set("love", "high", 10);
        demo.set("love", "low", 20);
        demo.get("love", 5);
        demo.get("love", 10);
        demo.get("love", 15);
        demo.get("love", 20);
        demo.get("love", 25);
    }
}
