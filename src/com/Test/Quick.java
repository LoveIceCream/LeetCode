/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/18 8:34 ����
 */
public class Quick {
    /**
     * ��������
     * @param array
     */
    public static void quickSort(int[] array) {
        int len;
        if(array == null
                || (len = array.length) == 0
                || len == 1) {
            return ;
        }
        sort(array, 0, len - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+",");
        }
    }

    /**
     * ���ź����㷨���ݹ�ʵ��
     * @param array
     * @param left
     * @param right
     */
    public static void sort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        // base�д�Ż�׼��
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // ˳�����Ҫ���ȴ��ұ߿�ʼ�����ң�ֱ���ҵ���baseֵС����
            while(array[j] >= base && i < j) {
                j--;
            }

            // �ٴ������ұ��ң�ֱ���ҵ���baseֵ�����
            while(array[i] <= base && i < j) {
                i++;
            }

            // �����ѭ��������ʾ�ҵ���λ�û���(i>=j)�ˣ������������������е�λ��
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // ����׼���ŵ��м��λ�ã���׼����λ��
        array[left] = array[i];
        array[i] = base;

        // �ݹ飬�������׼����������ִ�к�����ͬ���Ĳ���
        // i��������Ϊ������ȷ���õĻ�׼ֵ��λ�ã������ٴ���
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    public static void main(String[] args) {
        int[] a = {14,52,6,73,2,31,18,9,10};
        quickSort(a);
    }
}