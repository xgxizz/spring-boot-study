package priv.springboot.xgx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedisOperations() {
        // Set a key-value pair
        redisTemplate.opsForValue().set("key", "value");

        // Get the value for the key
        String value = redisTemplate.opsForValue().get("key");
        assertEquals("value", value);

        // Delete the key
        redisTemplate.delete("key");

        // Check if the key exists
        boolean exists = redisTemplate.hasKey("key");
        assertEquals(false, exists);
    }

    @Test
    public void test() {
        redisTemplate.opsForValue().setIfPresent("name", "xgx", 10, TimeUnit.SECONDS);
    }
}