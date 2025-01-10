package com.vanky.vankyutils;

import com.vanky.vankyutils.distributedlock.DistributedLockRedisImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class VankyUtilsApplicationTests {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedisConnect(){
        DistributedLockRedisImpl distributedLockRedis = new DistributedLockRedisImpl(redisTemplate, 60);

        if (distributedLockRedis.lock("test_lock")) {
            System.out.println("加锁成功");
        } else {
            System.out.println("加锁失败");
        }

        try {
            Thread.sleep(3000);
            if (distributedLockRedis.unlock("test_lock")) {
                System.out.println("解锁成功");
            } else {
                System.out.println("解锁失败");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
