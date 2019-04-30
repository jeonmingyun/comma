package com.org.ticketzone.appMem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.service.OwnerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MemAppHomeController {
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(@RequestBody OwnerVO o ) {
		System.out.println(o.getOwner_id());

		return o.getOwner_id() +"app";
	}
}
