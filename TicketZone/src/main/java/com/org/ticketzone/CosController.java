package com.org.ticketzone;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class CosController {
	// ÄÚ½º
	@RequestMapping(value = "/cos", method = RequestMethod.GET)
	public String cos(Model model) throws JsonProcessingException {
		return "cos";
	}
}
