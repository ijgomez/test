//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.9.210/xslt/JavaClass.xsl

package org.example.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.example.test.form.UserLoginForm;

public class UserLoginAction extends Action {
 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserLoginForm userLoginForm = (UserLoginForm) form;

		if (userLoginForm.getUserName().equals("test") && userLoginForm.getPassword().equals("test")) {
			request.setAttribute("userName", userLoginForm.getUserName());
			return mapping.findForward("success");
		}

		return mapping.findForward("failure");
	}

}
