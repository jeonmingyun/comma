package com.org.ticketzone.appMem;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.MemberVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.service.AppMemService;
import com.org.ticketzone.service.MemberService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class AppMemHomeController {
	MemberService memberService;
	AppMemService appMemService;
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(@RequestBody OwnerVO o ) {
		System.out.println(o.getOwner_id());

		return o.getOwner_id() +"app";
	}

	@ResponseBody
	@RequestMapping(value = "/mem_db_login", method = RequestMethod.POST)
	public JSONArray mem_db_login(@RequestBody MemberVO mvo ) {
		JSONArray arr = new JSONArray();
		
		arr.add(appMemService.ownerList());
		arr.add(appMemService.storeList());
		arr.add(appMemService.menuList());
		arr.add(appMemService.categorieList());
		arr.add(appMemService.coordinatesList());
		
		return arr;
	}
}
