package com.org.ticketzone;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.StoreAttachVO;
import com.org.ticketzone.domain.StoreCriteria;
import com.org.ticketzone.domain.StorePageDTO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.service.StoreAttachService;
import com.org.ticketzone.service.StoreService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class StoreController {
	private StoreService storeService;
	
	// 제휴매장
	@RequestMapping(value = "/store", method = RequestMethod.GET)
	public String sotre(Model model, StoreCriteria Cri, StoreVO store) {
		
		
//		model.addAttribute("list", storeService.storeList(Cri));
		if(store.getSido() == null || store.getSido().equals("") || store.getSido().equals("선택")) {
		int total = storeService.total(Cri);
		model.addAttribute("list", storeService.getListWithPaging(Cri));
		model.addAttribute("pageMaker", new StorePageDTO(Cri, total));
		
		} else {
		int total = storeService.searchTotal(store);
		model.addAttribute("list", storeService.getListWithSearchPaging(Cri));
		model.addAttribute("pageMaker", new StorePageDTO(Cri, total));
		}
		System.out.println(Cri);
		return "store";
	}
	
	@ResponseBody
	@RequestMapping(value = "/storSearch", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String storeSearch(Model model, StoreCriteria Cri, StoreVO store) {
		
		return "success";
	}
	

	// 제휴매장 상세페이지처리
	@ResponseBody
	@RequestMapping(value = "/store.do", method = RequestMethod.POST)
	public void store_do(Model model, @RequestBody String license, HttpSession session) {
		ArrayList<StoreVO> storeList = new ArrayList<StoreVO>();
		storeList = storeService.storeInfo(license);

		// session.setAttribute("info", store.get(0).getLicense_number());
		session.setAttribute("storeList", storeList);
	}

	// 제휴매장 상세처리
	@RequestMapping(value = "/store/store_info", method = RequestMethod.GET)
	public String store_info(Model model) {

		return "store/store_info";
	}
}
