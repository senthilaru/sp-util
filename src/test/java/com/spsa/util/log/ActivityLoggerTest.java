package com.spsa.util.log;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.spsa.util.log.domain.TestDomain;
import com.spsa.util.log.domain.TestMaskedDomain;

public class ActivityLoggerTest {

	@Test
	public void testSimpleInstance() {
		TestDomain test = new TestDomain();
		ActivityLogger logger = new ActivityLogger();
		String result = logger.toString(test);
		System.out.println("result:" + result);
		System.out.println("test:" + test);
		Assert.assertEquals("[name=SPSenthil, instances=[], age=XX]", result);
	}

	@Test
	public void testInstanceArrayValuesWithStringMask() {
		TestDomain test = new TestDomain();
		test.populate();
		ActivityLogger logger = new ActivityLogger();
		String result = logger.toString(test);
		System.out.println("result:" + result);
		Assert.assertEquals("[name=SPSenthil, instances=[[name=Arumugam, instances=[], age=XX]], age=XX]", result);
	}

	@Test
	public void testInstanceWithArrayMask() {
		TestMaskedDomain test = new TestMaskedDomain();
		test.populate();
		ActivityLogger logger = new ActivityLogger();
		String result = logger.toString(test);
		System.out.println("result:" + result);
		Assert.assertEquals("[name=SPSenthil, instances=XX, age=XX]", result);
	}

	@Test
	public void testList() {
		List<String> test = new ArrayList<>();
		test.add("MSP");
		test.add("Shop");
		ActivityLogger logger = new ActivityLogger();
		String result = logger.toString(test);
		System.out.println("result:" + result);

		Assert.assertEquals("[elementData=(MSP, Shop)]", result);
	}

	@Test
	public void test() {
		List<String> test = new ArrayList<>();
		test.add("MSP");
		test.add("Shop");
		System.out.println(test);
		ActivityLogger logger = new ActivityLogger();
		String result = logger.toString(test);
		System.out.println("result:" + result);

		Assert.assertEquals("[elementData=(MSP, Shop)]", result);
	}

}
