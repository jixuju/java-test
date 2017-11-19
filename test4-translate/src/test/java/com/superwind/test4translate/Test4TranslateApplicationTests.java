package com.superwind.test4translate;

import com.superwind.test4translate.service.TransApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test4TranslateApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testTransApi() {
		String APP_ID = "111";
		String SECURITY_KEY = "222";
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

		String query = "请求太频繁，请稍后再试";
		System.out.println(api.getTransResult(query, "auto", "en"));
	}

}
