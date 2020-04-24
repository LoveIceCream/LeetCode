package com.company.easy;

/**
 * @author : wangzhen
 * create at:  2020/4/22  9:56 下午
 * @description: 263.丑数
 */
public class QNumber263 {

    public static void main(String[] args) {
       System.out.println( isUgly(13));
    }

    public static boolean isUgly(int num) {
        if(num == 0)return false;
        int flag = 0;   //记录num 是否能够被 2 3 5 整除
        do{
            if(num % 5 ==0){
                num = num/5;
            }else{
                flag++;
            }
        }while(flag == 0 );
        flag = 0;
        do{
            if(num % 3 ==0){
                num = num/3;
            }else{
                flag++;
            }
        }while(flag == 0 );
        flag = 0;
        do{
            if(num % 2 ==0){
                num = num/2;
            }else{
                flag++;
            }
        }while(flag == 0 );

        if( num == 1){
            return true;
        }else{
            return false;
        }
    }
}
