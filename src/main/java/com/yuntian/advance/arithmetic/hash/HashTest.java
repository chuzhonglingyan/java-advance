package com.yuntian.advance.arithmetic.hash;

/**
 * @Auther: yuntian
 * @Date: 2019/7/28 0028 17:38
 * @Description:
 */
public class HashTest {


    public static void main(String[] args) {

        System.out.println(additiveHash("233232w3", 11));
        System.out.println(additiveHash("232323232", 11));
        System.out.println("cee9a457e790cf20d4bdaa6d69f01e41".length());
    }


    public static int hashCode(String key) {
        int h = 0;
        if (key.length() > 0) {
            char val[] = key.toCharArray();
            for (int i = 0; i < key.length(); i++) {
                h = 31 * h + val[i];
            }
        }
        return h;
    }


    /**
     * 加法Hash prime是任意的质数，看得出，结果的值域为[0,prime-1]。
     *
     * @param key
     * @param prime
     * @return
     */
    public static int additiveHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return (hash % prime);
    }


    /**
     * 位运算Hash
     *
     * @param key
     * @param prime
     * @return
     */
    public static int rotatingHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); ++i) {
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        }
        return (hash % prime);
    }


}
