package org.example.test.ds.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {


	 @RequestMapping("/logout")
	 public String showView(HttpServletRequest request, HttpServletResponse response) {
		 return "logout";
	 }
	

}