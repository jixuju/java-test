package com.superwind.test2ratelimit;

import com.superwind.test2ratelimit.service.RateLimitPolicy;
import com.superwind.test2ratelimit.service.TokenBucketLuaJedis;
import com.superwind.test2ratelimit.service.TokenBucketLuaRedisTemplate;
import com.superwind.test2ratelimit.service.UnitTimesIncr;
import com.superwind.test2ratelimit.service.UserRateLimitPolicy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2RatelimitApplicationTests {

	@Autowired
	TokenBucketLuaRedisTemplate tokenBucketLuaRedisTemplate;
	@Autowired
	UnitTimesIncr unitTimesIncr;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testTokenBucketByRedisTemplete(){
		RateLimitPolicy policy;
		policy = new UserRateLimitPolicy(100, 1000, 10000);
		String path = "classpath:token-bucket.lua";

		for (int i = 0; i < 20; i++) {
			boolean result = tokenBucketLuaRedisTemplate.access(path,"USER_TEMPLATE_10001",policy);
			System.out.println(result);
		}
	}

	@Test
	public void testTokenBucketLuaJedis(){
		RateLimitPolicy policy;
		policy = new UserRateLimitPolicy(22, 1000, 10000);
		String path = "classpath:token-bucket.lua";
		TokenBucketLuaJedis tokenBucketLuaJedis = new TokenBucketLuaJedis();
		tokenBucketLuaJedis.init(path,policy);

		for (int i = 0; i < 20; i++) {
			boolean result = tokenBucketLuaJedis.access("USER_JEDIS_10001");
			System.out.println(result);
		}
	}

	@Test
	public void testUnitTimesIncr(){
		String path = "classpath:unit-times.lua";

		for (int i = 0; i < 20; i++) {
			boolean result = unitTimesIncr.access(path,"USER_TIMES_10001");
			System.out.println(result);
		}
	}
}
