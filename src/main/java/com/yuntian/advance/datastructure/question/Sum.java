package com.yuntian.advance.datastructure.question;

/**
 * @Auther: yuntian
 * @Date: 2019/7/18 0018 23:46
 * @Description:
 */
public class Sum {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4};

        System.out.println(sum1(data));

        System.out.println(sum2(data,0));
    }

    public static int  sum1(int[] data){
        int sum=0;
        for (int aData : data) {
//            sum=sum+aData;
            sum +=aData;
        }
        return sum;
    }

    /**
     * s(1)=a[0]
     *
     * s(2)=a[0]+a[1]
     *
     * s(n-1)=a[0]+a[1]+..a[n-2]
     *
     * s(n)=a[0]+a[1]+..a[n-1]
     *
     * s(n)=s(n-1)+a[n-1]    n个
     *
     * @param data
     * @return
     */
    public static int  sum2(int[] data,int index){
        if (index==data.length){
            return  0;
        }
        int result=sum2(data,index+1);
        return data[index]+result;
    }
}
