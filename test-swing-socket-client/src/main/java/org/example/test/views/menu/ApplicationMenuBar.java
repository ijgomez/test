package org.example.test.views.menu;

import java.awt.Cursor;
import java.util.ResourceBundle;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.menu.buttons.AboutMenuItem;
import org.example.test.views.components.menu.buttons.CleanMenuItem;
import org.example.test.views.components.menu.buttons.CloseMenuItem;
import org.example.test.views.components.menu.buttons.OpenMenuItem;
import org.example.test.views.components.menu.buttons.OptionsMenuItem;
import org.example.test.views.components.menu.buttons.SaveAsMenuItem;
import org.example.test.views.components.menu.buttons.SaveMenuItem;

public class ApplicationMenuBar extends JMenuBar implements ApplicationObserver {

	private static final long serialVersionUID = 7189582102634292466L;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
	private OpenMenuItem openFileMenuItem;
	
	private SaveMenuItem saveMenuItem;
	
	private SaveAsMenuItem saveAsMenuItem;
	
	private CloseMenuItem closeMenuItem;
	
	private CleanMenuItem cleanMenuItem;
	
	private OptionsMenuItem optionsMenuItem;
	
	private AboutMenuItem aboutMenuItem;
	
	public ApplicationMenuBar() {
		this.initComponents();
	}

	private void initComponents() {
		JMenu fileMenu, editMenu, viewMenu, configurationMenu, helpMenu;
		JCheckBoxMenuItem checkBoxMenuItem;

        openFileMenuItem = new OpenMenuItem();

        saveMenuItem = new SaveMenuItem();
        
        saveAsMenuItem = new SaveAsMenuItem();
        
        closeMenuItem = new CloseMenuItem();
        
        cleanMenuItem = new CleanMenuItem();
        
        checkBoxMenuItem = new JCheckBoxMenuItem();
        checkBoxMenuItem.setText("Tramas de Datos");
        checkBoxMenuItem.setSelected(true);
        
        optionsMenuItem = new OptionsMenuItem();
        
        aboutMenuItem = new AboutMenuItem();
        
        fileMenu = new JMenu();
        fileMenu.setText(resourceBundle.getString("menu.file.text"));
        fileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileMenu.add(openFileMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(new JPopupMenu.Separator());
        fileMenu.add(closeMenuItem);
        
        editMenu = new JMenu();
        editMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editMenu.setText(resourceBundle.getString("menu.edit.text"));
        
        
        viewMenu = new JMenu();
        viewMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewMenu.setText(resourceBundle.getString("menu.view.text"));
        viewMenu.add(cleanMenuItem);
        viewMenu.add(new JPopupMenu.Separator());
        viewMenu.add(checkBoxMenuItem);
        
        configurationMenu = new JMenu();
        configurationMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        configurationMenu.setText(resourceBundle.getString("menu.configuration.text"));
        configurationMenu.add(optionsMenuItem);
        
        helpMenu = new JMenu();
        helpMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        helpMenu.setText(resourceBundle.getString("menu.help.text"));
        helpMenu.add(aboutMenuItem);

        super.add(fileMenu);
        super.add(editMenu);
        super.add(viewMenu);
        super.add(configurationMenu);
        super.add(helpMenu);
		
	}
	
	private ApplicationObservable observable;
	
	@Override
	public void registerIn(ApplicationObservable observable) {
		if (observable != null) {
			this.observable = observable;
			this.observable.add(this);
		} else {
			this.observable.remove(this);
			this.observable = observable;
		}
		this.openFileMenuItem.registerIn(observable);
		this.saveMenuItem.registerIn(observable);
		this.saveAsMenuItem.registerIn(observable);
		this.closeMenuItem.registerIn(observable);
		this.cleanMenuItem.registerIn(observable);
		this.optionsMenuItem.registerIn(observable);
		this.aboutMenuItem.registerIn(observable);
	}
	
	@Override
	public void listener(ApplicationEvent evento) {
		// TODO Auto-generated method stub
		
	}
}
