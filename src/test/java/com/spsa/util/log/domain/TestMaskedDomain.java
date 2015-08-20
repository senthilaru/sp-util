package com.spsa.util.log.domain;

import java.util.ArrayList;
import java.util.List;

import com.spsa.util.log.Mask;

public class TestMaskedDomain {

	private String name = "SPSenthil";
	private @Mask List<TestMaskedDomain> instances = new ArrayList<>();
	private @Mask String age = "34";

	public TestMaskedDomain() {
	}

	public TestMaskedDomain(String name) {
		this.name = name;
	}

	public void populate() {
		instances.add(new TestMaskedDomain("Arumugam"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestMaskedDomain> getInstances() {
		return instances;
	}

	public void setInstances(List<TestMaskedDomain> instances) {
		this.instances = instances;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "TestMe [name=" + name + ", instances=" + instances + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((instances == null) ? 0 : instances.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TestMaskedDomain other = (TestMaskedDomain) obj;
		if (age == null) {
			if (other.age != null) {
				return false;
			}
		} else if (!age.equals(other.age)) {
			return false;
		}
		if (instances == null) {
			if (other.instances != null) {
				return false;
			}
		} else if (!instances.equals(other.instances)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
