package org.example.test.views.components.panels;

import org.example.test.views.components.ApplicationModelListener;

public abstract class AppContainerView extends AppPanel implements ApplicationModelListener {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 1169424945255632992L;

}
