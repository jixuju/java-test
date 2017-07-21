package com.superwind.test2ratelimit.service;

/**
 * Created by jiangxj on 2017/7/13.
 */
public class UserRateLimitPolicy extends RateLimitPolicy {
    public UserRateLimitPolicy(long capacity, long intervalInMills, long maxBurstTime) {
        super(capacity, intervalInMills, maxBurstTime);
    }

    @Override
    public String genBucketKey(String identity) {
        return "RATE_LIMITER:TOKEN_BUCKET:" + getIntervalInMills() + ":" + getCapacity() + ":" + identity;
    }
}
