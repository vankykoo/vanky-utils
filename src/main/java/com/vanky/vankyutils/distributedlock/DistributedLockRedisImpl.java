package com.vanky.vankyutils.distributedlock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author vanky
 * @create 2025/1/8 22:09
 * @description
 */
public class DistributedLockRedisImpl implements DistributedLock {

    RedisTemplate<String, String> redisTemplate;
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    // 锁自动过期时间
    private int lockExpireTime = 10;

    public DistributedLockRedisImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public DistributedLockRedisImpl(RedisTemplate<String, String> redisTemplate, int lockExpireTime) {
        this.redisTemplate = redisTemplate;
        this.lockExpireTime = lockExpireTime;
    }

    public int getLockExpireTime() {
        return lockExpireTime;
    }

    public void setLockExpireTime(int lockExpireTime) {
        this.lockExpireTime = lockExpireTime;
    }

    @Override
    public AutoClosableLock tryLock(String key) {
        return null;
    }

    @Override
    public boolean lock(String key) {
        return false;
    }

    @Override
    public boolean unlock(String key) {
        return false;
    }
}
