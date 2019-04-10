package com.org.ticketzone.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.org.ticketzone.domain.BoardVO;
import com.org.ticketzone.service.BoardService;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	private BoardService boardService; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String board_no = request.getParameter("board_no");
//		System.out.println(boardService.boardUpdInfo(board_no));
//		ArrayList<BoardVO> board_arr = boardService.boardUpdInfo(board_no);
//		System.out.println(board_arr);
//		String board_password = board_arr.get(0).getBoard_password();
//		System.out.println(board_password);
//		if ( == null) {
//			response.sendRedirect("/login");
//			return false;
//		} else
		return true;
	}
}
