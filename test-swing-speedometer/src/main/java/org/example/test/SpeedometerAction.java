package org.example.test;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpeedometerAction {

	SpeedometerPanel speedometer;
	 
    public SpeedometerAction(SpeedometerPanel sp)
    {
        speedometer = sp;
    }
 
    public JPanel getUIpanel()
    {
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setBackground(Color.pink);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                speedometer.setSpeed(slider.getValue());
            }
        });
        JPanel panel = new JPanel();
        panel.setBackground(Color.pink);
        panel.add(slider);
        return panel;
    }

}
