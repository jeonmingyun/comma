package com.org.ticketzone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.ticketzone.domain.AddressVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.service.AddressService;
import com.org.ticketzone.service.CategorieService;
import com.org.ticketzone.service.StoreService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MngrOnlyController {
	
	private StoreService storeService;
	private CategorieService categorieService;
	private AddressService addressService;
	
	// 관리자 페이지
	@RequestMapping(value = "/mngrOnly")
	public String admin(Model model) {
		
		return "/mngrOnly/mStore";
	}
	
	// 매장등록 페이지
	@RequestMapping(value = "/mStore_Register", method = RequestMethod.GET)
	public String mStore_Register(Model model) {
		
		model.addAttribute("cate", categorieService.categorieFoodList());
		
		return "mngrOnly/mStore/mStore_Register";
	}
	
	@RequestMapping(value = "/mStore_Reg", method = RequestMethod.POST)
	public String Register(Model model, StoreVO store, AddressVO address) {
			
		addressService.insertAddress(address);
		storeService.storeRegister(store);
		
		
		return "/mngrOnly/mStore";
	}

}
