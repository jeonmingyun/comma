package com.org.ticketzone;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.BoardVO;
import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.InqAttachVO;
import com.org.ticketzone.domain.PageDTO;
import com.org.ticketzone.domain.ReplyVO;
import com.org.ticketzone.service.BoardService;
import com.org.ticketzone.service.CategorieService;
import com.org.ticketzone.service.IncludeService;
import com.org.ticketzone.service.InqAttachService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class InquiryController {
	private BoardService boardService;
	private IncludeService includeService;
	private CategorieService categorieService;
	private InqAttachService inqAttachService;

	// 고객센터
	@RequestMapping(value = "/inquiry", method = RequestMethod.GET)
	public String inquiry(Model model, Criteria Cri) {
		int total = boardService.total(Cri);
		model.addAttribute("suggestList", boardService.boardList(Cri));
		model.addAttribute("list", boardService.getListWithPaging(Cri));
		model.addAttribute("pageMaker", new PageDTO(Cri, total));
		
		return "inquiry";
	}

	// 문의 글쓰기 페이지 이동
	@RequestMapping(value = "/inquiryWrite", method = RequestMethod.GET)
	public String inquiryWrite(Model model) {
		model.addAttribute("cate", categorieService.boardCateList());
		
		return "inquiry/inquiryWrite";
	}

	// 문의 글쓰기 처리
	@RequestMapping(value = "/insertInquiry", method = RequestMethod.POST)
	public String insertInquiry(Model model, BoardVO board, InqAttachVO inq, HttpServletRequest request) {
		String content = board.getBoard_content();
		content = content.replace("\n", "<br />");
	
		board.setBoard_content(content);
		
		if(inq.getInq_filename() == null) {	
			boardService.boardInsert(board);
		} else {
			boardService.boardInsert(board);
			inqAttachService.Inq_fileinsert(inq);
		}
		return "redirect:/inquiry";
	}
	
	// 문의 글쓰기 비번 체크
	@RequestMapping(value = "/board_pass_form", method = RequestMethod.GET)
	public String inquiry_pass_form() {
		return "interceptor/board_pass_form";
	}
		
	// 문의 글쓰기 비번 체크
	@RequestMapping(value = "/board_pass_pro", method = RequestMethod.POST)
	public String inquiry_pass_pro(Model model, BoardVO board) {
		BoardVO board_pass_ck = boardService.board_pass(board);
		String board_no = board.getBoard_no() + "";
		model.addAttribute("InquiryUpd", boardService.boardUpdInfo(board_no));
		model.addAttribute("replyList", includeService.replyList(board_no));
		model.addAttribute("board_no", board_no);
		
		if(board_pass_ck != null) { // pass 성공시
			return "inquiry/showInquiry";
		} else { // pass 실패시
			return "redirect:board_pass_form";
		}
		
	}
	
	// 문의글 상세보기
	@RequestMapping(value = "/showInquiry", method = RequestMethod.GET)
	public String showInquiry(Model model, HttpServletRequest request) {
		String board_no = request.getParameter("board_no");
		model.addAttribute("InquiryUpd", boardService.boardUpdInfo(board_no));
		model.addAttribute("replyList", includeService.replyList(board_no));
		model.addAttribute("file", inqAttachService.Inq_findByBoard_no(Integer.parseInt(board_no)));
		return "inquiry/showInquiry";
	}

	// 문의글 수정페이지 이동
	@RequestMapping(value = "/updInquiry", method = RequestMethod.GET)
	public String updInquiry(Model model, HttpServletRequest request) {
		String board_no = request.getParameter("board_no");
		model.addAttribute("InquiryUpd", boardService.boardUpdInfo(board_no));
		model.addAttribute("file", inqAttachService.Inq_findByBoard_no(Integer.parseInt(board_no)));
		return "inquiry/updInquiry";
	}

	// 문의사항 수정처리
	@ResponseBody
	@RequestMapping(value = "/updInquiryForm", method = RequestMethod.POST)
	public String updateBoard(BoardVO board, InqAttachVO inq) {
		String content = board.getBoard_content();
		content = content.replace("\n", "<br />");
		
		board.setBoard_content(content);
		
		if(inq.getInq_uuid() == null) {
			System.out.println("파일없음");
			boardService.boardUpd(board);
		} else {
			inqAttachService.Inq_fileModifyinsert(inq);
			boardService.boardUpd(board);
		}
		
		return "success";
	}

	// 문의사항 삭제처리
	@RequestMapping(value = "/delInquiry", method = RequestMethod.GET)
	public String deleteBoard(BoardVO board) {
		boardService.boardDel(board);
			
		return "redirect:/inquiry";
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/addReply", method = RequestMethod.POST) public
	 * ArrayList<ReplyVO> searchCustomer(Model model, ReplyVO
	 * reply,HttpServletRequest request) { String board_no =
	 * request.getParameter("board_no"); includeService.addReply(reply); // insert
	 * 
	 * return includeService.replyList(board_no); //select }
	 */
	

	// 댓글 추가
	@ResponseBody
	@RequestMapping(value = "/addReply", method = RequestMethod.POST)
	public ArrayList<ReplyVO> searchCustomer(Model model, ReplyVO reply,HttpServletRequest request) {
		String board_no = request.getParameter("board_no");
		
		String addReply_content = reply.getReply_content();
		addReply_content = addReply_content.replace("\n","<br/>");
		reply.setReply_content(addReply_content);
		
		includeService.addReply(reply); // insert
		
		return includeService.replyList(board_no); //select
	}
	
	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/reply_delete", method = RequestMethod.POST)
	public String replyDelete(Model model, HttpServletRequest request) {
		String board_no = request.getParameter("board_no");
		includeService.reply_delete(board_no);
		
		return "";
	}
	
	// 댓글 수정
	@ResponseBody
	@RequestMapping(value = "/reply_update", method = RequestMethod.POST)
	public ArrayList<ReplyVO> replyUpdate(Model model, HttpServletRequest request) {
		String reply_content = request.getParameter("reply_content");
		String board_no = request.getParameter("board_no");
		
		reply_content = reply_content.replace("\n","<br/>");
		includeService.reply_update(reply_content, board_no);

		return includeService.replyList(board_no);
	}

}
