
package com.org.ticketzone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.CoordinatesVO;
import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.OwnerVO;
import com.org.ticketzone.domain.StoreAttachVO;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.service.CategorieService;
import com.org.ticketzone.service.CoordinatesService;
import com.org.ticketzone.service.MemberService;
import com.org.ticketzone.service.NumberTicketService;
import com.org.ticketzone.service.StoreAttachService;
import com.org.ticketzone.service.StoreMenuService;
import com.org.ticketzone.service.StoreService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class MngrOnlyController {

	private StoreService storeService;
	private CategorieService categorieService;
	private CoordinatesService coordinatesService;
	private NumberTicketService numberTicketService;
	private MemberService memberService;
	private StoreAttachService storeAttachService;
	private StoreMenuService storeMenuService;
	// 관리자 첫화면
	@RequestMapping(value = "/mngrOnly")
	public String admin(Model model, HttpSession session) {
		ArrayList<StoreVO> store_arr = new ArrayList<StoreVO>();
		ArrayList<OwnerVO> owner_arr = (ArrayList<OwnerVO>) session.getAttribute("id");
		String owner_id = owner_arr.get(0).getOwner_id();

		store_arr = storeService.storeGet(owner_id);
		model.addAttribute("store", storeService.storeGet(owner_id));
//		String license_number = store_arr.get(0).getLicense_number();
//		session.setAttribute("store", store_arr);

		return "/mngrOnly/mStore";
	}
	
		
	@RequestMapping(value = "mTicketSet")
	public String mTicketSet(Model model, StoreVO store) {		
		model.addAttribute("license_number", store.getLicense_number());
		return "/mngrOnly/mStoreAdmin/mTicketSet";
	}
	
	//메뉴 관리 페이지
	@RequestMapping(value = "mMenuAdmin")
	public String mMenuAdmin(Model model, HttpServletRequest request, StoreMenuVO menu) {
		
		String license_number = request.getParameter("license_number");

		String checkMenu = storeMenuService.checkMenu(license_number);
		System.out.println(menu);
		if(checkMenu.equals("not")) {
			System.out.println("메뉴가없음");
		} else {
			model.addAttribute("menu",storeMenuService.menuList(license_number));
			model.addAttribute("tab", storeMenuService.getCate(menu));
			System.out.println(menu.getMenu_cate());
		} 
		
		if(menu.getMenu_cate() != null) {
			model.addAttribute("menu",storeMenuService.getListTocate(menu));
			model.addAttribute("tab", storeMenuService.getCate(menu));
			model.addAttribute("license_number", license_number);
			System.out.println("정상기능");
		} else {
			model.addAttribute("license_number", license_number);
		}
				
		return "/mngrOnly/mStoreAdmin/mMenuAdmin";
	}
	
	//메뉴 카테고리 검색리스트
	@ResponseBody
	@RequestMapping(value = "getListTocate", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String getListTocate(Model model, StoreMenuVO menu, HttpServletRequest request){
		String license_number = request.getParameter("license_number");
		String menu_cate = request.getParameter("menu_cate");
//		System.out.println(test2 + test);
//		System.out.println(menu);
//		System.out.println(storeMenuService.getListTocate(menu));
			
		return menu_cate; 
	}
	
	//카테고리추가
	@ResponseBody
	@RequestMapping(value = "loadCate", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String loadCate(Model model, StoreMenuVO menu, HttpServletRequest request) {
		String menu_cate = request.getParameter("menu_cate");
		
		return menu_cate;
	}
	//메뉴추가
	@RequestMapping(value = "insertMenu")
	public String insertMenu(Model model, StoreMenuVO menu, HttpServletRequest request) {
			
			
		model.addAttribute("add", menu.getMenu_cate());
		model.addAttribute("tab", storeMenuService.getCate(menu));
		model.addAttribute("license_number", menu.getLicense_number());
		
		return "/mngrOnly/mStoreAdmin/mMenuAdmin"; 
	}
	
   
	   
	//최종 메뉴추가 처리
	
	@ResponseBody
	@RequestMapping(value = "insertAccess", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String insertAccess(@RequestBody ArrayList<StoreMenuVO> menu, HttpServletRequest request) {
		String license_number = menu.get(0).getLicense_number();
		System.out.println(license_number);
		String checkMenu = storeMenuService.checkMenu(license_number);


//		for(StoreMenuVO vo : menu) {
//			System.out.println(vo.getLicense_number());
//		}
//		System.out.println(menu);
		
	
		if(checkMenu.equals("not")) {			
			storeMenuService.firstMenu(menu);
		} else {
			storeMenuService.addMenu(menu);
		}
		
		return menu.get(0).getMenu_cate();
		
	}
	
	//메뉴 수정
	@ResponseBody
	@RequestMapping(value = "updateMenu", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String menuUpdate(@RequestBody ArrayList<StoreMenuVO> menu, HttpServletRequest request) {
		
		
		storeMenuService.updateMenu(menu);
		
		return menu.get(0).getMenu_cate();
	}
	
	//메뉴 삭제
	@ResponseBody
	@RequestMapping(value = "deleteMenu", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String menuDelete(@RequestBody ArrayList<StoreMenuVO> menu) {
		
		storeMenuService.deleteMenu(menu);
		
		return menu.get(0).getMenu_cate();
	}
	

	// 관리자 로그아웃
	@RequestMapping(value = "/mngrLogout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "mngr";
	}

	// 매장등록 페이지
	@RequestMapping(value = "/mStore_Register", method = RequestMethod.GET)
	public String mStore_Register(Model model) {
		model.addAttribute("cate", categorieService.categorieFoodList());

		return "mngrOnly/mStore/mStore_Register";
	}

	// 매장등록 처리(insert)
	@RequestMapping(value = "/mStore_Reg", method = RequestMethod.POST)
	public String Register(Model model, StoreVO store, CoordinatesVO coor,StoreAttachVO vo) {
		
		storeService.storeRegister(store);
		coordinatesService.insertXY(coor);
		storeAttachService.StoreImgInsert(vo);
//		System.out.println(store);
//		System.out.println(vo);
//		System.out.println(coor);
		return "/mngrOnly/mStore";
	}

	// 매장수정 페이지 이동
	@RequestMapping(value = "/updmStore_Register", method = RequestMethod.GET)
	public String updmStore_Register(StoreVO storeVO, Model model, CoordinatesVO coor, StoreAttachVO vo) {
		String license = storeVO.getLicense_number();
		model.addAttribute("license_number", storeVO.getLicense_number());
		model.addAttribute("coor", coordinatesService.XYList(license));
		model.addAttribute("updmStore", storeService.storeUpdate(license));
		model.addAttribute("cate", categorieService.categorieFoodList());

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
	public String mCustomer(Model model, HttpServletRequest request) {
		String license_number = request.getParameter("license_number");
		model.addAttribute("license_number", numberTicketService.waitList(license_number));
		model.addAttribute("wait", numberTicketService.tWait(license_number));
		model.addAttribute("success", numberTicketService.tSuccess(license_number));
		model.addAttribute("cancel", numberTicketService.tCancel(license_number));
		model.addAttribute("absence", numberTicketService.tAbsence(license_number));
//		System.out.println(model.addAttribute("absence", numberTicketService.tAbsence(license_number)));
		model.addAttribute("member", memberService.memberTest());

		return "/mngrOnly/mCustomer";
	}

	@ResponseBody
	@RequestMapping(value = "/getLicense", method = RequestMethod.POST)
	public String getLicense(Model model, HttpServletRequest request) {
		String license_number = request.getParameter("license_number");
		model.addAttribute("license_number", numberTicketService.waitList(license_number));

		return " ";
	}


	// 번호표 발급
	@ResponseBody
	@RequestMapping(value = "/issue_ticket", method = RequestMethod.POST)
	public String isuue_ticket(Model model, NumberTicketVO ticket) {

		String codeMaker = numberTicketService.codeSelect(ticket);
		System.out.println(codeMaker + "code");
		System.out.println(ticket + "ticket");
		if (codeMaker.equals("not")) {
			System.out.println("코드를 발급해야합니다!");
			numberTicketService.firstCode(ticket);
			numberTicketService.makeTicket(ticket);
		} else {
			System.out.println("이미코드가 있습니다!");
			numberTicketService.plusTicket(ticket);
		}

		return " ";
	}

	// 번호표 순환(대기인원수 -1)
	@RequestMapping(value = "/minus_ticket", method = RequestMethod.POST)
	public String minus_ticket(Model model, NumberTicketVO ticket) {
		numberTicketService.minusTicket(ticket);
		System.out.println("정상결제입니다.");
		return "/mngrOnly/mCustomer";
	}

	// 번호표 발급취소(취소한사람 기준 -1)
	@RequestMapping(value = "/cancel_ticket", method = RequestMethod.POST)
	public String cancel_ticket(Model model, NumberTicketVO ticket) {

		numberTicketService.cancelStatus(ticket);
		numberTicketService.cancelTicket(ticket);

		return "/mngrOnly/mCustomer";
	}

	// 번호표 부재(부재중인사람 기준 -1)
	@RequestMapping(value = "/absence_ticket", method = RequestMethod.POST)
	public String absence_ticket(Model model, NumberTicketVO ticket) {

		numberTicketService.absenceStatus(ticket);
		
		numberTicketService.cancelTicket(ticket);
		return "/mngrOnly/mCustomer";
	}
	
	

//	@RequestMapping(value = "/getLicense", method = RequestMethod.POST)
//	public String getLicense(Model model, NumberTicketVO ticket) {
//		model.addAttribute("ticket", numberTicketService.waitList(ticket));
//		return "/mngrOnly/mCustomer";
//	}
}
