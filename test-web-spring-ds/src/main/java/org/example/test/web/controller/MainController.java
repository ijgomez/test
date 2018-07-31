package org.example.test.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/index")
	public String showView(Locale locale) {

		LOGGER.info("Welcome home! The client locale is {}.", locale);

		return "index";
	}

}
