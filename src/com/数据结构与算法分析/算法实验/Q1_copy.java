package 算法实验;

import java.util.Random;

/**
 * 一、分治法
 * 求解问题：FFT算法
 * 说明离散FOURIER变换的定义，用分治法给出计算该变换的算法，并注意如何将递归形式的算法转换成迭代形式。
 * 比较直接计算和FFT算法的复杂度，探讨相关思想如何应用在其它一些变换上得到相应的快速算法。
 * https://www.cnblogs.com/RabbitHu/p/FFT.html
 * https://www.cnblogs.com/RabbitHu/p/BZOJ2194.html
 * <p>
 * https://blog.csdn.net/DreamlessDreams/article/details/79795618
 * <p>
 * https://blog.csdn.net/xienaoban/article/details/69486299
 * <p>
 * 计算两个多项式的乘积
 *
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/12 3:10 下午
 */
public class Q1_copy {

    int[] multinomial_A;
    int[] multinomial_B;

    Double[] res;

    Complex[] complexes_A;
    Complex[] complexes_B;
    Complex[] omega;
    Complex[] inv;

    int n = 1;
    static Complex[] buf;

    public Q1_copy() {
        multinomial_A = getRandomCoefficient(4, 10);
        multinomial_B = getRandomCoefficient(4, 10);
    }

