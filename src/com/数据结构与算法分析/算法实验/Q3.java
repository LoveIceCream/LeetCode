package �㷨ʵ��;

import java.io.*;

/**
 * �����㷨
 * ������⣺�����������й�����
 * �����������й���m�� n �����гɾ������еĳ�������ɡ�Ϊ�˷�ֹ����������
 * ��Ҫ�ڳ����������þ�����������λ��ÿ�����������˳��˼��������ڵĳ������⣬
 * �����Լ����������ڵĳ��������ڵ��ϡ��¡�����4 �������ҡ������һ�����ž�����������λ���㷨��
 * ʹ���������й���ÿһ�������Ҷ��ھ��������˵ļ���֮�£������õľ��������������١�
 * ���һ���㷨�����㾯�������˵������λ���ţ�ʹ���������й���ÿһ�������Ҷ��ھ��������˵ļ���֮�£������õľ��������������١�
 * <p>
 * ���룺���ļ�input.txt��������,�ļ��ĵ�һ����2 ��������m ��n (1��m,n��20)��
 * �������������ľ��������������������λ���������output.txt��
 * �ļ��е�һ���Ǿ���������������������m ����ÿ��n ������0 ��ʾ����λ��1 ��ʾ��λ��
 *
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/12 3:11 ����
 */
public class Q3 {
    // ���������� �����λ
    int[][] directionArr = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // ��
    int m;
    // ��
    int n;
    // ���ٻ����˸��� -- ����ֵ
    int minCount;
    // ��ǰ�ѷ��û���������
    int tempCount;
    // �������� 1
    int limit_all_count;
    // �������� 2
    int limit_redundancy;
    // ��¼�����״̬
    int[][] spyOnStatusArray;
    // ����ؽڵ������
    int spyOnCount;
    // ��ػ�����λ�� - ���ս��
    int[][] robotLocationArray;
    // ��ػ�����λ�� - �м��¼
    int[][] tempLocationArray;

