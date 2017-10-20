package org.rapid.web.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@RequestMapping("admin/login")
	public String login() {
		return "hello";
	}
}
