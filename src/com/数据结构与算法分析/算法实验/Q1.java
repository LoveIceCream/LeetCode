package �㷨ʵ��;

import java.util.ArrayList;
import java.util.Random;

/**
 * һ�����η�
 * ������⣺FFT�㷨
 * ˵����ɢFOURIER�任�Ķ��壬�÷��η���������ñ任���㷨����ע����ν��ݹ���ʽ���㷨ת���ɵ�����ʽ��
 * �Ƚ�ֱ�Ӽ����FFT�㷨�ĸ��Ӷȣ�̽�����˼�����Ӧ��������һЩ�任�ϵõ���Ӧ�Ŀ����㷨��
 * https://www.cnblogs.com/RabbitHu/p/FFT.html
 * https://www.cnblogs.com/RabbitHu/p/BZOJ2194.html
 * <p>
 * https://blog.csdn.net/DreamlessDreams/article/details/79795618
 * <p>
 * https://blog.csdn.net/xienaoban/article/details/69486299
 * <p>
 * ������������ʽ�ĳ˻�
 *
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/12 3:10 ����
 */
public class Q1 {

    int[] multinomial_A;
    Complex[] complexes_A;
    Double[] res;
    Complex[] omega;
    Complex[] inv;

    int n = 1;
    static Complex[] buf;

    public Q1(int n, int maxValue) {
        multinomial_A = getRandomCoefficient(n, maxValue);
    }

    /**
     * �ݹ�fft
     *
     * @param complex
     * @param n
     */
    public void FFT_Recursion(Complex[] complex, int startIndex, int n, boolean isReciprocal) {
        if (n == 1) {
            return;
        }
        int m = n / 2;
        for (int i = startIndex; i < startIndex + m; i++) { //��ÿһ�����ż��Ϊ����
            buf[i] = complex[2 * i - startIndex];
            buf[i + m] = complex[2 * i + 1 - startIndex];
        }
        for (int i = startIndex; i < startIndex + n; i++) {
            complex[i] = buf[i];
        }
        FFT_Recursion(complex, startIndex, m, isReciprocal); //�ݹ鴦������������
        FFT_Recursion(complex, startIndex + m, m, isReciprocal);
        for (int i = startIndex; i < startIndex + m; i++) { //ö��x������A(x)
            Complex ome = new Complex(Math.cos(2 * Math.PI * i / n), Math.sin(2 * Math.PI * i / n));;
            if (isReciprocal) {
                ome = ome.conj();
            }
            Complex wa = ome.mul(complex[i + m]);
            buf[i] = complex[i].add(wa); //����֮ǰ�Ƴ��Ľ��ۼ���
            buf[i + m] = complex[i].sub(wa);
        }
        for (int i = startIndex; i < +startIndex + n; i++) {
            complex[i] = buf[i];
        }

    }

