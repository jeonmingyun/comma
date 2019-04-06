package com.org.ticketzone;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.ticketzone.domain.AddressVO;
import com.org.ticketzone.domain.OwnerVO;
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

	// 관리자 첫화면
	@RequestMapping(value = "/mngrOnly")
	public String admin(Model model, HttpSession session) {
		ArrayList<StoreVO> store_arr = new ArrayList<StoreVO>();
		ArrayList<OwnerVO> owner_arr = (ArrayList<OwnerVO>) session.getAttribute("id");
		String owner_id = owner_arr.get(0).getOwner_id();
		
		store_arr = storeService.storeGet(owner_id);
		session.setAttribute("store", store_arr);
		
		return "/mngrOnly/mStore";
	}

	// 매장등록 페이지
	@RequestMapping(value = "/mStore_Register", method = RequestMethod.GET)
	public String mStore_Register(Model model) {
		model.addAttribute("cate", categorieService.categorieFoodList());

		return "mngrOnly/mStore/mStore_Register";
	}

	// 매장등록 처리(insert)
	@RequestMapping(value = "/mStore_Reg", method = RequestMethod.POST)
	public String Register(Model model, StoreVO store, AddressVO address) {
		addressService.insertAddress(address);
		storeService.storeRegister(store);

		return "/mngrOnly/mStore";
	}

	// 매장수정 페이지 이동
	@RequestMapping(value = "/updmStore_Register", method = RequestMethod.GET)
	public String updmStore_Register(StoreVO storeVO, Model model, HttpServletRequest request) {
		String license = storeVO.getLicense_number();
		model.addAttribute("updmStore", storeService.storeUpdate(license));

		return "/mngrOnly/mStore/updmStore_Register";
	}

	// 매장수정 처리
	@RequestMapping(value = "/updmStore_Complete", method = RequestMethod.POST)
	public String updmStore_Complete(StoreVO store) {
		storeService.storeUpdCom(store);

		return "redirect:/mngrOnly";
	}

	// 고객관리 페이지 이동
	@RequestMapping(value = "/mCustomer", method = RequestMethod.GET)
	public String mCustomer(Model model) {
		return "/mngrOnly/mCustomer";
	}

	// 매장관리 페이지 이동
	@RequestMapping(value = "/mState", method = RequestMethod.GET)
	public String mState(Model model) {
		return "/mngrOnly/mState";
	}
}
