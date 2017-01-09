import com.peiyu.mem.redis.JedisTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-redis-config.xml"})
public class JedisTemplateUnitTests {
    @Autowired
    private JedisTemplate jedisTemplate;

    @Test
    public void testSet() {
        jedisTemplate.set("myredis", "helloworld");
        String result = jedisTemplate.get("myredis");
        assertEquals("helloworld", result);
    }
    @Test
    public void testFlushDB(){
        jedisTemplate.flushDB();
    }

}
