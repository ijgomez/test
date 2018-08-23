/**
 * Copyright (c) 2008 Mark S. Kolich
 * http://mark.kolich.com
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package org.example.test.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.example.test.camera.AxisCamera;

public class AxisViewerFrame extends JFrame implements Runnable, ChangeListener {

	private static final String URL_AXIS_CAMERA = "http://%s/axis-cgi/jpg/image.cgi";

	private static final long serialVersionUID = 3325123392308489256L;

	private AxisCamera camera;
	
	private boolean refreshThreadState = true;
	
	private boolean pausedThreadState = false;

	// The following components are GUI-level objects used in the
	// actual application GUI.
	private JSlider refreshSlider = null;
	private JButton pauseButton = null;
	private JTextField mouseCoordinates = null;
	
	private String user;
	
	private String password;
	
	private String url;

	public AxisViewerFrame(String ip, String user, String password) {
		super("Axis Camera Viewer");
		this.url = String.format(URL_AXIS_CAMERA, ip);
		this.user = user;
		this.password = password;
		this.initializateGUI();

	}

	private void initializateGUI() {
		JPanel controlPanel, cameraPanel;
		
		this.mouseCoordinates = new JTextField("Mouse Coordinates:  (0,0)");
		this.mouseCoordinates.setPreferredSize(new Dimension(20, 20));
		this.mouseCoordinates.setEditable(false);
		this.mouseCoordinates.setHorizontalAlignment(JTextField.RIGHT);
		this.mouseCoordinates.setFont(new Font("Arial", Font.BOLD, 12));
		this.mouseCoordinates.setBackground(new Color(200, 200, 200));

		this.camera = new AxisCamera(String.format("Axis Network Camera (%s)",this.url), this.url, this.user, this.password, AxisViewerConstants.REFRESH_RATE );
		
		cameraPanel = new JPanel();
		cameraPanel.setLayout(new BoxLayout(cameraPanel, BoxLayout.PAGE_AXIS));
		cameraPanel.add(this.camera);
		cameraPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.refreshSlider = new JSlider(JSlider.HORIZONTAL, (int) AxisViewerConstants.MIN_REFRESH_RATE, (int) AxisViewerConstants.MAX_REFRESH_RATE, (int) this.camera.getRefreshRate());
		this.refreshSlider.addChangeListener(this);
		this.refreshSlider.setMajorTickSpacing(1000);
		this.refreshSlider.setMinorTickSpacing(500);
		this.refreshSlider.setPaintTicks(true);
		this.refreshSlider.setPaintLabels(true);
		
		this.pauseButton = new JButton("Pause");
		this.pauseButton.setActionCommand("pause");
		this.pauseButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if ("pause".equals(e.getActionCommand())) {
					if (!pausedThreadState) {
						pause();
						refreshSlider.setEnabled(false);
						pauseButton.setText("  Play  ");
					} else {
						resume();
						refreshSlider.setEnabled(true);
						pauseButton.setText("Pause");
					} 

				}
				
			}
		});
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		controlPanel.add(this.refreshSlider);
		controlPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		controlPanel.add(this.pauseButton);
		controlPanel.add(Box.createRigidArea(new Dimension(100, 0)));
		controlPanel.add(Box.createHorizontalGlue());
		
		// Set the size of the window.
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(650, 590);
		super.setLocation(100, 100);
		super.setResizable(false);

		super.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				// Get the x and y coords of the mouse click.
				int x = e.getX() - 5;
				int y = e.getY() - 50;

				if (x >= 0 && y >= 0) {
					// Set the mouse coordinates in the JTextField when the mouse is moved.
					mouseCoordinates.setText("Mouse Coordinates:  (" + x + "," + y + ")");
				} 
			}
			public void mouseDragged(MouseEvent e) { }
		});
		
		super.getContentPane().add(this.mouseCoordinates, BorderLayout.PAGE_START);
		super.getContentPane().add(cameraPanel, BorderLayout.CENTER);
		super.getContentPane().add(controlPanel, BorderLayout.PAGE_END);
	}

	/**
	 * Called when the CameraFrame object is asked to run itself as a thread.
	 */
	public void run() {

		// Keep looping as long as the refresh thread is true.
		while (this.refreshThreadState) {

			try {
				// Sleep as long as needed...
				Thread.sleep(this.camera.getRefreshRate());
			} catch (InterruptedException i) {
				// do nothing
			}

			// If the camera is not paused, then alert the camera object to
			// refresh itself.
			if (!this.pausedThreadState) {
				this.camera.refresh();
			}

		} /* while */

		// To safely end this thread, we must use return instead of
		// thread.stop() which is deprecated.
		return;
	} 

	/**
	 * Stops the current camera thread.
	 * 
	 */
	public void stop() {
		this.refreshThreadState = false;
	}

	/**
	 * Pauses the current camera thread.
	 * 
	 */
	public void pause() {
		this.pausedThreadState = true;
		this.camera.setPausedState(true);
		this.camera.repaint();
	}

	/**
	 * Resumes the camera thread from pause.
	 * 
	 */
	public void resume() {
		this.pausedThreadState = false;
		this.camera.setPausedState(false);
		this.camera.repaint();
	}



	/**
	 * Returns true of the camera frame is open. False if it is closed.
	 * 
	 * @return
	 */
	public boolean isOpen() {
		return this.isVisible();
	}

	/**
	 * Handles all state changes of the refresh-rate slider.
	 */
	public void stateChanged(ChangeEvent e) {

		// Get the event source, and convert it to a JSlider object.
		JSlider source = (JSlider) e.getSource();

		if (!source.getValueIsAdjusting()) {
			// Set the new refresh rate.
			this.camera.setRefreshRate((long) source.getValue());
		}

	}

}

