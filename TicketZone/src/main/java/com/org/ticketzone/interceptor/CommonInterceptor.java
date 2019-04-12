package com.org.ticketzone.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.org.ticketzone.domain.BoardVO;
import com.org.ticketzone.service.BoardService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommonInterceptor extends HandlerInterceptorAdapter {
	private BoardService boardService; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String board_no = request.getParameter("board_no");
		ArrayList<BoardVO> board_arr = boardService.boardUpdInfo(board_no);
		String board_password = board_arr.get(0).getBoard_password();

		if ( board_password != null) {
			response.sendRedirect("/board_pass_form?board_no="+board_no);
			return false;
		} else
			return true;
	}
}
