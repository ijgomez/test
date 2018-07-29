package org.example.test.views.menu;

import java.awt.Cursor;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.menu.buttons.CleanMenuItem;
import org.example.test.views.components.menu.buttons.CloseMenuItem;
import org.example.test.views.components.menu.buttons.OpenMenuItem;
import org.example.test.views.components.menu.buttons.SaveMenuItem;

public class ApplicationMenuBar extends JMenuBar implements ApplicationObserver {

	private static final long serialVersionUID = 7189582102634292466L;
	
	private OpenMenuItem openFileMenuItem;
	
	private SaveMenuItem saveMenuItem;
	
	private CloseMenuItem closeMenuItem;
	
	private CleanMenuItem cleanMenuItem;
	
	public ApplicationMenuBar() {
		this.initComponents();
	}

	private void initComponents() {
		JMenu fileMenu, editMenu;

        openFileMenuItem = new OpenMenuItem();
        

        saveMenuItem = new SaveMenuItem();
        
        closeMenuItem = new CloseMenuItem();
        
        cleanMenuItem = new CleanMenuItem();
        
        fileMenu = new JMenu();
        fileMenu.setText("File");
        fileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileMenu.add(openFileMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(new JPopupMenu.Separator());
        fileMenu.add(closeMenuItem);
        
        editMenu = new JMenu();
        editMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editMenu.setText("Edit");
        editMenu.add(cleanMenuItem);

        super.add(fileMenu);
        super.add(editMenu);
		
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
		this.closeMenuItem.registerIn(observable);
		this.cleanMenuItem.registerIn(observable);
	}
	
	@Override
	public void listener(ApplicationEvent evento) {
		// TODO Auto-generated method stub
		
	}
}
