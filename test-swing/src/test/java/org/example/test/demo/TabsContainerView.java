package org.example.test.demo;

import javax.swing.JLabel;

import org.example.test.views.annotations.ApplicationContainerViewConfig;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

@ApplicationContainerViewConfig(titleTextKey = "toolbar.button.container.tree.text", toolTipTextKey = "toolbar.button.container.tree.tool.tip", order = 3, selected = false)
public class TabsContainerView extends AppPanel implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 8543052142754893011L;

	@Override
	protected void initializateGUI() {
		// TODO Auto-generated method stub
		super.add(new JLabel("Tree container"));
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
