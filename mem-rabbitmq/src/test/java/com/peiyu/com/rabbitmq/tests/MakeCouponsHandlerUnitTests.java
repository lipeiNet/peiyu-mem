package com.peiyu.com.rabbitmq.tests;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/12/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-rabbitmq-consumer.xml"})
public class MakeCouponsHandlerUnitTests {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-rabbitmq-consumer.xml");
        System.out.println("启动消息监听");
    }
}
