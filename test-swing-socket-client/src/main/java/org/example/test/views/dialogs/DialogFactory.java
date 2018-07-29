package org.example.test.views.dialogs;

import javax.swing.JDialog;
import javax.swing.JFrame;

public final class DialogFactory {

	public static JDialog createAboutDialog(JFrame owner) {
		JDialog dialog;
		
		dialog = new AboutDialog(owner);
		dialog.setLocationRelativeTo(owner);
		
		return dialog;
	}
	
	public static JDialog createOptionsDialog(JFrame owner) {
		JDialog dialog;
		
		dialog = new OptionsDialog(owner);
		dialog.setLocationRelativeTo(owner);
		
		return dialog;
	}
	
}
