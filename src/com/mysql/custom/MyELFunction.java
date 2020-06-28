package com.mysql.custom;

import java.util.List;

/**
 * 自定义 EL 函数库：
 * 类中定义 1～N 个静态的、有返回值的函数
 */
public class MyELFunction {

    /**
     * 加
     * @param args  jsp 中 ... 不支持
     * @return
     */
    public static int add(List<Integer> args){
        int sum = 0;
        for (int temp : args) {
            sum += temp;
        }
        return sum;
    }

    /**
     * 减
     * @param x
     * @param y
     * @return
     */
    public static int sub(int x, int y){
        return (x - y);
    }

    /**
     * 乘
     * @param x
     * @param y
     * @return
     */
    public static int adv(int x, int y){
        int ans = x * y;
        return ans;
    }

    /**
     * 除
     * @param x
     * @param y
     * @return
     * @throws ArithmeticException
     */
    public static double div(int x, int y) throws ArithmeticException {
        double ans = 0;
        try {
            ans = x / y;
        } catch (ArithmeticException e){
            throw e; //向上抛出异常
        } finally {
        }
        return ans;
    }

    /**
     * 验证是否是邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if (email == null || email.equals("")){
            return false;
        }
        String regex = "[a-zA-Z0-9]\\w+@\\w+\\.(com|cn)";
        return email.matches(regex);
    }

}
