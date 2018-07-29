package org.example.test.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/traces")
public class TracesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TracesController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showView() {

		LOGGER.info("View Traces.");

		return "traces-view";
	}

}
