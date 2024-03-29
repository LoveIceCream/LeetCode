package 算法实验;

import java.util.Scanner;

/**
 * 复数类
 *
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/5/25 10:29 上午
 */
public class Complex {
    double real = 0.00;  // 实部
    double image = 0.00; // 虚部

    Complex() {  // 不带参数的构造方法
        Scanner input = new Scanner(System.in);
        double real = input.nextDouble();
        double image = input.nextDouble();
        Complex(real, image);
    }

    private void Complex(double real, double image) { // 供不带参数的构造方法调用
        this.real = real;
        this.image = image;
    }

    Complex(double real, double image) { // 带参数的构造方法
        this.real = real;
        this.image = image;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImage() {
        return image;
    }

    public void setImage(double image) {
        this.image = image;
    }

    Complex add(Complex a) { // 复数相加
        double real2 = a.getReal();
        double image2 = a.getImage();
        double newReal = real + real2;
        double newImage = image + image2;
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    Complex sub(Complex a) { // 复数相减
        double real2 = a.getReal();
        double image2 = a.getImage();
        double newReal = real - real2;
        double newImage = image - image2;
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    Complex mul(Complex a) { // 复数相乘
        double real2 = a.getReal();
        double image2 = a.getImage();
        double newReal = real * real2 - image * image2;
        double newImage = image * real2 + real * image2;
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    Complex div(Complex a) { // 复数相除
        double real2 = a.getReal();
        double image2 = a.getImage();
        double newReal = (real * real2 + image * image2) / (real2 * real2 + image2 * image2);
        double newImage = (image * real2 - real * image2) / (real2 * real2 + image2 * image2);
        Complex result = new Complex(newReal, newImage);
        return result;
    }

    Complex conj() {
        Complex result = new Complex(real, -image);
        return result;
    }

    public void print() { // 输出
        if (image > 0) {
            System.out.println(real + " + " + image + "i");
        } else if (image < 0) {
            System.out.println(real + "" + image + "i");
        } else {
            System.out.println(real);
        }
    }
}