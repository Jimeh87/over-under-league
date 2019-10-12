package com.overunderleague;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class FooController {

	@GetMapping
	String foo() {
		return "foo";
	}
}
