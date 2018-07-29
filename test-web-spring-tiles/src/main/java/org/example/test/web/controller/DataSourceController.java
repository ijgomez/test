package org.example.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/datasource")
public class DataSourceController {

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showView() {
		ModelAndView model;

		model = new ModelAndView("datasource-view");
		model.addObject("datasource", "datasource information");
		
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String execute(BindingResult bindingResult, Model model) {
		return "datasource-view";
	}

}
