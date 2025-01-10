package com.vanky.vankyutils.distributedlock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * @author vanky
 * @create 2025/1/8 22:09
 * @description
 */
@Slf4j
public class DistributedLockRedisImpl implements DistributedLock {

    RedisTemplate<String, String> redisTemplate;
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    // 锁自动过期时间
    private int lockExpireTime = 10;
    // 重试次数
    private static final int RETRY_TIMES = 10;
    // 默认值
    private static final String DEFAULT_VALUE = "DEFAULT_VALUE";

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
        return () -> innerLock(key, DEFAULT_VALUE, lockExpireTime);
    }

    @Override
    public boolean lock(String key) {
        if (!StringUtils.hasText(key)) {
            log.error("加锁失败，key 为空！");
            return false;
        }
        return tryLock(key, DEFAULT_VALUE, lockExpireTime);
    }

    @Override
    public boolean unlock(String key) {
        if (!StringUtils.hasText(key)) {
            log.error("解锁失败，key 为空！");
            return false;
        }
        return innerUnlock(key, DEFAULT_VALUE);
    }

    @Override
    public boolean lock(String key, String value) {
        if (!(StringUtils.hasText(key) && StringUtils.hasText(value))){
            log.error("加锁失败，key 或 value 为空！");
        }
        return tryLock(key, value, lockExpireTime);
    }

    @Override
    public boolean unlock(String key, String value) {
        if (!(StringUtils.hasText(key) && StringUtils.hasText(value))){
            log.error("解锁失败，key 或 value 为空！");
        }
        return innerUnlock(key, value);
    }

    private Boolean tryLock(String key, String value, Integer lockExpireTime) {
        int retry = 0;
        while (retry < RETRY_TIMES) {
            if (innerLock(key, value, lockExpireTime)) {
                log.info("获取锁成功 key：{}， value：{}， 重试次数：{}", key, value, retry);
                return true;
            }
            retry++;
        }
        log.warn("获取锁失败 key：{}， value：{}， 重试次数：{}", key, value, RETRY_TIMES);
        return false;
    }

    /**
     * redis 加锁操作
     * @param key
     * @param value
     * @param lockExpireTime
     * @return
     */
    private Boolean innerLock(String key, String value, int lockExpireTime) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(stringRedisSerializer.serialize(key),
                stringRedisSerializer.serialize(value),
                Expiration.seconds(lockExpireTime), RedisStringCommands.SetOption.SET_IF_ABSENT));
    }

    private static final RedisScript<Long> unlockScript = RedisScript
            .of("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end", Long.class);

    /**
     * LUA 脚本保证解锁操作的原子性，先判断值，后解锁
     * @param key
     * @param value
     * @return
     */
    private boolean innerUnlock(String key, String value) {
        Long execute = redisTemplate.execute(unlockScript, Collections.singletonList(key), value);
        return execute != null && execute.equals(1L);
    }
}
