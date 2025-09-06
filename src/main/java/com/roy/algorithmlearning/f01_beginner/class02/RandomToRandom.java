package com.roy.algorithmlearning.f01_beginner.class02;

// 知道有一个方法f1()可以得到1-5的等概率随机数， 现在不能用其他的概率函数， 要得到一个方法g1()可以得到1-7的等概率随机数
public class RandomToRandom {

    public static void main(String[] args) {
        int repeatTimes = 10000000;
        //验证f2()得到的0和1时等概率的
        int count = 0;
        for (int i = 0; i < repeatTimes; i++) {
            if (f2() == 0) {
                count++;
            }
        }
        System.out.println(count);
        System.out.println((double) count / (double) repeatTimes);
        System.out.println();

        //验证0-7是等概率得到的
        int [] countNum = new int[8];
        for (int i = 0; i < repeatTimes; i ++){
            int ans = f3();
            countNum[ans]++;
        }
        for (int i =0; i<countNum.length; i++){
            System.out.println("得到"+ i + "的次数为："+ countNum[i]);
        }
        System.out.println();

        //验证1-7是等概率得到的
        int [] countNum1 = new int[8];
        for (int i = 0; i < repeatTimes; i ++){
            int ans = g();
            countNum1[ans]++;
        }
        for (int i =0; i < countNum1.length; i++){
            System.out.println("得到"+ i + "的次数为："+ countNum1[i]);
        }


    }

    //这个方法不能修改
    public static int f1() {
        return (int) (Math.random() * 5) + 1;
    }

    //由f1()得到一个 0和1 的等概率发生器
    public static int f2() {
        int ans = 0;
        //如果ans==3的话，就重做， 所以只会得到1，2 和 4，5，四个数的概率分别是25%
        do {
            ans = f1();
        } while (ans == 3);
        // 得到的时1，2，那么返回0；得到的时4，5，那就返回1， 这个就是等概率的0和1
        return ans < 3 ? 0 : 1;
    }

    //由0和1的等概率发生器可以得到 0到任意的数字范围 的等概率发生器
    //通过结果左移的方法，可以得到二进制数对应的值， 那么三个二进制位可以表示000-111，即0-7（4+2+1）
    public static int f3() {
//        return (f2()<<2) + (f2()<<1) + (f2()<<0);
        return (f2()<<2) + (f2()<<1) + f2();
    }

    //可以得到0-6的等概率方法
    public static int f4() {
        int ans = 0;
        //遇到7就重做，这里会等概率得到0-6
        do {
            ans= f3();
        }while (ans == 7);
        return ans;
    }

    //目标方法
    public static int g() {
        return f4()+1;

    }

    //那么从给定一个从x-y的等概率随机数方法，要得到 a-b 的等概率随机数方法
    //思路： 1. 我们可以先得到 0-(b-a) 范围的随即方法，结果在加上 a 就是想要的方法
    //      2. 先用 x-y 的随机数方法，如果奇数个，中间一个得到就重做，两边得到反别返回0或1， 可以得到0和1的等概率方法
    //      3. 由0和1 的等概率方法可以得到0-z的等概率随机数方法， z要大于等于b，可以用二进制来思考需要调用几次上面的方法（1+2+...+2^n >= b）
    //      4. 如果得到的数大于b-a，就重做，就可以得到 0-(b-a)范围的随机方法


}
