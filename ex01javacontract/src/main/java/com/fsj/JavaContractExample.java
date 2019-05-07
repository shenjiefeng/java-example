package com.fsj;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class JavaContractExample implements Cloneable {

    /**
     * 声明为final的情况：不需要重新赋值的变量 / 对象参数不允许修改引用的指向 / 类方法不允许重写
     */
    public final String contractName = "alibaba-java-contract";

    @Getter
    @Setter
    private String name;

    // ## 5.4 OOP规约

    /**
     * 5.4.7 所有的相同类型的包装类对象之间值的比较,全部使用 equals 方法
     * 5.4.8 POJO类属性和RPC方法返回值使用包装类型，局部变量使用基本数据类型。
     */

    /**
     * 5.4.13 注意split陷阱，避免IndexOutOfBoundsException
     * 第一个参数是正则表达式，如果要按照.分割，应该写作 \\.
     * limit默认为0，会丢弃最后的空子串： If limit is zero then
     * the pattern will be applied as many times as possible, the array can
     * have any length, and trailing empty strings will be discarded.
     */
    private static void egSplit() {
        String s = "a,b,c,,,";
        Println(s.split(",").length);//3
        Println(s.split(",", -1).length);//6
        s = "a.b.c";
//        for(String i: s.split("\\.",-1)) System.out.println(i);
        Println(s.split(".", -1));
    }

    /**
     * 通用打印
     * 5.4.17 字符串联接方式：使用 StringBuilder 的 append 方法进行扩展效率更高
     *
     * @param os 可变参数，object类型
     */
    private static void Println(Object... os) {
        StringBuilder sb = new StringBuilder();
        for (Object o : os) {
            sb.append(o).append(" ");
        }

        System.out.println(sb.toString());
    }


    /**
     * 5.4.19 对象的clone方法默认为浅拷贝
     *
     * @return
     */
    @Override
    public JavaContractExample clone() {
        try {
            return (JavaContractExample) super.clone();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // ## 5.5 集合规约

    /**
     * 5.5.2 subList 返回的是 ArrayList 的内部类 SubList,并不是 ArrayList ,而是 ArrayList 的一个视图,对于SubList子列表的所有操作最终会反映到原列表上
     */
    private static void egArrayList() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        Object[] objects = list.toArray();//返回 Object[], 不良用法
        String[] arrs = list.toArray(new String[0]);//容量不够时会自动分配内存空间，并返回新数组地址。
        Println(arrs, arrs.length);
        arrs = list.toArray(new String[list.size()]);
        Println(arrs, arrs.length);


        List<String> sublist = list.subList(0, 1);
        //java.lang.ClassCastException: java.util.ArrayList$SubList cannot be cast to java.util.ArrayList
//        Println((ArrayList<String>)sublist);
        sublist.remove(0);
        Println(list);
    }

    /**
     * 5.5.13  Map 类集合 K/V 能不能存储 null 值的情况
     * HashMap
     * TreeMap
     * ConcurrentHashMap
     * Hashtable
     */
    private static void egMap() {
        //todo
    }

    /**
     * 1.6.2 创建线程或线程池时请指定有意义的线程名称,方便出错时回溯
     */
    private static void egThread(){
        class TimerTaskThread extends Thread{
            public TimerTaskThread(){
                super.setName("my-time-task-thread");
            }
        }
        TimerTaskThread taskThread=new TimerTaskThread();
        Println(taskThread);
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Println("#java contract example:");
//        Println(1, 2, "three");
//        Println();
//        egSplit();

//        JavaContractExample jc = new JavaContractExample();
//        JavaContractExample jc1 = jc.clone();
//        Println(jc, jc1, jc == jc1, jc.equals(jc1));

//        egArrayList();
        egThread();
    }
}
