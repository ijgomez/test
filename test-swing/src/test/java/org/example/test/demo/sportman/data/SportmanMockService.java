package org.example.test.demo.sportman.data;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SportmanMockService {

	private List<Sportman> data;
	
	public SportmanMockService() {
		data = new ArrayList<>();
//		for (int i = 0; i < 100; i++) {
//			data.add(new Sportman((long)(i + 1), RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(10), RandomUtils.nextInt(1900, 2019), RandomUtils.nextBoolean()));
//		}

		data.add(new Sportman(1L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(2L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(3L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(4L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(5L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(6L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(7L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(8L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(9L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(10L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(11L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(12L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(13L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(14L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(15L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(16L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(17L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(18L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(19L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(20L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(21L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(22L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(23L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(24L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(25L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(26L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(27L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(28L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(29L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(30L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(31L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(32L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(33L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(34L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(35L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(36L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(37L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(38L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(39L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(40L, "Joe", "Brown", "Pool", 10, false));
		data.add(new Sportman(41L, "Kathy", "Smith", "Snowboarding", 5, false));
		data.add(new Sportman(42L, "John", "Doe", "Rowing", 3, true));
		data.add(new Sportman(43L, "Sue", "Black", "Knitting", 2, false));
		data.add(new Sportman(44L, "Jane", "White", "Speed reading", 20, true));
		data.add(new Sportman(45L, "Joe", "Brown", "Pool", 10, false));
	}
	
	public List<Sportman> findByCriteria(SportmanCriteria criteria) {
		log.trace("Criteria: pageNumber={}, pageSize={}, sortField={}, sortOrder={}", criteria.getPageNumber(), criteria.getPageSize(), criteria.getSortField(), criteria.getSortOrder());
		
		// Filter by criteria
		
		int fistRegistry, lastRegistry;
		fistRegistry = (criteria.getPageNumber() * criteria.getPageSize() + 1);                       
		lastRegistry = (criteria.getPageNumber() * criteria.getPageSize() + criteria.getPageSize());    
		if (lastRegistry > data.size()) {
			lastRegistry = data.size();
		}
		
		return data.subList(fistRegistry -1, lastRegistry);
		
//		return data;
	}
	
	public Integer countByCriteria(SportmanCriteria criteria) {
		// Filter by criteria
		return data.size();
	}

	public void save(Sportman sportman) {
		log.trace("Save: {}", sportman);
		data.add(sportman);
	}
}
