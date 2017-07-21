package com.superwind.test2ratelimit.service;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangxj on 2017/7/10.
 * 控制访问速率模式-令牌桶guava
 */
public class TokenBucketGuava {
    public static void main(String[] args) {
//        TokenBucket.testAcquire();
        TokenBucketGuava.testTryAcquire();
    }

    public static void testAcquire(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
        RateLimiter rateLimiter = RateLimiter.create(200);
        while(true) {
            rateLimiter.acquire();
            System.out.println(simpleDateFormat.format(new Date()));
        }
    }

    public static void testTryAcquire(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
        RateLimiter rateLimiter = RateLimiter.create(200);
        while(true) {
            boolean hasToken = rateLimiter.tryAcquire();
            System.out.println(hasToken+" "+simpleDateFormat.format(new Date()));
        }
    }
}
