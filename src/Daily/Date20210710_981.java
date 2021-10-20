import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/10 4:51 下午
 */
public class Date20210710_981 {


    /**
     * 创建一个基于时间的键值存储类?TimeMap，它支持下面两个操作：
     * <p>
     * 1. set(string key, string value, int timestamp)
     * <p>
     * 存储键?key、值?value，以及给定的时间戳?timestamp。
     * 2. get(string key, int timestamp)
     * <p>
     * 返回先前调用?set(key, value, timestamp_prev)?所存储的值，其中?timestamp_prev <= timestamp。
     * 如果有多个这样的值，则返回对应最大的??timestamp_prev?的那个值。
     * 如果没有值，则返回空字符串（""）。
     * ?
     * <p>
     * 示例 1：
     * <p>
     * 输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
     * 输出：[null,null,"bar","bar",null,"bar2","bar2"]
     * 解释：?
     * TimeMap kv; ?
     * kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1 ?
     * kv.get("foo", 1);  // 输出 "bar" ?
     * kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"） ?
     * kv.set("foo", "bar2", 4); ?
     * kv.get("foo", 4); // 输出 "bar2" ?
     * kv.get("foo", 5); // 输出 "bar2" ?
     * <p>
     * 示例 2：
     * <p>
     * 输入：inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
     * 输出：[null,null,null,"","high","high","low","low"]
     * ?
     * <p>
     * 提示：
     * <p>
     * 所有的键/值字符串都是小写的。
     * 所有的键/值字符串长度都在?[1, 100]?范围内。
     * 所有?TimeMap.set?操作中的时间戳?timestamps 都是严格递增的。
     * 1 <= timestamp <= 10^7
     * TimeMap.set 和?TimeMap.get?函数在每个测试用例中将（组合）调用总计?120000 次。
     */
    public Date20210710_981() {
    }

    /**
     * Initialize your data structure here.
     */
//    public TimeMap() {
    // 首先，这是一个 key value 的存储结构
    // 基础是通过key 获取指定的valuevalue 值
    // 在此基础上，key 存储时存在时间戳记录，get的时候获取 小于等于指定时间戳记录对应的最大值。存在返回值，否则 返回空字符串 “”
    // 所以简单想， 外层直接一个 hashmap 也不需要存储，o(1) 的外层查找
    // 内层需要存储 时间戳和value
    // set 时的时间戳是严格递增的 所以不存在 同一个时间戳对应多个值
    // 最简单value 是一个  LinkHashHashMap

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
            // 找 比  timestamp  小 的 第一个 值
            // 遍历太慢了呗 找一个更快的方式
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
