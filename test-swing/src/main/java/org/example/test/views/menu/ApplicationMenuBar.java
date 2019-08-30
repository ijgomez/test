package org.example.test.views.menu;

import java.awt.Cursor;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import org.example.test.views.components.ApplicationModel;
import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.menubar.AppMenuBar;
import org.example.test.views.menu.buttons.CloseMenuItem;
import org.example.test.views.menu.buttons.OpenMenuItem;
import org.example.test.views.menu.buttons.SaveMenuItem;
import org.example.test.views.menu.buttons.SettingsMenuItem;

public class ApplicationMenuBar extends AppMenuBar implements ApplicationModelListener {

	private static final long serialVersionUID = 7189582102634292466L;

	private OpenMenuItem openFileMenuItem;
	
	private SaveMenuItem saveMenuItem;
	
	private CloseMenuItem closeMenuItem;
	
	private SettingsMenuItem settingsMenuItem;
	
	@Override
	protected void initializateGUI() {
		JMenu fileMenu, editMenu, settingsMenu;

        openFileMenuItem = new OpenMenuItem();
        
        saveMenuItem = new SaveMenuItem();
        
        closeMenuItem = new CloseMenuItem();
        
        settingsMenuItem = new SettingsMenuItem();
        
        fileMenu = new JMenu();
        fileMenu.setText(textResources.getString("menu.file.text"));
        fileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileMenu.add(openFileMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(new JPopupMenu.Separator());
        fileMenu.add(closeMenuItem);
        
        editMenu = new JMenu();
        editMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editMenu.setText(textResources.getString("menu.edit.text"));
        
        settingsMenu = new JMenu();
        settingsMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingsMenu.setText(textResources.getString("menu.settings.text"));
        settingsMenu.add(settingsMenuItem);

        super.add(fileMenu);
        super.add(editMenu);
        super.add(settingsMenu);
		
	}
	
	@Override
	protected void registerEventListeners() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setModel(ApplicationModel model) {
		super.setModel(model);
		this.openFileMenuItem.setModel(model);
		this.saveMenuItem.setModel(model);
		this.closeMenuItem.setModel(model);
		this.settingsMenuItem.setModel(model);
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
	
}
