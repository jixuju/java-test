package com.superwind.service;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SnowflakeSequenceTest {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/**
	 * 重复性测试
	 */
	@Test
	public void testRepeated() {
		Set<Long> set = new HashSet<Long>();
		int maxTimes = 10000 * 10;
		SnowflakeSequence snowflakeSequence = new SnowflakeSequence(0, 0);
		for (int i = 0; i < maxTimes; i++) {
			set.add(snowflakeSequence.nextId());
		}
		Assert.assertEquals(maxTimes, set.size());
	}

	/**
	 * 数据测试
	 */
	@Test
	public void name() {
		try {
			int times = 0, maxTimes = 1000;
			SnowflakeSequence snowflakeSequence = new SnowflakeSequence(0, 0);
			for (int i = 0; i < maxTimes; i++) {
				long id = snowflakeSequence.nextId();
				if(id%2==0){
					times++;
				}
				Thread.sleep(10);
			}
			System.out.println("偶数:" + times + ",奇数:" + (maxTimes - times) + "!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 性能测试
	 */
	@Test
	@PerfTest(invocations = 30000, threads = 20)
	@Required(max = 50, average = 10, totalTime = 60000)
	public void test1() throws Exception {
		SnowflakeSequence snowflakeSequence = new SnowflakeSequence(0, 0);
		snowflakeSequence.nextId();
	}
}
