//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.9.210/xslt/JavaClass.xsl

package org.example.test.form;

import org.apache.struts.action.ActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-12-2005
 * 
 * XDoclet definition:
 * @struts:form name="userLoginForm"
 */
public class UserLoginForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	private static final long serialVersionUID = 2731456012330921937L;

	/** password property */
	private String password;

	/** userName property */
	private String userName;

	// --------------------------------------------------------- Methods

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Returns the userName.
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/** 
	 * Set the userName.
	 * @param userName The userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}

