package com.luxoft.springcore.objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service("operatorsBean")
@PropertySource("classpath:operators.properties")
public class Operators {

	@Value("${operators.ageComparisonTest}")
	private boolean ageComparisonTest;
	@Value("${operators.distanceComparisonTest}")
	private boolean distanceComparisonTest;
	
	public boolean isAgeComparisonTest() {
		return ageComparisonTest;
	}

	public void setAgeComparisonTest(boolean ageComparisonTest) {
		this.ageComparisonTest = ageComparisonTest;
	}

	public boolean isDistanceComparisonTest() {
		return distanceComparisonTest;
	}

	public void setDistanceComparisonTest(boolean distanceComparisonTest) {
		this.distanceComparisonTest = distanceComparisonTest;
	}

}
