package com.org.ticketzone.service;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.BoardVO;
import com.org.ticketzone.mapper.InterceptorMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InterceptorServiceImplement implements InterceptorService {
	InterceptorMapper mapper;
	
	@Override
	public BoardVO board_pass_pro(BoardVO board) {
		return mapper.board_pass_pro(board);
	}

}
