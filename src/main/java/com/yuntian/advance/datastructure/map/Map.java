package com.yuntian.advance.datastructure.map;

/**
 * @Auther: yuntian
 * @Date: 2019/7/27 0027 17:37
 * @Description:
 */
public interface Map<K, V> {

    void add(K k, V v);

    V remove(K k);

    boolean contains(K k);

    V get(K k);

    void set(K k, V v);

    int getSize();

    boolean isEmpty();

}
