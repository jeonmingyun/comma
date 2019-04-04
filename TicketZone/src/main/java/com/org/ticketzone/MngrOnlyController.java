
package com.org.ticketzone;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.AddressVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.service.AddressService;
import com.org.ticketzone.service.CategorieService;
import com.org.ticketzone.service.NumberTicketService;
import com.org.ticketzone.service.StoreService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MngrOnlyController {

	private StoreService storeService;
	private CategorieService categorieService;
	private AddressService addressService;
	private NumberTicketService numberTicketService;

	// 관리자 첫화면
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

	// 매장등록 처리(insert)
	@RequestMapping(value = "/mStore_Reg", method = RequestMethod.POST)
	public String Register(Model model, StoreVO store, AddressVO address) {

		addressService.insertAddress(address);
		storeService.storeRegister(store);

		return "/mngrOnly/mStore";
	}

	// 고객관리 페이지 이동

	@RequestMapping(value = "/mCustomer", method = RequestMethod.GET)
	public String mCustomer(Model model, HttpServletRequest request) {
		String license_number = request.getParameter("license_number");
		model.addAttribute("license_number", numberTicketService.waitList(license_number));

		return "/mngrOnly/mCustomer";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLicense", method = RequestMethod.POST)
		public String getLicense(Model model, HttpServletRequest request) {
		String license_number = request.getParameter("license_number");
		model.addAttribute("license_number", numberTicketService.waitList(license_number));
		
		return " ";
	}

	// 매장관리 페이지 이동

	@RequestMapping(value = "/mState", method = RequestMethod.GET)
	public String mState(Model model) {

		return "/mngrOnly/mState";
	}

	// 번호표 발급
	@RequestMapping(value = "/issue_ticket", method = RequestMethod.POST)
	public String isuue_ticket(Model model, NumberTicketVO ticket) {
		String codeMaker = numberTicketService.codeSelect(ticket);
		if (codeMaker.equals("not")) {
			System.out.println("코드를 발급해야합니다!");
			numberTicketService.firstCode(ticket);
			numberTicketService.makeTicket(ticket);
		} else {
			System.out.println("이미코드가 있습니다!");
			numberTicketService.plusTicket(ticket);
		}

		return "/mngrOnly/mCustomer";
	}

	// 번호표 순환(대기인원수 -1)
	@RequestMapping(value = "/minus_ticket", method = RequestMethod.POST)
	public String minus_ticket(Model model, NumberTicketVO ticket) {
		numberTicketService.minusTicket(ticket);
		System.out.println("정상결제입니다.");
		return "/mngrOnly/mCustomer";
	}
	
//	@RequestMapping(value = "/getLicense", method = RequestMethod.POST)
//	public String getLicense(Model model, NumberTicketVO ticket) {
//		model.addAttribute("ticket", numberTicketService.waitList(ticket));
//		return "/mngrOnly/mCustomer";
//	}
}
