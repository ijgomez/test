package org.example.test;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.example.test.views.ApplicationViewConfiguration;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.toolbar.AppToolBar;

public class DemoApplicationToolBar extends AppToolBar implements ApplicationModelListener {

	private static final long serialVersionUID = -95062186306252920L;
	
	public DemoApplicationToolBar(ApplicationViewConfiguration viewConfiguration) {
		super(viewConfiguration);
	}
	
	protected void handlerInitializateGUI() {
		JButton button1, button2, button3, button4, button5;

		button1 = new JButton("Entity 1");
		button2 = new JButton("Entity 2");
		button3 = new JButton("Entity 3");
		button4 = new JButton("Entity 4");
		button5 = new JButton("Entity 5");
		super.add(new Separator());
		super.add(button1);
		super.add(button2);
		super.add(button3);
		super.add(button4);
		super.add(new JPanel());
		super.add(new Separator());
		super.add(button5);
	}
	
	@Override
	protected void handlerRegisterEventListeners() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	
	
}
