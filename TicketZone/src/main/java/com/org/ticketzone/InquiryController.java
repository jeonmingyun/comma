package com.org.ticketzone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.ticketzone.domain.BoardVO;
import com.org.ticketzone.service.BoardService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class InquiryController {
	private BoardService boardService;

	// 고객센터
	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public String inquiry(Model model) {
		model.addAttribute("suggestList", boardService.boardList());
		return "inquiry";
	}
	
	// 문의 글쓰기 페이지 이동
	@RequestMapping(value = "/inquiryWrite", method = RequestMethod.GET)
	public String inquiryWrite(Model model) {
		return "inquiry/inquiryWrite";
	}

	// 문의 글쓰기 처리
	@RequestMapping(value = "/insertInquiry", method = RequestMethod.POST)
	public String insertInquiry(Model model, BoardVO board) {
		
		boardService.boardInsert(board);
		return "redirect:/inquiry";
	}
		
	// 문의글 상세보기
	@RequestMapping(value = "/showInquiry", method = RequestMethod.GET)
	public String showInquiry(Model model, HttpServletRequest request) {
		String board_no = request.getParameter("board_no");
		model.addAttribute("InquiryUpd", boardService.boardUpdInfo(board_no));

		return "inquiry/showInquiry";
	}
	
	// 문의글 수정페이지 이동
	@RequestMapping(value = "/updInquiry", method = RequestMethod.GET)
	public String updInquiry(Model model, HttpServletRequest request) {
		String board_no = request.getParameter("board_no");
		model.addAttribute("InquiryUpd", boardService.boardUpdInfo(board_no));
		return "inquiry/updInquiry";
	}
	
	// 문의사항 수정처리
	@RequestMapping(value = "/updInquiryForm", method = RequestMethod.POST)
	public String updateBoard(BoardVO board) {
		boardService.boardUpd(board);

		return "redirect:inquiry";
	}
	
	// 문의사항 삭제처리
	@RequestMapping(value = "/delInquiry", method = RequestMethod.GET)
	public String deleteBoard(BoardVO board) {
		boardService.boardDel(board);
			
		return "redirect:/inquiry";
	}	
}
