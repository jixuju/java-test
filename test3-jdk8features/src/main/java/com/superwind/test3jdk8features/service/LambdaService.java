package com.superwind.test3jdk8features.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jiangxj on 2017/8/12.
 */
public class LambdaService {
    public void testDefault() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        formula.calculate(100);
        formula.sqrt(16);
    }

    public void testLambda() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        Collections.sort(names, (String a, String b)->{return b.compareTo(a);});
    }

    public static void main(String[] args) {
        LambdaService lambdaService = new LambdaService();
        lambdaService.testLambda();
    }
}
