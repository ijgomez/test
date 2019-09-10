package org.example.test.demo;

import java.util.List;

import org.example.test.demo.data.Sportman;
import org.example.test.demo.data.SportmanCriteria;
import org.example.test.demo.data.SportmanMockService;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.datatables.AppDataTable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SportmanDataTable extends AppDataTable<Sportman, SportmanCriteria> implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 5190038862045411081L;

	private SportmanMockService service;
	
	@Override
	protected void handlerInitializateGUI() {
		// TODO Auto-generated method stub
		this.service = new SportmanMockService();
	}
	
	@Override
	protected String[] createColumnNames() {
		return new String[]{
				"First Name",
				"Last Name",
				"Sport",
				"# of Years",
            	"Vegetarian"
			};
	}
	
	@Override
	protected SportmanCriteria buildCriteria() {
		SportmanCriteria criteria;
		
		criteria = new SportmanCriteria();
		// TODO Auto-generated method stub
		return criteria;
	}

	public void addRegister(Sportman sportman) {
		log.trace("New Registry: {}", sportman);
		service.save(sportman);
		super.updateView();
	}
	
	@Override
	protected Object[] toMapper(Sportman sportman) {
		return new Object[] {sportman.getName(), sportman.getSurname(), sportman.getSport(), sportman.getYear(), sportman.getVegetarian()};
	}

	@Override
	protected Integer countByCriteria(SportmanCriteria criteria) {
		return service.countByCriteria(criteria);
	}
	
	@Override
	protected List<Sportman> findByCriteria(SportmanCriteria criteria) {
		return service.findByCriteria(criteria);
	}
}