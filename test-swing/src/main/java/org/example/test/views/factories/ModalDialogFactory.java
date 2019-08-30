package org.example.test.views.factories;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.example.test.views.resources.TextResources;

public class ModalDialogFactory {
	
	public static boolean showConfirmExitDialog(Component parentComponent) {
		TextResources textResources = ResourcesFactory.getFactory().text();
		
		int showConfirmDialog = JOptionPane.showConfirmDialog(parentComponent, textResources.getString("dialog.confirm.exit.text"), textResources.getString("dialog.confirm.exit.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		return (showConfirmDialog == JOptionPane.YES_OPTION);
	}
}
