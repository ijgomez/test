package org.example.test.views.defaults;

import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.toolbar.AppToolBar;

public class DefaultApplicationToolBar extends AppToolBar implements ApplicationModelListener {

	private static final long serialVersionUID = -95062186306252920L;
	
	public DefaultApplicationToolBar(ApplicationViewConfiguration viewConfiguration) {
		super(viewConfiguration);
	}
	
	protected void handlerInitializateGUI() { }
	
	@Override
	protected void handlerRegisterEventListeners() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	
	
}
