/**
 * 
 */
package com.maheshd.dockerdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mahesh
 *
 */
@Controller
public class TestController {

	@Value("${logo.path}")
	private String logoPath;
	
	@GetMapping
	public ModelAndView helloWord() {
		ModelAndView model = new ModelAndView("index.html");
		model.addObject("logo", logoPath);
		return model;
	}
}
