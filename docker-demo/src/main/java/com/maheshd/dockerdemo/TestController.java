/**
 * 
 */
package com.maheshd.dockerdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mahesh
 *
 */
@RestController
public class TestController {

	@GetMapping
	public String helloWord() {
		
		return "Hello World!!!";
	}
}
