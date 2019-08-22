package org.example.test.views;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class ModalDialogFactory {
	
	public static boolean showConfirmExitDialog(Component parentComponent) {
		ResourceBundle textResources;
		
		textResources = LocaleHelper.getTextResources();
		
		int showConfirmDialog = JOptionPane.showConfirmDialog(parentComponent, textResources.getString("mensaje.confirmacion.exit.text"), textResources.getString("mensaje.confirmacion.exit.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		return (showConfirmDialog == JOptionPane.YES_OPTION);
	}
}
