package com.org.ticketzone;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.ticketzone.domain.EmailVO;
import com.org.ticketzone.service.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	@Inject
	EmailService emailService;
	
	@RequestMapping("write.do")
	public String write() {
		return "email/write";
	}
	
	@RequestMapping("send.do")
	public String send(@ModelAttribute EmailVO vo, Model model) {
		try {
			emailService.sendMail(vo);
			model.addAttribute("message","메일이 발송되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message","이메일 발송 실패...");
		}
		return "/email/write";
		
	}
}
