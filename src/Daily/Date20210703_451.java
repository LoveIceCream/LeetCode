import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/3 9:25 上午
 */
public class Date20210703_451 {
    public Date20210703_451() {

    }

    public static void main(String[] args) {
        Date20210703_451 demo = new Date20210703_451();
        String s = "tree";
        System.out.println(demo.frequencySort(s));
    }

    /**
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer(s.length());
        // 构建map 获取到每个字符都有多少个了 然后按照数量从大到小遍历即可
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }

        // 遍历map
        ArrayList<String> stringArrayList = new ArrayList<>(charCountMap.size());
        Iterator iterator = charCountMap.keySet().iterator();
        while (iterator.hasNext()) {
            char key = (Character) iterator.next();
            int count = charCountMap.get(key);
            String temp = "";
            for (int i = 0; i < count; i++) {
                temp += key;
            }
            stringArrayList.add(temp);
        }

        stringArrayList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        for (String str : stringArrayList) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

}
