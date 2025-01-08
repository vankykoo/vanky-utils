package com.vanky.vankyutils.distributedlock;

/**
 * @author vanky
 * @create 2025/1/8 22:07
 * @description 分布式锁接口
 */
public interface DistributedLock extends AutoClosableDistributedLock {

    /**
     * 加锁
     * @param key
     * @return
     */
    boolean lock(String key);

    /**
     * 解锁
     * @param key
     * @return
     */
    boolean unlock(String key);
}
