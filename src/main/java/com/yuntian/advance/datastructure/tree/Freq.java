package com.yuntian.advance.datastructure.tree;

/**
 * @Auther: yuntian
 * @Date: 2019/7/29 0029 21:39
 * @Description:
 */
public  class Freq implements Comparable<Freq> {

    public int e, freq;

    public Freq(int e, int freq) {
        this.e = e;
        this.freq = freq;
    }

    /**
     * 定义比较规则，频次越低优先级越高。
     *
     * @param another
     * @return
     */
    @Override
    public int compareTo(Freq another) {
        if (this.freq < another.freq) {
            return 1;
        } else if (this.freq > another.freq) {
            return -1; // 当前元素比传进来的元素要大的话，返回1。这是正常大则优先级高，我们这里颠倒一下即可。
        } else {
            return 0;
        }
    }
}