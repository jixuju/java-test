package com.superwind.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by jiangxj on 2017/6/9.
 */
public class AESUtilTest {
    @Test
    public void test() {
        String content = "qieuradjfk?id=100096&token=213817238sadkfj&time=1238712839";
        String password = "12345678";
        // 加密
        System.out.println("加密前：" + content);
        String s = AESUtil.encrypt(content, password);
        System.out.println("加密后："+s);
        // 解密

        String s1 = AESUtil.decrypt(s, password);
        System.out.println("解密后：" +s1);
        Assertions.assertThat(s1).isEqualTo(content);
    }
}
