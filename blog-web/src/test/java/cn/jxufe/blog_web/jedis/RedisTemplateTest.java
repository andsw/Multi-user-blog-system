package cn.jxufe.blog_web.jedis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author hsw
 * @date 2020/3/7 9:56 下午
 */
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Test
    public void testTemplate() {
        redisTemplate.opsForValue().append("firstKey", "firstValue");
        System.out.println(redisTemplate.opsForValue().get("firstKey"));
        redisTemplate.delete("firstKey");
        System.out.println(redisTemplate.opsForValue().get("firstKey"));
    }
}
