package com.superwind.test2ratelimit.service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiangxj on 2017/7/13.
 * 控制并发数模式-通过原子计数器
 */
public class ConcurrentNumAtomic {
    private final AtomicInteger permit = new AtomicInteger(0);
    private final Integer limit = 100;

    public void process(){
        try{
            Integer cnt = permit.incrementAndGet();
            if (cnt < limit){
                //业务逻辑处理
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            permit.decrementAndGet();
        }
    }
}
