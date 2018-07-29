package org.example.test;

import java.awt.*;
import javax.swing.*;

public class RotateImage extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4441647073769593052L;
	
	//Declare an Image object for us to use.
    Image image;
    
    // Create a constructor method
    public RotateImage(){
       super();
       // Load an image to play with.
       image = Toolkit.getDefaultToolkit().getImage("src/main/resources/coyote.jpg");
    }
  
    public void paintComponent(Graphics g){
         Graphics2D g2d=(Graphics2D)g; // Create a Java2D version of g.
         g2d.translate(170, 0); // Translate the center of our coordinates.
         g2d.rotate(-0.5);  // Rotate the image by 1 radian.
         g2d.drawImage(image, 0, 0, 200, 200, this);
    }

}
