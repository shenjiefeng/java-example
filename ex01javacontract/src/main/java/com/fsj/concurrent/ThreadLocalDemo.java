package com.fsj.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author fengshenjie
 * @date 19/5/7 17:21
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {

        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        InnerClass innerClass = new InnerClass();
        for (int i = 1; i <= threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            }, "thread - " + i).start();
        }
        countDownLatch.await();

    }

    private static class InnerClass {

        public void add(String newStr) {
            //方法1
//            StringBuilder str = Counter.counter.get();
//            Counter.counter.set(str.append(newStr));

            // 方法2，等价，取出sb引用类型直接append
            Counter.counter.get().append(newStr);
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }

        public void set(String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {

        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            /**
             *  父类为public 方法，且默认返回 null，这里重载ThreadLocal的initialValue
             * @return
             */
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };

    }

}

