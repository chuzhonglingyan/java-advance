package com.yuntian.advance.datastructure.list;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/7/16 0016 22:33
 * @Description:
 */
public class ArrayList<E> implements List<E> {

    private E[] data;

    private static int initCapacity = 8;

    /**
     * 数组中实际元素数量
     */
    private int size;


    public ArrayList() {
        this(initCapacity);
    }

    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }


    /**
     * 复杂度O(n)
     * @param e
     */
    public void addFist(E e) {
        add(e, 0);
    }

    /**
     * 复杂度O(1)
     * @param e
     */
    public void addLast(E e) {
        add(e, size);
    }


    /**
     * 复杂度O(n)
     */
    public E removeFirst() {
        return remove(0);
    }


    /**
     * 复杂度O(1)
     */
    public E removeLast() {
        return remove(size-1);
    }



    /**
     *
     *
     * @param e
     */
    @Override
    public void add(E e) {
        add(e, size);
    }

    /**
     *  复杂度O(n)
     * @param e
     * @param index
     */
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed. index mismatch condition");
        }
        //1.判断数组是否满容
        if (isFull()){
            resize(capacity()*2);
        }
        //2.插入元素,需要将插入位置的元素以及后续元素向右移动(数组末端)
        for (int i = index; i <size ; i++) {
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }


    /**
     * [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
     * [1,2,3,4,5,6,7,9,0,0,0,0,0,0,0,0]
     * [1,2,3,4,6,7,9,0,0,0,0,0,0,0,0,0]
     * [1,2,7,9,0,0,0,0,0,0,0,0,0,0,0,0]
     * [1,2,7,9,0,0,0,0]
     * 复杂度O(n)
     * @param index
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >=size) {
            throw new IllegalArgumentException("remove failed. index mismatch condition");
        }
        E ret = data[index];
        //2.删除元素,需要将删除位置的元素以及后面元素向左移动(数组首端)
        for (int i = index+1; i <size ; i++) {
            data[i-1]=data[i];
        }
        //原来最后一个元素的引用还存在,因此要释放掉引用
        data[size-1]=null;
        size--;
        //满足容量缩减到一个程度
        if (size==capacity()/4&&capacity()/2!=0){
            resize(capacity()/2);
        }

        return ret;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >=size) {
            throw new IllegalArgumentException("get failed. index mismatch condition");
        }
        return data[index];
    }



    public void set(int index,E e) {
        if (index < 0 || index >=size) {
            throw new IllegalArgumentException("set failed. index mismatch condition");
        }
        data[index]=e;
    }


    public int  indexOf(E e){
        for (int i = 0; i < size; i++) {
            if (data[i]==e){
                return i;
            }
        }
        return  -1;
    }




    private int capacity(){
        return  data.length;
    }

    public void  resize(int newCapacity){
        E[] oldData=data;
        data=(E[]) new Object[newCapacity];
        System.arraycopy(oldData,0,data,0,size);
    }




    public boolean isFull() {
        return size == data.length;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }


    /**
     * 打印数组信息及遍历元素。
     *
     * @return 数组信息和元素遍历结果
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }


}
