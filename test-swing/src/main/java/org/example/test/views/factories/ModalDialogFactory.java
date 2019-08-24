package org.example.test.views.factories;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.example.test.views.components.helpers.LocaleHelper;

public class ModalDialogFactory {
	
	public static boolean showConfirmExitDialog(Component parentComponent) {
		ResourceBundle textResources;
		
		textResources = LocaleHelper.getMessagesResources();
		
		int showConfirmDialog = JOptionPane.showConfirmDialog(parentComponent, textResources.getString("dialog.confirm.exit.text"), textResources.getString("dialog.confirm.exit.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		return (showConfirmDialog == JOptionPane.YES_OPTION);
	}
}
