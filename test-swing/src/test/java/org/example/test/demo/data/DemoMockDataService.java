package org.example.test.demo.data;

import java.util.ArrayList;
import java.util.List;

public class DemoMockDataService {

	private List<Sportman> data;
	

	public DemoMockDataService() {
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
}
