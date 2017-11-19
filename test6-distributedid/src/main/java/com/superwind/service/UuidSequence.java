package com.superwind.service;

import java.util.UUID;

/**
 * Created by jiangxj on 2017/11/18.
 */
public class UuidSequence {
    public synchronized long nextId() {
        UUID uuid = UUID.randomUUID();
        return Long.valueOf(uuid.toString());
    }
}
