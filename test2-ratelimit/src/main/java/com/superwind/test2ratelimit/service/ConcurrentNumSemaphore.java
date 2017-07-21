package com.superwind.test2ratelimit.service;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiangxj on 2017/7/10.
 * 控制并发数模式-通过信号量
 */
public class ConcurrentNumSemaphore {
    private final Semaphore permit = new Semaphore(10, true);

    public void process(){
        try{
            permit.acquire();
            //业务逻辑处理

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            permit.release();
        }
    }
}
