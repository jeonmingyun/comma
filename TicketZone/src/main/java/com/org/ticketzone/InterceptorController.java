package com.org.ticketzone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class InterceptorController {

	@RequestMapping(value = "/mngr_login", method = RequestMethod.GET)
	public String mngr_login(Model model) {
		return " ";
	}
}
