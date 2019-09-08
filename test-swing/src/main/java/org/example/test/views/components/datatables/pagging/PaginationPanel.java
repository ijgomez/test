package org.example.test.views.components.datatables.pagging;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import org.example.test.views.components.ApplicationConstants;
import org.example.test.views.components.datatables.AppDataTableListener;
import org.example.test.views.components.datatables.pagging.buttons.FirstPageButton;
import org.example.test.views.components.datatables.pagging.buttons.LastPageButton;
import org.example.test.views.components.datatables.pagging.buttons.NextPageButton;
import org.example.test.views.components.datatables.pagging.buttons.PreviousPageButton;
import org.example.test.views.components.panels.AppPanel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaginationPanel extends AppPanel implements AppDataTableListener, ApplicationConstants {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -913388843573832438L;
	
	/** Number of the record shown at the top of the page. */
	private int actualRegistry = 0;
	
	/** Total number of records contained in the data table. */ 
	private int totalRegistry = 0;
	
	private JLabel pagingCounterPanel;

	@Override
	protected void initializateGUI() {
		FirstPageButton firstPagingButton;
		PreviousPageButton prevPagingButton;
		NextPageButton nextPagingButton;
		LastPageButton lastPagingButton;
		
		firstPagingButton = new FirstPageButton();
        prevPagingButton = new PreviousPageButton();
        nextPagingButton = new NextPageButton();
        lastPagingButton = new LastPageButton();
        
        this.pagingCounterPanel = new JLabel("%s - %s, de %s registries");
        

		super.add(firstPagingButton);
		super.add(prevPagingButton);
		super.add(pagingCounterPanel);
		super.add(nextPagingButton);
		super.add(lastPagingButton);
	}

	@Override
	protected void registerEventListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void firstPageAction(ActionEvent e) {
		log.trace("Fist page: {}", e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void previousPageAction(ActionEvent e) {
		log.trace("Previous page: {}", e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextPageAction(ActionEvent e) {
		log.trace("Next page: {}", e);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lastPageAction(ActionEvent e) {
		log.trace("Last page: {}", e);
		// TODO Auto-generated method stub
		
	}
	
	

}
