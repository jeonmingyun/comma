package com.org.ticketzone.appMngr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
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
		String db_owner_password;
		
		if( ownervo.size() != 0) {
			db_owner_password = ownervo.get(0).getOwner_password();
			if( db_owner_password.equals(owner_password))
				return "1"; // login success
			 else 
				return "0"; // pass wrong
		} else {
			return "-1"; // id wrong
		}
		
	}
}