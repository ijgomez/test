package org.example.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.example.test.core.domain.PeticionDS;
import org.example.test.core.domain.RespuestaDS;
import org.example.test.core.helper.SQLParser;
import org.example.test.core.services.DataSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/datasource")
public class DataSourceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceController.class);

	@Autowired
	@Qualifier("peticionDSValidator")
	private Validator validator;
	
	@Autowired
	private DataSourceService dataSourceService;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showView() {
		ModelAndView model;
		PeticionDS peticionDS;
		
		peticionDS = new PeticionDS();
		
		model = new ModelAndView("datasource-view");
		model.addObject("peticionDS", peticionDS);
		
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String execute(@Valid @ModelAttribute PeticionDS peticionDS, BindingResult bindingResult, Model model) {

		
		if (!bindingResult.hasErrors()) {
			// TODO
			
			List<String> sqls = SQLParser.parser(peticionDS.getSql());
			
			List<RespuestaDS> respuestaDSs = dataSourceService.execute(sqls);
			
			model.addAttribute("respuestaDSs", respuestaDSs);
			//TODO
			
		}

		model.addAttribute("datasource-entity", peticionDS);

		return "datasource-view";
	}

}
