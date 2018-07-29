package org.example.test.views.browser.details;

import java.awt.Color;
import java.awt.Font;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants.CharacterConstants;
import javax.swing.text.StyledDocument;

import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationLogComponent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class ResultDetailsView extends JScrollPane implements ApplicationObserver, ApplicationLogComponent {

	private static final long serialVersionUID = 1139088501677047263L;
	
	private JTextPane textPane;

	public ResultDetailsView() {
		this.initComponents();
	}

	private void initComponents() {

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setAutoscrolls(true);
		textPane.setFont((new Font("Courier New", 0, 12)));
		
		super.setViewportView(textPane);
	}
	
	private void text(String nivel, String text)  {
		StyledDocument document;
		SimpleAttributeSet attributeSet;
		
		document = textPane.getStyledDocument();
		attributeSet = new SimpleAttributeSet();
		
		if (nivel.equals("ERROR")) {
			attributeSet.addAttribute(CharacterConstants.Foreground, Color.RED);	
		} else if (nivel.equals("OK")) {
			attributeSet.addAttribute(CharacterConstants.Foreground, Color.GREEN);
		}
		
		try {
		
			document.insertString(document.getLength(), text +"\n", attributeSet);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textPane.validate();
	}
	
	public void text(Throwable th) {
		StyledDocument document;
		SimpleAttributeSet attributeSet;
		
		document = textPane.getStyledDocument();
		attributeSet = new SimpleAttributeSet();
		
			attributeSet.addAttribute(CharacterConstants.Foreground, Color.RED);	
		
		try {
			StringWriter errors = new StringWriter();
			th.printStackTrace(new PrintWriter(errors));
			document.insertString(document.getLength(), errors.toString() +"\n", attributeSet);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textPane.validate();
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
	}
	
	@Override
	public void listener(ApplicationEvent evento) {
		//Logger.getLogger(ResultDetailsView.class.getName()).log(Level.INFO, evento + "");
		// TODO Auto-generated method stub
		//this.text("INFO", evento.toString());
	}

	@Override
	public void info(String message) {
		this.text("INFO", message);
		
	}

	@Override
	public void warn(String message) {
		this.text("WARN", message);
	}

	@Override
	public void error(String message) {
		this.text("ERROR", message);
	}
	
	@Override
	public void error(Throwable th) {
		this.text(th);
		
	}

	public void clean() {
		textPane.setText("");
		textPane.validate();
	}
}
