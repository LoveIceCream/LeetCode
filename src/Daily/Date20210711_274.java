/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/11 1:38 ����
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
     * �� ����
     * �����򣬴Ӵ�С��Ȼ��������飬�������Ӧ��ֵ �����±��һ ���� �����Ӧ�� ��һ��ֵ��С�ڵ�ǰֵ
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int h = 0;
        // ���� ���� ����
        citations = quickSort(citations);
        for (int id :citations){
            System.out.println(id);
        }
        // ����һ�� ������ ����
        for (int i = 0; i < citations.length; i++) {
            int temp_h = i + 1;
            if (temp_h <= citations[i]) {
                // �� ��ǰ�±굱 ����ֵ ���� �ѱ����������������������һ������ temp_h ƪ���±����ô�������temp_h��
                // Ȼ����֤�ڶ��㣬������������ô������ܳ��� temp_h �Σ��� С�ڵ��� ����֮���û��������Ҳ����
                if (temp_h + 1 >= citations.length || temp_h >= citations[temp_h]) {
                    // ˵�����û�������� ||  // ��������� ���� С�ڵ��� temp_h
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
        // ���գ��������Ϊ �� temp С �� ��temp ���������
        swap(citations, low, j);
        return j;
    }

    public void swap(int[] citations, int low, int high) {
        int temp = citations[low];
        citations[low] = citations[high];
        citations[high] = temp;

    }
}