    public void FFT_iteration(Complex[] complexes, Complex[] omg, int n) {
        int lim = 0;
        while ((1 << lim) < n) {
            lim++;
        }
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = 0; j < lim; j++) {
                if (((i >> j) & 1) == 1) {
                    t |= (1 << (lim - j - 1));
                }
            }
            // i < t ������ʹ��ÿ�Ե�ֻ������һ�Σ����򽻻������൱��û������
            if (i < t) {
                Complex temp = new Complex(complexes[i].real, complexes[i].image);
                complexes[i].real = complexes[t].real;
                complexes[i].image = complexes[t].image;
                complexes[t].real = temp.real;
                complexes[t].image = temp.image;
            }
        }

        for (int l = 2; l <= n; l *= 2) {
            int m = l / 2;
            for (int j = 0; j < n; j += l) {
                for (int i = 0; i < m; i++) {
                    Complex temp = omg[n / l * i].mul(complexes[j + i + m]);
                    buf[j + i] = complexes[j + i].add(temp);
                    buf[j + i + m] = complexes[j + i].sub(temp);
                }
            }
            for (int j = 0; j < n; j++) {
                complexes[j] = buf[j];
            }
        }
    }

    /**
     * ���� int[] a={1,2,3}  a[0]==1 ����x��0�������ϵ��Ϊ1
     *
     * @param length
     * @return
     */
    public int[] getRandomCoefficient(int length, int maxValue) {
        int[] CoefficientArray = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            CoefficientArray[i] = random.nextInt(maxValue);
        }
        return CoefficientArray;
    }

    public void outMultinomial(int[] multinomial) {
        String multinomialStr = "";
        for (int i = 0; i < multinomial.length; i++) {
            if (multinomialStr == "") {
                multinomialStr += multinomial[i] + "x^" + i;
            } else {
                multinomialStr += " + " + multinomial[i] + "x^" + i;

            }
        }
        System.out.println(multinomialStr);
    }

    public void outMultinomialReversed(Double[] multinomial) {
        String multinomialStr = "";
        for (int i = multinomial.length - 1, k = 0; i >= 0; i--, k++) {
            if (multinomialStr == "") {
                multinomialStr += multinomial[i] + "x^" + k;
            } else {
                multinomialStr += " + " + multinomial[i] + "x^" + k;

            }
        }
        System.out.println(multinomialStr);
    }


    public static void runFFTRecursion(int n, int maxValue) {
        System.out.println("-------------------");
        System.out.println("FFT �ݹ飺");
        System.out.println("�������:" + n + "��ϵ������ߴ���Ϊ" + (n - 1) + ",ϵ�����ֵΪ" + maxValue);
        // ʵ���� Q1 ����ʼ�� ϵ��
        Q1 FFT = new Q1(n, maxValue);
        System.out.println("���ԭʼ���������γɵĶ���ʽ��");

        FFT.outMultinomial(FFT.multinomial_A);

        // 2����������
        while (FFT.n < FFT.multinomial_A.length) {
            FFT.n = FFT.n * 2;
        }

        FFT.complexes_A = new Complex[FFT.n];
        FFT.omega = new Complex[FFT.n];
        FFT.inv = new Complex[FFT.n];

        init(FFT.n, FFT.omega, FFT.inv);

        // ��ʼ�� complexes_A complexes_B

        for (int i = 0; i < FFT.n; i++) {
            if (i >= FFT.multinomial_A.length) {
                Complex complex = new Complex(0, 0);
                FFT.complexes_A[i] = complex;
                continue;
            }
            int index = FFT.multinomial_A.length - 1 - i;
            Double value = new Double(FFT.multinomial_A[index]);
            Complex complex = new Complex(value, 0);
            FFT.complexes_A[i] = complex;
        }


        buf = new Complex[FFT.n];
        FFT.FFT_Recursion(FFT.complexes_A, 0, FFT.multinomial_A.length, false);
        System.out.println("�������FOURIER�任��ĵ�ֵ");

        for (int i = 0; i < FFT.n; i++) {
            Complex complex = FFT.complexes_A[i];
            System.out.println(complex.real + "+" + complex.image + "i\n");
        }

        // IDFT
        FFT.FFT_Recursion(FFT.complexes_A, 0, FFT.multinomial_A.length, true);
        // ȡʵ��
        FFT.res = new Double[FFT.n];

        for (int i = 0; i < FFT.n; i++) {
            FFT.res[i] = Math.floor(FFT.complexes_A[i].real / FFT.multinomial_A.length + 0.5);
        }

        System.out.println("ͨ����任��ԭ�Ķ���ʽ��");
        FFT.outMultinomialReversed(FFT.res);
    }

    public static void runFFTiteration(int n, int maxValue) {
        System.out.println("-------------------");
        System.out.println("FFT ������");
        System.out.println("�������:" + n + "��ϵ������ߴ���Ϊ" + (n - 1) + ",ϵ�����ֵΪ" + maxValue);

        // ʵ���� Q1 ����ʼ�� ϵ��
        Q1 FFT = new Q1(n, maxValue);

        System.out.println("���ԭʼ���������γɵĶ���ʽ��");
        FFT.outMultinomial(FFT.multinomial_A);

        // 2����������
        while (FFT.n < FFT.multinomial_A.length) {
            FFT.n = FFT.n * 2;
        }

        FFT.complexes_A = new Complex[FFT.n];

        FFT.omega = new Complex[FFT.n];
        ;
        FFT.inv = new Complex[FFT.n];
        ;

        init(FFT.n, FFT.omega, FFT.inv);

        // ��ʼ�� complexes_A complexes_B

        for (int i = 0; i < FFT.n; i++) {
            if (i >= FFT.multinomial_A.length) {
                Complex complex = new Complex(0, 0);
                FFT.complexes_A[i] = complex;
                continue;
            }
            int index = FFT.multinomial_A.length - 1 - i;
            Double value = new Double(FFT.multinomial_A[index]);
            Complex complex = new Complex(value, 0);
            FFT.complexes_A[i] = complex;
        }


        buf = new Complex[FFT.n];
        FFT.FFT_iteration(FFT.complexes_A, FFT.omega, FFT.multinomial_A.length);


        System.out.println("�������FOURIER�任��ĵ�ֵ");
        for (Complex complex : FFT.complexes_A) {
            System.out.println(complex.real + "+" + complex.image + "i\n");
        }

        FFT.FFT_iteration(FFT.complexes_A, FFT.inv, FFT.multinomial_A.length);

        FFT.res = new Double[FFT.n];

        for (int i = 0; i < FFT.n; i++) {
            FFT.res[i] = Math.floor(FFT.complexes_A[i].real / FFT.multinomial_A.length + 0.5);
        }

        System.out.println("ͨ����任��ԭ�Ķ���ʽ��");
        FFT.outMultinomialReversed(FFT.res);

    }

    public static void init(int n, Complex[] omega, Complex[] inv) {
        for (int i = 0; i < n; i++) {
            omega[i] = new Complex(Math.cos(2 * Math.PI * i / n), Math.sin(2 * Math.PI * i / n));
            inv[i] = omega[i].conj();
        }
    }

    public static void main(String[] args) {

        // ͨ���޸� n �� maxvalue ��������
        // �������ʽ�������

        // ����ʽϵ������
        int n = 4;
        // ϵ�����ֵ
        int maxvalue = 10;
        runFFTRecursion(n, maxvalue);
        runFFTiteration(n, maxvalue);

    }
}
