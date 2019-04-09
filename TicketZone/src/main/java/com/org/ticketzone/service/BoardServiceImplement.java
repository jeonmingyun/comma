package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.BoardVO;
import com.org.ticketzone.mapper.BoardMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardServiceImplement implements BoardService {
	private BoardMapper mapper;
	@Override
	public ArrayList<BoardVO> boardList() {
		return mapper.boardList();
	}
	
	@Override
	public ArrayList<BoardVO> boardUpdInfo(String board_no) {
		return mapper.boardUpdInfo(board_no);
	}
	
	@Override
	public BoardVO board_pass(String board_no) {
		return mapper.board_pass(board_no);
	}
	
	@Override
	public void boardUpd(BoardVO board) {	
		mapper.boardUpd(board);
	}

	@Override
	public void boardDel(BoardVO board) {
		mapper.boardDel(board);
		
	}

	@Override
	public void boardInsert(BoardVO board) {
		mapper.boardInsert(board);
		
	}
			
}
