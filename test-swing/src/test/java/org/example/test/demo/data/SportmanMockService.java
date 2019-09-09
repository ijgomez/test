package org.example.test.demo.data;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SportmanMockService {

	private List<Sportman> data;
	
	public SportmanMockService() {
		data = new ArrayList<>();
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman("Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman("John", "Doe", "Rowing", 3, true));
		data.add(new Sportman("Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman("Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman("Joe", "Brown", "Pool", 10, false));
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
