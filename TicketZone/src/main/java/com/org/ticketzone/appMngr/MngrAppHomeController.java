package com.org.ticketzone.appMngr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String login(@RequestBody OwnerVO ownervo) {
		String owner_id = ownervo.getOwner_id();
		String owner_password = ownervo.getOwner_password();
		ArrayList<OwnerVO> ownerList = ownerService.login(owner_id);
		String db_owner_password;

		if( ownerList.size() != 0) {
			db_owner_password = ownerList.get(0).getOwner_password();
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
	public String id_check(@RequestBody OwnerVO ownervo) {
		String owner_id = ownervo.getOwner_id();
		ArrayList<OwnerVO> ownerList = ownerService.login(owner_id);
		
		if( ownerList.size() != 0) {
			return "0";
		} else {
			return "1";
		}
	}
	// id가 있을때 0 return / id가 없을때 1 return 
	//관리자 회원가입
	@ResponseBody
	@RequestMapping(value = "/appregister", method = RequestMethod.POST)
	public String appregister(@RequestBody OwnerVO owner) {
		ownerService.insertOwner(owner); 

		return "1";
	}
}
