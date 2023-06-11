package com.roy.algorithmlearning.beginner.class02;

//有一个 不等概率 得到 0和1 的方法f1()，现在要得到 等概率 的得到 0和1 的方法g()
public class RandomToRandom1 {

    public static void main(String[] args) {

        int repeatTimes = 10000000;
        //验证 0 和1 是等概率得到的
        int [] countNum = new int[2];
        for (int i = 0; i < repeatTimes; i ++){
            int ans = g();
            countNum[ans]++;
        }
        for (int i =0; i < countNum.length; i++){
            System.out.println("得到"+ i + "的次数为："+ countNum[i]);
        }

    }

    //这个是黑盒，不可看到，不可修改
    public static int f1() {
        return Math.random() < 0.76 ? 0 : 1;
    }

    public static int g() {
        int ans = 0;
        //如果两次f1()的结果相同，那么重做，不相同则返回，这个返回 0 或 1 的概率为 p* (1-p),
        do {
            ans = f1();
        }while(ans == f1());
        return ans;
    }
}
