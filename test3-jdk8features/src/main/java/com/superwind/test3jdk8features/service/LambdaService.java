package com.superwind.test3jdk8features.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.sqrt;

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
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }

    public void testFunctional() {
        Formula formula = a ->sqrt(a*100);

        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }

    public void testLambda() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        Collections.sort(names, (a, b)->{return b.compareTo(a);});
    }

    public static void main(String[] args) {
        LambdaService lambdaService = new LambdaService();
        lambdaService.testLambda();
        lambdaService.testDefault();
        lambdaService.testFunctional();
    }
}
