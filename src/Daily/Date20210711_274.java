/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/11 1:38 下午
 */
public class Date20210711_274 {
    public Date20210711_274() {
    }

    public static void main(String[] args) {
        int[] c2 = {11,15};
        Date20210711_274 demo = new Date20210711_274();

        System.out.println(demo.hIndex(c2));
    }

    /**
     * 高 引用
     * 先排序，从大到小，然后遍历数组，当数组对应的值 等于下标加一 并且 数组对应的 下一个值，小于当前值
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int h = 0;
        // 数组 排序 降序
        citations = quickSort(citations);
        for (int id :citations){
            System.out.println(id);
        }
        // 遍历一遍 引用量 数组
        for (int i = 0; i < citations.length; i++) {
            int temp_h = i + 1;
            if (temp_h <= citations[i]) {
                // 当 当前下标当 引用值 大于 已遍历文章数量。即可满足第一部，有 temp_h 篇文章被引用次数大于temp_h次
                // 然后验证第二点，其余的文章引用次数不能超过 temp_h 次，即 小于等于 或者之后就没有数据了也满足
                if (temp_h + 1 >= citations.length || temp_h >= citations[temp_h]) {
                    // 说明后边没有数据了 ||  // 后边有数据 并且 小于等于 temp_h
                    h = temp_h;
                }

            }
        }

        return h;
    }


    public int[] quickSort(int[] citations) {
        quickSort(citations, 0, citations.length - 1);
        return citations;
    }

    public void quickSort(int[] citations, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = partition(citations, low, high);
        quickSort(citations, low, mid - 1);
        quickSort(citations, mid + 1, high);
    }

    public int partition(int[] citations, int low, int high) {
        int i = low, j = high + 1;
        int temp = citations[low];
        while (true) {
            while (citations[++i] >= temp && i != high) ;
            while (temp >= citations[--j] && j != low) ;
            if (i >= j) {
                break;
            }
            swap(citations, i, j);
        }
        // 最终，将数组分为 比 temp 小 和 比temp 大的两部分
        swap(citations, low, j);
        return j;
    }

    public void swap(int[] citations, int low, int high) {
        int temp = citations[low];
        citations[low] = citations[high];
        citations[high] = temp;

    }
}
