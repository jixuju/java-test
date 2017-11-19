package com.superwind.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class UuidSequenceTest {
	@Test
	public void testUUID() {
		UuidSequence uuidSequence = new UuidSequence();
		Long seqId = uuidSequence.nextId();
		Assertions.assertThat(seqId).isNotZero();
	}

}
