package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.BoardVO;
import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.NoticeBoardVO;

public interface BoardService {
	public ArrayList<BoardVO> boardList(Criteria cri);
	public ArrayList<BoardVO> boardUpdInfo(String board_no);
	public void boardUpd(BoardVO board);
	public void boardDel(BoardVO board);
	public void boardInsert(BoardVO board);
	public ArrayList<BoardVO> getListWithPaging(Criteria cri);
	public int total(Criteria cri);
	public int SearchCount(Criteria cri);
}
