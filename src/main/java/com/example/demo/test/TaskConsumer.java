package com.example.demo.test;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 模拟消费者
 * Created by ywf on 17-11-30.
 */
public class TaskConsumer  implements Runnable{
    Jedis jedis = new Jedis("10.0.0.176",6379);

    public void run() {
        Random random = new Random();

        while(true){

            //从任务队列"task-queue"中获取一个任务，并将该任务放入暂存队列"tmp-queue"
            String taskid = jedis.rpoplpush("task-queue", "tmp-queue");//命令用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回。


            // 处理任务----纯属业务逻辑，模拟一下：睡觉
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //模拟成功和失败的偶然现象
            if(random.nextInt(13) % 7 == 0){// 模拟失败的情况,概率为2/13
                //将本次处理失败的任务从暂存队列"tmp-queue"中，弹回任务队列"task-queue"
                jedis.rpoplpush("tmp-queue", "task-queue");
                System.out.println(taskid + "处理失败，被弹回任务队列");

            } else {// 模拟成功的情况

                // 将本次任务从暂存队列"tmp-queue"中清除
                jedis.rpop("tmp-queue");//命令用于移除并返回列表的最后一个元素
                System.out.println(taskid+"处理成功，被清除");

            }
        }

    }

}
