package org.example.test.views.components.buttons;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;

public abstract class AppButton extends BasicButton implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 4106812624638240974L;
	
	protected ApplicationModel model;

	public AppButton(String titleTextKey, String toolTipTextKey) {
		super(titleTextKey, toolTipTextKey);
	}
	
	@Override
	public void setModel(ApplicationModel model) {
		if (model != null) {
			this.model = model;
			this.model.register(this);
			this.updateView();
		} else {
			this.model.unregister(this);
			this.model = model;
		}
	}
	
	@Override
	public abstract void updateView();

}
