package com.org.ticketzone;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.NumberTicketVO;
import com.org.ticketzone.domain.StoreCriteria;
import com.org.ticketzone.domain.StoreMenuVO;
import com.org.ticketzone.domain.StorePageDTO;
import com.org.ticketzone.domain.StoreVO;
import com.org.ticketzone.service.NumberTicketService;
import com.org.ticketzone.service.StoreMenuService;
import com.org.ticketzone.service.StoreService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class StoreController {
	private StoreService storeService;
	private StoreMenuService storeMenuService;
	private NumberTicketService numberTicketService;
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
	
	// 제휴매장 메뉴리스트 출력
	@RequestMapping(value = "/goMenu", method = RequestMethod.GET)
	public String goMenu(Model model, HttpServletRequest request, StoreMenuVO menu) {
		
		
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
		
		
		return "store/store_menu";
	}
	
	@ResponseBody
	@RequestMapping(value = "store_getListTocate", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String getListTocate(Model model, StoreMenuVO menu, HttpServletRequest request){
		String license_number = request.getParameter("license_number");
		String menu_cate = request.getParameter("menu_cate");
//		System.out.println(test2 + test);
//		System.out.println(menu);
//		System.out.println(storeMenuService.getListTocate(menu));
			
		return menu_cate; 
	}
	
	@RequestMapping(value = "/store_chart")
	public String test2(Model model, HttpServletRequest request) {
		String owner_id = request.getParameter("onwer_id");
		System.out.println(owner_id + "이게널?");
		model.addAttribute("today", numberTicketService.today());				
		return "/store/store_chart";
	}
	
	@ResponseBody
	@RequestMapping(value ="/store_chartDel", method= RequestMethod.POST)
	public ArrayList<NumberTicketVO> test3(Model model, String today, String day) {
		
		
		return numberTicketService.getTotalDel(today);
	}
	
	@ResponseBody
	@RequestMapping(value ="/store_chartAdd", method=RequestMethod.POST)
	public ArrayList<NumberTicketVO> test4(Model model, String today, String day){
		
		System.out.println(numberTicketService.getTotalAdd(today));
		
		
		return numberTicketService.getTotalAdd(today);
	}
}
