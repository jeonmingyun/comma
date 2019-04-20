package com.org.ticketzone.appMngr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	public JSONArray login(@RequestBody OwnerVO ownervo) {
		String owner_id = ownervo.getOwner_id();
		String owner_password = ownervo.getOwner_password();
		ArrayList<OwnerVO> ownerList = ownerService.login(owner_id);
		String db_owner_password;
		JSONArray jarr = new JSONArray();
		JSONObject jobj = new JSONObject();
		
		if( ownerList.size() != 0) {
			db_owner_password = ownerList.get(0).getOwner_password();
			if( db_owner_password.equals(owner_password)) {
				jobj.put("status", "1");
				jarr.add(jobj);
				System.out.println(jarr);
				return jarr; // login success
			} else {
				jobj.put("status", "0");
				jarr.add(jobj);
				System.out.println(jarr);
				return jarr; // pass wrong
			}
		} else {
			jobj.put("status", "-1");
			jarr.add(jobj);
			System.out.println(jarr);
			return jarr; // id wrong
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
