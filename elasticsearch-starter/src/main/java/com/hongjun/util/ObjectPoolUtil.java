package com.hongjun.util;

import com.google.common.collect.Queues;

import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;

public class ObjectPoolUtil<T> {
    /**
     * 存放最大数量
     */
    private final int maxPoolSize;

    /**
     * 存放的数据队列
     */
    private final Queue<T> objPool;

    /**
     * 函数式接口,只包含一个无参的方法get(),返回一个泛型对象
     */
    private final Supplier<T> defaultObjectSupplier;

    /**
     * 对象工厂
     * Object[] 入参数组
     * T 返回泛型对象
     */
    private final Function<Object[], T> objFactory;

    public ObjectPoolUtil(int maxPoolSize, Supplier<T> defaultObjectSupplier, Function<Object[], T> objFactory) {
        this.maxPoolSize = maxPoolSize;
        this.objPool = Queues.newConcurrentLinkedQueue();
        this.defaultObjectSupplier = defaultObjectSupplier;
        this.objFactory = objFactory;
    }


    /**
     * 从对象池中获取一个对象，若对象池为空，则创建一个新对象
     * @param args 创建新对象时传入的参数
     * @return 从对象池中获取的对象
     */
    public synchronized T borrowObject(Object... args) {
       /* // 直接先从对象池中获取对象
        T obj = objPool.poll();
        if (obj == null) {
            obj = createObject(args);
        }
        return obj;*/

        T obj = objPool.poll();
        if (obj == null) {
            // 如果对象池中没有对象，则创建一个新对象
            obj = objFactory.apply(args);
        }
        return obj;
    }


    /**
     * 归还一个对象到对象池中
     * @param obj 归还的对象
     */
    public synchronized void returnObject(T obj) {
       /* if (obj == null) {
            return;
        }
        // 若对象池未满并且对象池并未包含此归还对象，则将对象归还到对象池中
        if (objPool.size() < maxPoolSize && !objPool.contains(obj)) {
            objPool.add(obj);
        }*/
        if (objPool.size() < maxPoolSize) {
            objPool.offer(obj);
        }
    }
}
