package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.BoardVO;

public interface BoardService {
	public ArrayList<BoardVO> boardList();
	public ArrayList<BoardVO> boardUpdInfo(String board_no);
	public void boardUpd(BoardVO board);
}
