package org.example.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class SpeedometerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9221055596329955269L;
	
	private Font font;
	private final int PAD;
	private double phi;
	private Image image;

	public SpeedometerPanel() {
		loadImage();
		font = new Font("lucida sans demibold", Font.PLAIN, 18);
		PAD = 5;
		phi = -90.0; // 12 o'clock
		setBackground(Color.white);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();

		// draw dial
		double dia = w * 7 / 8;
		double x = (w - dia) / 2;
		double y = (h - dia) / 2 + dia / 4;
		double start = 0.0;
		double extent = 180.0;
		Shape arc = new Arc2D.Double(x, y, dia, dia, start, extent, Arc2D.OPEN);
		g2.draw(arc);

		// draw image
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		int icx = (w - imageWidth) / 2;
		int icy = (h - imageHeight) / 2;
		Shape oldClip = g2.getClip();
		g2.setClip(arc);
		g2.drawImage(image, icx, icy, this);
		g2.setClip(oldClip);

		// draw 10 tick marks
		double thetaInc = extent / 10;
		double theta = 0.0;
		double x0 = x + dia / 2;
		double y0 = y + dia / 2;
		double r = dia / 2;
		double x1, y1, x2, y2;
		Shape tick;
		for (int i = 0; i < 11; i++) {
			x1 = x0 + (r - 4) * Math.cos(Math.toRadians(theta));
			y1 = y0 + (r - 4) * Math.sin(Math.toRadians(theta));
			x2 = x0 + r * Math.cos(Math.toRadians(theta));
			y2 = y0 + r * Math.sin(Math.toRadians(theta));
			tick = new Line2D.Double(x1, y1, x2, y2);
			g2.draw(tick);
			theta -= thetaInc;
		}

		// draw tick labels
		g2.setFont(font);
		FontRenderContext frc = g2.getFontRenderContext();
		LineMetrics lm;
		theta = 0.0;
		for (int i = 0; i < 11; i++) {
			String s = String.valueOf((10 - i) * 10);
			float width = (float) font.getStringBounds(s, frc).getWidth();
			lm = font.getLineMetrics(s, frc);
			float height = lm.getAscent();
			double cos = Math.cos(Math.toRadians(theta));
			double sin = Math.sin(Math.toRadians(theta));
			//double diag = Math.sqrt(width * width + height * height) / 2;
			double cx = x0 + (r - PAD) * cos - (width * cos) / 2;
			double cy = y0 + (r - PAD) * sin - (height * sin) / 2;
			float sx = (float) cx - width / 2;
			float sy = (float) cy + height / 2;
			g2.drawString(s, sx, sy);
			theta -= thetaInc;
		}

		// draw needle
		double x3, y3, x4, y4;
		// radius is one half of the minimum width of the needle /_____\
		// with the origin (x0,y0) located midway between \ /
		// the low tip and the place of maximum width \ x /
		double radius = 5.0; // \_/
		x1 = x0 - radius * Math.cos(Math.toRadians(phi));
		y1 = y0 - radius * Math.sin(Math.toRadians(phi));
		//Shape s0 = new Line2D.Double(x0, y0, x1, y1);
		double phiOffset = Math.toDegrees(Math.atan2(2.5, 2.5));
		x2 = x0 + radius * Math.sqrt(2) * Math.cos(Math.toRadians(phi - phiOffset));
		y2 = y0 + radius * Math.sqrt(2) * Math.sin(Math.toRadians(phi - phiOffset));
		Shape s1 = new Line2D.Double(x1, y1, x2, y2);
		x3 = x0 + (r - 8) * Math.cos(Math.toRadians(phi));
		y3 = y0 + (r - 8) * Math.sin(Math.toRadians(phi));
		Shape s2 = new Line2D.Double(x2, y2, x3, y3);
		x4 = x0 + radius * Math.sqrt(2) * Math.cos(Math.toRadians(phi + phiOffset));
		y4 = y0 + radius * Math.sqrt(2) * Math.sin(Math.toRadians(phi + phiOffset));
		Shape s3 = new Line2D.Double(x3, y3, x4, y4);
		Shape s4 = new Line2D.Double(x4, y4, x1, y1);
		GeneralPath needle = new GeneralPath(s1);
		needle.append(s2, true);
		needle.append(s3, true);
		needle.append(s4, true);
		g2.setPaint(Color.red);
		g2.fill(needle);
	}

	public void setSpeed(int speed) {
		phi = 1.80 * speed - 180.0;
		repaint();
	}

	private void loadImage() {
		String fileName = "/coyote.jpg";
		try {
			URL url = getClass().getResource(fileName);
			image = ImageIO.read(url);
		} catch (MalformedURLException mue) {
			System.out.println("Bad URL: " + mue.getMessage());
		} catch (IOException ioe) {
			System.out.println("IOE: " + ioe.getMessage());
		}
	}
}
