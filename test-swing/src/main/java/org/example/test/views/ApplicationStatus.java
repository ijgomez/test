package org.example.test.views;

import java.util.Calendar;

import javax.swing.JLabel;

import org.example.test.views.components.ApplicationModelListener;
import org.example.test.views.components.panels.AppPanel;

public class ApplicationStatus extends AppPanel implements ApplicationModelListener {

	private static final long serialVersionUID = -1661205291887146815L;

	private JLabel timeLabel;
	
	protected void initializateGUI() {
		
		this.timeLabel = new JLabel();
		this.timeLabel.setText(String.format("%1$tH:%1$tM:%1$tS del %1$te de %1$tB de %1$tY.", Calendar.getInstance()));

		super.add(this.timeLabel);
	}
	
	@Override
	protected void registerEventListeners() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
	
}
