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

    /**
     * 加锁支持设置值
     * @param key 锁对应键
     * @param value 锁对应值
     * @return
     */
    boolean lock(String key, String value);

    /**
     * 解锁需要键值对应
     * @param key 锁对应键
     * @param value 锁对应值
     * @return
     */
    boolean unlock(String key, String value);
}
