package com.superwind.test3jdk8features.service;

/**
 * Created by jiangxj on 2017/8/12.
 */
@FunctionalInterface
public interface Formula {
    double calculate(int a);
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
