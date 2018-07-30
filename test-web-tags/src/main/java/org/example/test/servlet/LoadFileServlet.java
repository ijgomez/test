package org.example.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.example.test.fwk.file.Fichero;

public class LoadFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5039410514895651242L;

	/**
	 * Constructor of the object.
	 */
	public LoadFileServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart){
			out.println("Formulario Para archivos");
			
		}
		
//		 Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

//		 Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

//		 Parse the request
		try {
			List items = upload.parseRequest(request);
			if (items != null){
				for (int i = 0; i < items.size(); i++){
					 FileItem f = (FileItem)items.get(i);
					 try {
					 out.println(f.getName() + ":====> " +  Fichero.createChecksum(f.getInputStream()));
					 } catch (Exception e) {
						 out.println(f.getName() + ": " +  e.getClass());
					}
				}
			}
		} catch (FileUploadException e) {
			out.println("ERROR");
			//e.printStackTrace();
		}
		
		
		
		
		
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
