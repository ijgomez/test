package org.example.test.fwk.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TablaTag extends SimpleTagSupport {

	private boolean multiple = false;

	private JspWriter html;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {

		int numColumna = 30;
		int numFilas = 70;
		int numCabecera = 2;
		String valorCelda = "&nbsp;";

		html = this.getJspContext().getOut();
		
		
		html.print("<div id='capaExterior'><div id='capaInterior'>");
		html.print("<table>");
		html.print("<thead>");
		for (int i = 0; i < numCabecera; i++) {
			html.print("<tr>");
			for (int j = 0; j < numColumna; j++){
				valorCelda = Integer.toString(j);
				if (j == 0 || j == 1){
					html.print("<th nowrap=\"nowrap\">Columna(" + i + ") " + valorCelda + "</th>");
				} else {
				html.print("<th nowrap=\"nowrap\">Valor(" + i + ") " + valorCelda + "</th>");
				}
			}
			html.print("</tr>");
		}
		html.print("</thead>");
		for (int i = 0; i < numFilas; i++) {
			html.print("<tr>");
			for (int j = 0; j < numColumna; j++){
				valorCelda = Integer.toString(j);
				if (j == 0 || j == 1){
					html.print("<td nowrap=\"nowrap\">Columna " + valorCelda + "</td>");
				} else {
				html.print("<td nowrap=\"nowrap\">Valor " + valorCelda + "</td>");
				}
			}
			html.print("</tr>");
		}
		html.print("</table>");
		html.print("</div></div>");
		html.print("<script type=\"text/javascript\">fijarScrollTabla('capaInterior', true, true)</script>");
		
	}

	/**
	 * @return the multiple
	 */
	public boolean getMultiple() {
		return multiple;
	}

	/**
	 * @param multiple
	 *            the multiple to set
	 */
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

}
