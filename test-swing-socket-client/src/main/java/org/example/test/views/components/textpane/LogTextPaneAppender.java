package org.example.test.views.components.textpane;

import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleConstants.CharacterConstants;
import javax.swing.text.StyledDocument;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

public class LogTextPaneAppender extends WriterAppender {
	
	private static final Logger LOGGER = Logger.getLogger(LogTextPaneAppender.class);

	private StyledDocument document;

	public LogTextPaneAppender(StyledDocument document) {
		this.document = document;
		//setLayout(new PatternLayout("%-d{HH:mm:ss,SSS} %-15c{1} - %m%n"));
		setLayout(new PatternLayout("%-d{HH:mm:ss,SSS} %t %-15c{1} - %m%n"));
	}

	@Override
	public void append(final LoggingEvent event) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final String message;
				SimpleAttributeSet attributeSet;
				try {
					message = layout.format(event);
					attributeSet = new SimpleAttributeSet();
					if (event.getLevel().equals(Level.ERROR)) {
						attributeSet.addAttribute(CharacterConstants.Foreground, Color.RED);
					} else if (event.getLevel().equals(Level.WARN)) {
						attributeSet.addAttribute(CharacterConstants.Foreground, new Color(255, 140, 0));
					} else if (event.getLevel().equals(Level.INFO)) {
						attributeSet.addAttribute(CharacterConstants.Foreground, Color.BLACK);
						StyleConstants.setBold(attributeSet, true);
					} else if (event.getLevel().equals(Level.DEBUG)) {
						attributeSet.addAttribute(CharacterConstants.Foreground, Color.DARK_GRAY);
					} else if (event.getLevel().equals(Level.TRACE)) {
						attributeSet.addAttribute(CharacterConstants.Foreground, Color.DARK_GRAY);
					}
					document.insertString(document.getLength(), message, attributeSet);
					
					if (layout.ignoresThrowable()) {
						String[] s = event.getThrowableStrRep();
						if (s != null) {
							for (String m : s) {
								document.insertString(document.getLength(), m, attributeSet);
								document.insertString(document.getLength(), Layout.LINE_SEP, attributeSet);
							}
						}
					}
				} catch (BadLocationException e) {
					LOGGER.error("Fallo al escribir el log en la consola:", e);
				}
    
			}
		});
	}
	
	
}
