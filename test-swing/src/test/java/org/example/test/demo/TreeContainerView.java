package org.example.test.demo;

import javax.swing.JLabel;

import org.example.test.views.annotations.ApplicationContainerViewConfig;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

@ApplicationContainerViewConfig(titleTextKey = "toolbar.button.container.tabs.text", toolTipTextKey = "toolbar.button.container.tabs.tool.tip", order = 4, selected = false)
public class TreeContainerView extends AppPanel implements ApplicationModelListener {

	private static final long serialVersionUID = 8543052142754893011L;

	@Override
	protected void initializateGUI() {
		// TODO Auto-generated method stub
		super.add(new JLabel("Tabs container"));
	}

	@Override
	protected void registerEventListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
}