    /**
     * ���ļ���ȡ m n
     *
     * @param filePath
     * @return
     */
    public int[] getFromFile(String filePath) {
        int[] inputs = new int[2];

        try {
            File file = new File(filePath);
            //�ж��ļ��Ƿ����
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] lineTxts = lineTxt.split(" ");
                    inputs[0] = Integer.parseInt(lineTxts[0].trim());
                    inputs[1] = Integer.parseInt(lineTxts[1].trim());
                }
                read.close();
            } else {
                System.out.println("�Ҳ���ָ�����ļ�");
            }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }
        return inputs;
    }

    /**
     * �����������ļ���
     *
     * @param filePath
     */
    public void out2File(String filePath) {

        try {
            File writeName = new File(filePath);
            writeName.createNewFile(); // �������ļ�,��ͬ�����ļ��Ļ�ֱ�Ӹ���
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(minCount + "\r\n");
                System.out.println(minCount);
                for (int i = 1; i <= m; i++) {
                    for (int j = 1; j <= n; j++) {
                        System.out.print(robotLocationArray[i][j] + "\t");
                        out.write(robotLocationArray[i][j] + "\t");
                    }
                    out.write("\r\n");
                    System.out.println("\n");
                }
                // �ѻ���������ѹ���ļ�
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * ����߽� m+1 n+1 �߽�ֵ����ͳһ����
     * ��֦����
     * 1 ���õĻ����˸������ᳬ��mn/3+1��
     * 2 ���(i,j)�ѱ�����,����Ҫ�ڴ˴����û�����,ֱ����������.
     * 3 ���ҽ���(i,j)���������½ǻ���(I,j+1)δ������ʱ�ſ��Ƿ�����(i,j)�����.
     * 4 ���ҽ���(i,j+1)��(i,j+2)δ������ʱ�ſ��Ƿ�����(i,j+1)�����.
     * 5 ��i=nʱ,�����Ƿ�����(i+1,j)�����.
     * 6 ��¼�Ѿ����ӵĸ����,(��ǰ����ֵ��ȥ��ǰ�ѷ��ø���)*5���С��δ���ӵĸ����,��һ���ﲻ���ȵ�ǰ����ֵ���õ����,��ȥ.
     * 7 ������(6),���Ǹ��������,����ÿ�������˶��ܶ�������5�����,���ٻ���m/4+5������,
     * �����֦��������i<n-1�����,��Ϊ���������������ֵ���ѷ��ø����ǳ��ӽ�,
     * ���Ǵﲻ�����ֵ   ��
     *
     * @return
     */
    public void search() {

        minCount = m * n / 3 + 2;
        tempCount = 0;

        limit_all_count = m * n;
        limit_redundancy = m * n + n / 4 + 5;
        spyOnCount = 0;
        spyOnStatusArray = new int[m + 2][n + 2];
        robotLocationArray = new int[m + 2][n + 2];
        tempLocationArray = new int[m + 2][n + 2];

        // ��ʼ���߽�(���ܣ������) ���״̬
        for (int i = 0; i <= m + 1; i++) {
            spyOnStatusArray[i][0] = 1;
            spyOnStatusArray[i][n + 1] = 1;
        }
        for (int i = 0; i <= n + 1; ++i) {
            spyOnStatusArray[0][i] = 1;
            spyOnStatusArray[m + 1][i] = 1;
        }
        search(1, 1);

    }

    /**
     * ���� ��֦
     *
     * @param point_m
     * @param point_n
     */
    public void search(int point_m, int point_n) {

        // ��֦���� 1
        if (tempCount >= minCount) {
            return;
        }

        // ��֦���� 2
        while (point_m <= m && spyOnStatusArray[point_m][point_n] >= 1) //�ѷ��õĲ��ٱ�����
        {
            point_n++;
            if (point_n > n) {
                point_m++;
                point_n = 1;
            }
        }

        //���´� ���б��� ��������
        if (point_m > m) {
            // ����ǰ�� ��ֵ�����Ž�
            minCount = tempCount;
            // ��ǰ������λ������ ��ֵ�� ���ս������
            copyValue(robotLocationArray, tempLocationArray);
//            System.out.println("........................");
//            System.out.println(minCount);
//            for (int i = 1; i <= m; i++) {
//                for (int j = 1; j <= n; j++) {
//                    System.out.println(robotLocationArray[i][j] + "\t");
//                }
//                System.out.println("\n");
//            }
//            System.out.println("........................");
            return;
        }

        // ��֦���� 6 ��� ��minCount - tempCount) * 5 С��δ��ص����������֦ ת��Ϊ ��ֵ���ϱ���ص���������С������
        int reach = spyOnCount + (minCount - tempCount) * 5;
        if (reach <= limit_all_count) {
            return;
        }
        // ��֦���� 7
        if (point_m < m - 1 && reach <= limit_redundancy) {
            return;
        }

        // �����½ǵĵ� ���ߵ�ǰ�� �� �ĵ�δ�����
        if ((point_m == m && point_n == n)
                || spyOnStatusArray[point_m][point_n + 1] == 0) {
            calculate(point_m, point_n, point_m, point_n);
        }
        if (point_m < m) {
            calculate(point_m + 1, point_n, point_m, point_n);
        }
        if (point_n < n && (spyOnStatusArray[point_m][point_n + 1] == 0
                || spyOnStatusArray[point_m][point_n + 2] == 0)
        ) {
            calculate(point_m, point_n + 1, point_m, point_n);
        }
    }

    /**
     * ���� ����
     * @param x
     * @param y
     * @param i
     * @param j
     */
    public void calculate(int x, int y, int i, int j) {
        // (x,y) �㴦���û�����
        tempLocationArray[x][y] = 1;
        // ��ʱ������������һ
        tempCount++;
        // ���������� ����λ�õļ��״ֵ̬��һ�����Ϊ1 ���Ѿ�����ص�λ�õ����� ��һ
        for (int k = 0; k < 5; k++) {
            int tempValue = ++spyOnStatusArray[x + directionArr[k][0]][y + directionArr[k][1]];
            if (tempValue == 1) {
                spyOnCount++;
            }
        }
        // �� ������
        search(i, j + 1);
        // (x,y) �㴦ȡ��������
        tempLocationArray[x][y] = 0;
        // ��ʱ������������һ
        tempCount--;
        // ���������У�����λ�õļ��״ֵ̬��һ�����Ϊ0 �Ѿ�����ص�λ������ ��һ
        for (int k = 0; k < 5; k++) {
            int tempValue = --spyOnStatusArray[x + directionArr[k][0]][y + directionArr[k][1]];
            if (tempValue == 0) {
                spyOnCount--;
            }
        }
    }

    /**
     * ���������ֵ
     * @param newArr
     * @param oldArr
     */
    public void copyValue(int[][] newArr, int[][] oldArr) {
        for (int i = 0; i < oldArr.length; i++) {
            for (int j = 0; j < oldArr[i].length; j++) {
                newArr[i][j] = oldArr[i][j];
            }
        }
    }

    public static void main(String[] args) {
        String inputPath = "src/com/���ݽṹ���㷨����/�㷨ʵ��/input.txt";
        String outputPath = "src/com/���ݽṹ���㷨����/�㷨ʵ��/output.txt";
        Q3 q3 = new Q3();
        int[] inputs = q3.getFromFile(inputPath);
        System.out.println(inputs[0] + "," + inputs[1]);
        q3.m = inputs[0];
        q3.n = inputs[1];
        q3.search();
        q3.out2File(outputPath);
    }
}
