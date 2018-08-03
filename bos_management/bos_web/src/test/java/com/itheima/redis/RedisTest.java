package com.itheima.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RedisTest {
    @Test
    public void test(){
        Jedis jedis = new Jedis("localhost");
        String name = jedis.set("name", "xiaoming");
        String value=jedis.get("name");
        System.out.println(name+"       "+value);

    }


    @Autowired
        private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test02(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        //新增
        ops.set("name","小明");

        //查询
        String s = ops.get("name");
        System.out.println(s);

    }


}
