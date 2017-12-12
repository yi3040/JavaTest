package com.example.demo.test;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.UUID;

/**
 * redis模拟生产者
 * Created by ywf on 17-11-30.
 */
public class TaskProducer   implements Runnable{
    Jedis jedis = new Jedis("10.0.0.176",6379);

    public void run() {
        Random random = new Random();
        while(true){
            try{
                Thread.sleep(random.nextInt(600) + 600);
                // 模拟生成一个任务
                UUID taskid = UUID.randomUUID();
                //将任务插入任务队列：task-queue
                jedis.lpush("task-queue", taskid.toString());//令将一个或多个值插入到列表头部。 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误。
                System.out.println("插入了一个新的任务： " + taskid);

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
