package com.org.ticketzone.appMngr;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.service.AppMngrService;
import com.org.ticketzone.service.OwnerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MngrAppHomeController {
	OwnerService ownerService;
	AppMngrService appMngrService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody OwnerVO ownervo) {
		String owner_id = ownervo.getOwner_id();
		String owner_password = ownervo.getOwner_password();
		ArrayList<OwnerVO> ownerList = ownerService.login(owner_id);
		String db_owner_password;
		System.out.println(ownervo);
		if (ownerList.size() != 0) {
			db_owner_password = ownerList.get(0).getOwner_password();
			if (db_owner_password.equals(owner_password))
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

		if (ownerList.size() != 0) {
			return "0";
		} else {
			return "1";
		}
	}

	// id가 있을때 0 return / id가 없을때 1 return
	// 관리자 회원가입
	@ResponseBody
	@RequestMapping(value = "/appregister", method = RequestMethod.POST)
	public String appregister(@RequestBody OwnerVO owner) {
		ownerService.insertOwner(owner);

		return "1";
	}

	@ResponseBody
	@RequestMapping(value = "/mngr_db_login", method = RequestMethod.POST)
	public JSONArray mngr_db_login(@RequestBody OwnerVO o) {
		JSONArray arr = new JSONArray();
		String owner_id = o.getOwner_id();

		arr.add(appMngrService.ownerList());
		arr.add(appMngrService.categorieList());
//		arr.add(appMngrService.memberList());
		arr.add(appMngrService.storeList(owner_id));
		arr.add(appMngrService.menuList());
		arr.add(appMngrService.NumberTicketList());
		System.out.println(appMngrService.storeList(owner_id));
		return arr;
	}

	@ResponseBody
	@RequestMapping(value = "/m_chart", method = RequestMethod.POST)
	public JSONArray m_chart() {
		JSONArray arr = new JSONArray();
		arr.add(appMngrService.M_chart());
		System.out.println("도착");
		return arr;		
	}
	
	@ResponseBody
	@RequestMapping(value = "/success_ticket", method = RequestMethod.POST)
	public void success_ticket(@RequestBody NumberTicketVO vo) {
		appMngrService.success_ticket(vo);
		appMngrService.success_status(vo);
	}

}