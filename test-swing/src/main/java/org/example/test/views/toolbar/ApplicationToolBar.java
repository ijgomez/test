package org.example.test.views.toolbar;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.toolbar.AppToolBar;

public class ApplicationToolBar extends AppToolBar implements ApplicationModelListener {

	private static final long serialVersionUID = -95062186306252920L;

	protected void initializateGUI() {
		JButton button1, button2, button3, button4, button5;
		
		button1 = new JButton("Entity 1");
		button2 = new JButton("Entity 2");
		button3 = new JButton("Entity 3");
		button4 = new JButton("Entity 4");
		button5 = new JButton("Entity 5");
		
		super.add(button1);
		super.add(button2);
		super.add(button3);
		super.add(button4);
		super.add(new JPanel());
		super.add(new Separator());
		super.add(button5);
	}
	
	@Override
	protected void registerEvents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(ApplicationModel model) {
		super.setModel(model);
		// TODO Auto-generated method stub
		
	}

}
