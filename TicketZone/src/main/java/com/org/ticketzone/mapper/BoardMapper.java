package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.BoardVO;

public interface BoardMapper {
	public ArrayList<BoardVO> boardList();
	public ArrayList<BoardVO> boardUpdInfo(String board_no);
	public void boardUpd(BoardVO board);
	public void boardDel(BoardVO board);
	public void boardInsert(BoardVO board);
}
