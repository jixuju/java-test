package com.superwind.test2ratelimit.service;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by jiangxj on 2017/7/13.
 */
@Getter
@ToString
public abstract class RateLimitPolicy {
    private final long intervalInMills;
    private final long capacity;
    private final long maxBurstTokens;
    private final long intervalPerPermit;

    RateLimitPolicy(long capacity, long intervalInMills, long maxBurstTime) {
        this.capacity = capacity;
        this.intervalInMills = intervalInMills;
        intervalPerPermit = intervalInMills / capacity;
        maxBurstTokens = Math.min(maxBurstTime/intervalPerPermit, capacity);
    }

    public abstract String genBucketKey(String identity);
}
