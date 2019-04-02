package com.org.ticketzone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.ticketzone.domain.ReplyVO;
import com.org.ticketzone.service.IncludeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class IncludeController {
	
	IncludeService includeService;
	
	/* 답글 작성 */
	@RequestMapping(value = "/addReply", method = RequestMethod.POST)
	public String addReply(Model model, ReplyVO reply) {
		includeService.addReply(reply);
		
		return "redirect:/";
	}
	
}