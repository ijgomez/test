package org.example.test.ds.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LoginController {
	
	@Autowired
	LocaleResolver localeResolver;
	
	 @RequestMapping("/login")
	 public String showView(HttpServletRequest request, HttpServletResponse response) {
//		 ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	     // LocaleResolver localeResolver = (LocaleResolver) applicationContext.getBean("localeResolver");
	     //   LocaleContextHolder.setLocale(localeResolver.resolveLocale(request));
		 System.out.println(".....");
	     System.out.println(LocaleContextHolder.getLocale());
	     System.out.println(LocaleContextHolder.getLocaleContext());
	     System.out.println(localeResolver.resolveLocale(request));
	     

		 return "login";
	 }
	

}
