package org.example.test.demo.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SportmanMockService {

	private List<Sportman> data;
	
	public SportmanMockService() {
		data = new ArrayList<>();
//		for (int i = 0; i < 100; i++) {
//			data.add(new Sportman((long)(i + 1), RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(10), RandomUtils.nextInt(1900, 2019), RandomUtils.nextBoolean()));
//		}

		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(0L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(0L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(0L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(0L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(0L, "Joe", "Brown", "Pool", 10, false));
	}
	
	public List<Sportman> findByCriteria(SportmanCriteria criteria) {
		return data;
	}
	
	public Integer countByCriteria(SportmanCriteria criteria) {
		return data.size();
	}

	public void save(Sportman sportman) {
		log.trace("Save: {}", sportman);
		data.add(sportman);
	}
}
