package com.vanky.vankyutils.distributedlock;

/**
 * @author vanky
 * @create 2025/1/8 22:17
 * @description
 */
public interface AutoClosableDistributedLock {

    /**
     * 尝试加锁
     * @param key
     * @return null：加锁失败
     */
    AutoClosableLock tryLock(String key);
}
