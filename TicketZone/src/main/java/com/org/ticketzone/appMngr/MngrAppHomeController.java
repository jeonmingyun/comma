package com.org.ticketzone.appMngr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.service.OwnerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MngrAppHomeController {
	OwnerService ownerService;
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		String owner_id = request.getParameter("owner_id");
		String owner_password = request.getParameter("owner_password");
		ArrayList<OwnerVO> ownervo = ownerService.login(owner_id);
		String db_owner_password = ownervo.get(0).getOwner_password();
		System.out.println(ownervo);
		
		if( ownervo.size() != 0) {
			if( db_owner_password.equals(owner_password))
				return "1"; // login success
			 else 
				return "0"; // pass wrong
		} else {
			return "-1"; // id wrong
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/id_check", method = RequestMethod.POST)
	public String id_check(HttpServletRequest request) {
		String owner_id = request.getParameter("owner_id");
		ArrayList<OwnerVO> ownervo = ownerService.login(owner_id);
		
		if( ownervo.size() != 0) {
			return "0";
		} else {
			return "1";
		}
	}
	// id가 있을때 0 return / id가 없을때 1 return 
	//관리자 회원가입
	@ResponseBody
	@RequestMapping(value = "/appregister", method = RequestMethod.POST)
	public String appregister(Model model, OwnerVO owner) {
		ownerService.insertOwner(owner); 

		return "1";
	}
}
