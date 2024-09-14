package priv.springboot.xgx;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedissonLockTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testLock() throws InterruptedException {
        RLock lock = redissonClient.getLock("lock");
        boolean isLock = lock.tryLock(1, 10, TimeUnit.SECONDS);
        if (isLock) {
            try {
                System.out.println("执行业务逻辑");
                Thread.sleep(1000);
            } finally {
                lock.unlock();
            }

        }
    }

    @Test
    public void testParallel() throws Exception {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                RLock lock = redissonClient.getLock("lock");
                boolean isLock = false;
                try {
                    isLock = lock.tryLock(1, 10, TimeUnit.MICROSECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (isLock) {
                    try {
                        ValueContant.temp++;
                        System.out.println(Thread.currentThread().getName() + "执行业务逻辑,结果" + ValueContant.temp);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }, "Thread-" + i);
            thread.join();
            thread.start();
        }
        // System.out.println(ValueContant.temp);
    }
}
