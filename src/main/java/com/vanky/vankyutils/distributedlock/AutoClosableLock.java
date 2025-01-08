package com.vanky.vankyutils.distributedlock;

/**
 * @author vanky
 * @create 2025/1/8 21:52
 * @description 支持自动解锁的抽象锁
 */
public interface AutoClosableLock extends AutoCloseable {

    /**
     * 释放资源方法
     */
    @Override
    default void close() {
        unlock();
    }

    /**
     * 解锁
     * @return
     */
    boolean unlock();
}
