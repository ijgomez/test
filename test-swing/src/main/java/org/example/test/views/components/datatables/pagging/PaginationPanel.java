package org.example.test.views.components.datatables.pagging;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import org.example.test.views.components.ApplicationConstants;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.datatables.AppDataTableListener;
import org.example.test.views.components.datatables.pagging.buttons.FirstPageButton;
import org.example.test.views.components.datatables.pagging.buttons.LastPageButton;
import org.example.test.views.components.datatables.pagging.buttons.NextPageButton;
import org.example.test.views.components.datatables.pagging.buttons.PreviousPageButton;
import org.example.test.views.components.panels.AppPanel;
import org.example.test.views.factories.ResourcesFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class PaginationPanel extends AppPanel implements AppDataTableListener, ApplicationConstants {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = -913388843573832438L;
	
	/** Number of the record shown at the top of the page. */
	private int actualRegistry = 0;
	
	/** Total number of records contained in the data table. */ 
	private int totalRegistry = 0;
	
	/** Button to go to the first page. */
	private FirstPageButton firstPagingButton;
	
	/** Button to go to the previous page. */
	private PreviousPageButton prevPagingButton;
		
	private JLabel pagingCounterPanel;
	
	/** Button to go to the next page. */
	private NextPageButton nextPagingButton;
	
	/** Button to go to the last page. */
	private LastPageButton lastPagingButton;

	@Override
	protected void initializateGUI() {
		
		this.firstPagingButton = new FirstPageButton();
		this.prevPagingButton = new PreviousPageButton();
        
        this.pagingCounterPanel = new JLabel(ResourcesFactory.getString("datatable.pagging.text", 0, 0, 0));
        
        this.nextPagingButton = new NextPageButton();
        this.lastPagingButton = new LastPageButton();
        
		super.add(firstPagingButton);
		super.add(prevPagingButton);
		super.add(pagingCounterPanel);
		super.add(nextPagingButton);
		super.add(lastPagingButton);
	}

	@Override
	protected void registerEventListeners() { }

	@Override
	public void updateView() {
		int fistRegistry = 0, lastRegistry = 0;
		
		fistRegistry = (actualRegistry * MAX_REGISTRY_BY_PAGE + 1);
		lastRegistry = (actualRegistry * MAX_REGISTRY_BY_PAGE + MAX_REGISTRY_BY_PAGE);
		if (lastRegistry > totalRegistry) {
			lastRegistry = totalRegistry;
		}
		
		log.trace("actualRegistry={}, fistRegistry={}, lastRegistry={}", actualRegistry, fistRegistry, lastRegistry);
		
		this.firstPagingButton.setEnabled(this.actualRegistry != 0);
		this.prevPagingButton.setEnabled(this.actualRegistry != 0);
		this.pagingCounterPanel.setText(ResourcesFactory.getString("datatable.pagging.text", fistRegistry, lastRegistry, totalRegistry));
		this.nextPagingButton.setEnabled(this.actualRegistry != this.totalRegistry / MAX_REGISTRY_BY_PAGE);
		this.lastPagingButton.setEnabled(this.actualRegistry != this.totalRegistry / MAX_REGISTRY_BY_PAGE);
	}
	

	@Override
	public void firstPageAction(ActionEvent e) {
		log.trace("Fist page: {}", e);
		this.actualRegistry = 0;
		((ApplicationModelListener) getParent()).updateView();
	}

	@Override
	public void previousPageAction(ActionEvent e) {
		log.trace("Previous page: {}", e);
		this.actualRegistry--;
		((ApplicationModelListener) getParent()).updateView();
	}

	@Override
	public void nextPageAction(ActionEvent e) {
		log.trace("Next page: {}", e);
		this.actualRegistry++;
		((ApplicationModelListener) getParent()).updateView();
	}

	@Override
	public void lastPageAction(ActionEvent e) {
		log.trace("Last page: {}", e);
		this.actualRegistry = this.totalRegistry / MAX_REGISTRY_BY_PAGE;
		((ApplicationModelListener) getParent()).updateView();
	}

}