    /**
     * 递归fft
     *
     * @param complex
     * @param n
     * @param isReciprocal
     */
    public void FFT_Recursion(Complex[] complex, int startIndex, int n, Complex[] omegas) {
        System.out.println("递归调用 FFT_Recursion startIndex=" + startIndex + "；n=" + n);
        if (n == 1) {
            System.out.println("n==1,reutrn");
            return;
        }
        int m = n / 2;
        for (int i = startIndex; i < startIndex + m; i++) { //将每一项按照奇偶分为两组
            buf[i] = complex[2 * i - startIndex];
            buf[i + m] = complex[2 * i + 1 - startIndex];
        }
        for (int i = startIndex; i < startIndex + n; i++) {
            complex[i] = buf[i];
        }
        FFT_Recursion(complex, startIndex, m, omegas); //递归处理两个子问题
        FFT_Recursion(complex, startIndex + m, m, omegas);
        for (int i = startIndex; i < startIndex + m; i++) { //枚举x，计算A(x)
            Complex ome = omegas[i];
            Complex wa = ome.mul(complex[i + m]);
            buf[i] = complex[i].add(wa); //根据之前推出的结论计算
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
            // i < t 的限制使得每对点只被交换一次（否则交换两次相当于没交换）
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
     * 获取多项式系数 数组 int[] a={1,2,3}  a[0]==1 代表x的0次幂项的系数为1
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

    public void outMultinomialReversed(int[] multinomial) {
        String multinomialStr = "";
        for (int i = multinomial.length - 1,k = 0; i >=0; i--,k++) {
            if (multinomialStr == "") {
                multinomialStr += multinomial[i] + "x^" + k;
            } else {
                multinomialStr += " + " + multinomial[i] + "x^" + k;

            }
        }
        System.out.println(multinomialStr);
    }


    public void outMultinomial(Double[] multinomial) {
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
        for (int i = multinomial.length - 1,k=0; i >=0; i--,k++) {
            if (multinomialStr == "") {
                multinomialStr += multinomial[i] + "x^" + k;
            } else {
                multinomialStr += " + " + multinomial[i] + "x^" + k;

            }
        }
        System.out.println(multinomialStr);
    }


    public Complex getOmega(int n, int k) {
        Complex complex = new Complex(Math.cos(2 * Math.PI * k / n), Math.sin(2 * Math.PI * k / n));
        return complex;
    }

    public static void runFFTRecursion() {
        // 实例化 Q1 并初始化 系数
        Q1_copy FFT = new Q1_copy();

        // 打印输出两个多项式
        FFT.outMultinomial(FFT.multinomial_A);
        FFT.outMultinomial(FFT.multinomial_B);

        // 2的整数次幂
        while (FFT.n < (FFT.multinomial_A.length + FFT.multinomial_B.length)) {
            FFT.n = FFT.n * 2;
        }

        FFT.complexes_A = new Complex[FFT.n];
        FFT.complexes_B = new Complex[FFT.n];

        FFT.omega = new Complex[FFT.n];;
        FFT.inv = new Complex[FFT.n];;

        init(FFT.n, FFT.omega, FFT.inv);

        // 初始化 complexes_A complexes_B

        System.out.println("初始化 complexes_A");
        for (int i = 0; i < FFT.n; i++) {
            if (i>= FFT.multinomial_A.length){
                Complex complex = new Complex(0, 0);
                FFT.complexes_A[i] = complex;
                continue;
            }
            int index = FFT.multinomial_A.length - 1 - i;
            Double value = new Double(FFT.multinomial_A[index]);
            Complex complex = new Complex(value, 0);
            FFT.complexes_A[i] = complex;
        }

        System.out.println("初始化 complexes_B");
        for (int i = 0; i < FFT.n; i++) {
            if (i>= FFT.multinomial_B.length){
                Complex complex = new Complex(0, 0);
                FFT.complexes_B[i] = complex;
                continue;
            }
            int index = FFT.multinomial_B.length - 1 - i;
            Double value = new Double(FFT.multinomial_B[index]);
            Complex complex = new Complex(value, 0);
            FFT.complexes_B[i] = complex;
        }


        buf = new Complex[FFT.n];
        FFT.FFT_Recursion(FFT.complexes_A, 0, FFT.multinomial_A.length, FFT.omega);

        buf = new Complex[FFT.n];
        FFT.FFT_Recursion(FFT.complexes_B, 0, FFT.multinomial_B.length, FFT.omega);

        System.out.println("输出 complexes_A");
        for (int i = 0; i < FFT.n; i++) {
            Complex complex = FFT.complexes_A[i];
            System.out.println(complex.real + "+" + complex.image + "i\n");
        }
        System.out.println("输出 complexes_B");
        for (int i = 0; i < FFT.n; i++) {
            Complex complex = FFT.complexes_B[i];
            System.out.println(complex.real + "+" + complex.image + "i\n");
        }

        // a b 相乘
        for (int i = 0; i < FFT.n; i++) {
            FFT.complexes_A[i].mul(FFT.complexes_B[i]);
        }
        // IDFT
        FFT.FFT_Recursion(FFT.complexes_B, 0, FFT.multinomial_A.length, FFT.inv);
        // 取实部
        FFT.res = new Double[FFT.n];

        for (int i = 0; i < FFT.n; i++) {
            FFT.res[i] = Math.floor(FFT.complexes_A[i].real / FFT.multinomial_A.length + 0.5);
        }

        FFT.outMultinomialReversed(FFT.res);
        //输出


    }

    public static void runFFTiteration() {
        // 输入两个多项式 系数

        // 实例化 Q1 并初始化 系数
        Q1_copy FFT = new Q1_copy();

        FFT.outMultinomial(FFT.multinomial_A);
        FFT.outMultinomial(FFT.multinomial_B);

        // 2的整数次幂
        while (FFT.n < (FFT.multinomial_A.length + FFT.multinomial_B.length)) {
            FFT.n = FFT.n * 2;
        }

        FFT.complexes_A = new Complex[FFT.n];
        FFT.complexes_B = new Complex[FFT.n];

        FFT.omega = new Complex[FFT.n];;
        FFT.inv = new Complex[FFT.n];;

        init(FFT.n, FFT.omega, FFT.inv);

        // 初始化 complexes_A complexes_B

        System.out.println("初始化 complexes_A");
        for (int i = 0; i < FFT.n; i++) {
            if (i>= FFT.multinomial_A.length){
                Complex complex = new Complex(0, 0);
                FFT.complexes_A[i] = complex;
                continue;
            }
            int index = FFT.multinomial_A.length - 1 - i;
            Double value = new Double(FFT.multinomial_A[index]);
            Complex complex = new Complex(value, 0);
            FFT.complexes_A[i] = complex;
        }

//        for (int i = 0; i < FFT.multinomial_B.length; i++) {
//            FFT.complexes_B[i].setReal(FFT.multinomial_B[FFT.multinomial_B.length - 1 - i]);
//        }
        System.out.println("初始化 complexes_B");
        for (int i = 0; i < FFT.n; i++) {
            if (i>= FFT.multinomial_B.length){
                Complex complex = new Complex(0, 0);
                FFT.complexes_B[i] = complex;
                continue;
            }
            int index = FFT.multinomial_B.length - 1 - i;
            Double value = new Double(FFT.multinomial_B[index]);
            Complex complex = new Complex(value, 0);
            FFT.complexes_B[i] = complex;
        }

        buf = new Complex[FFT.n];
        FFT.FFT_iteration(FFT.complexes_A, FFT.omega, FFT.multinomial_A.length);

        buf = new Complex[FFT.n];
        FFT.FFT_iteration(FFT.complexes_B, FFT.omega, FFT.multinomial_B.length);

        System.out.println("输出 complexes_A");
        for (Complex complex : FFT.complexes_A) {
            System.out.println(complex.real + "+" + complex.image + "i\n");
        }
        System.out.println("输出 complexes_B");
        for (Complex complex : FFT.complexes_B) {
            System.out.println(complex.real + "+" + complex.image + "i\n");
        }

        // 两个复数数组相乘
//        for (int i = 0; i < FFT.n; i++) {
//            FFT.complexes_A[i] = FFT.complexes_A[i].mul(FFT.complexes_B[i]);
//        }
        FFT.FFT_iteration(FFT.complexes_A, FFT.inv, FFT.multinomial_A.length);

        FFT.res = new Double[FFT.n];

        for (int i = 0; i < FFT.n; i++) {
            FFT.res[i] = Math.floor(FFT.complexes_A[i].real / FFT.multinomial_A.length + 0.5);
        }

        FFT.outMultinomialReversed(FFT.res);

    }

    public static void init(int n,Complex[] omega,Complex[] inv) {
        for (int i = 0; i < n; i++) {
            omega[i] = new Complex(Math.cos(2 * Math.PI * i / n), Math.sin(2 * Math.PI * i / n));
            inv[i] = omega[i].conj();
        }
    }

    public static void main(String[] args) {
//        runFFTRecursion();
//
        runFFTiteration();


//        for (int i = 0; i < FFT.n; i++) {
//            FFT.omega[i] = FFT.getOmega(FFT.n, i);
//            FFT.inv[i] = FFT.omega[i].conj();
//        }
//
//        FFT.FFT_iteration(FFT.complexes_A, FFT.omega);
//        FFT.FFT_iteration(FFT.complexes_B, FFT.omega);
//
//        for (int i = 0; i < FFT.n; i++) {
//            FFT.complexes_A[i] = FFT.complexes_A[i].mul(FFT.complexes_B[i]);
//        }
//        FFT.FFT_iteration(FFT.complexes_A, FFT.inv);
//
//        int[] res = new int[99];
//
//        for (int i = 0; i < FFT.n; i++) {
//            res[i] += Math.floor(FFT.complexes_A[i].real / FFT.n + 0.5);
//            res[i + 1] += res[i] / 10;
//            res[i] %= 10;
//        }
//        for (int i = res[FFT.multinomial_A.length + FFT.multinomial_B.length - 1] >= 0
//                ? FFT.multinomial_A.length + FFT.multinomial_B.length - 1 :
//                FFT.multinomial_A.length + FFT.multinomial_B.length - 2; i >= 0; i--) {
//            res[i] = '0' + res[i];
//        }

//        FFT.outMultinomial(FFT.multinomial_A);
//        FFT.outMultinomial(FFT.multinomial_B);
    }
}
