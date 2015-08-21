/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.immibytes.util.log;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.immibytes.util.log.domain.TestDomain;
import com.immibytes.util.log.domain.TestMaskedDomain;

public class MaskLoggerTest {

	@Test
	public void testSimpleInstance() {
		TestDomain test = new TestDomain();
		MaskLogger logger = new MaskLogger();
		String result = logger.toString(test);
		//System.out.println("result:" + result);
		//System.out.println("test:" + test);
		Assert.assertEquals("[name=SPSenthil, instances=[], age=XX]", result);
	}

	@Test
	public void testInstanceArrayValuesWithStringMask() {
		TestDomain test = new TestDomain();
		test.populate();
		MaskLogger logger = new MaskLogger();
		String result = logger.toString(test);
		//System.out.println("result:" + result);
		Assert.assertEquals("[name=SPSenthil, instances=[[name=Arumugam, instances=[], age=XX]], age=XX]", result);
	}

	@Test
	public void testInstanceWithArrayMask() {
		TestMaskedDomain test = new TestMaskedDomain();
		test.populate();
		MaskLogger logger = new MaskLogger();
		String result = logger.toString(test);
		//System.out.println("result:" + result);
		Assert.assertEquals("[name=SPSenthil, instances=XX, age=XX]", result);
	}

	@Test
	public void testList() {
		List<String> test = new ArrayList<>();
		test.add("MSP");
		test.add("Shop");
		MaskLogger logger = new MaskLogger();
		String result = logger.toString(test);
		//System.out.println("result:" + result);

		Assert.assertEquals("[elementData=(MSP, Shop)]", result);
	}

	@Test
	public void test() {
		List<String> test = new ArrayList<>();
		test.add("MSP");
		test.add("Shop");
		//System.out.println(test);
		MaskLogger logger = new MaskLogger();
		String result = logger.toString(test);
		//System.out.println("result:" + result);

		Assert.assertEquals("[elementData=(MSP, Shop)]", result);
	}

}
