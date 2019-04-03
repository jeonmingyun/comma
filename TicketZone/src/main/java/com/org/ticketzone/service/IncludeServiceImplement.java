package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.ReplyVO;
import com.org.ticketzone.mapper.IncludeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IncludeServiceImplement implements IncludeService{
	IncludeMapper mapper;
	
	@Override
	public int addReply(ReplyVO reply) {
		return mapper.addReply(reply);
//		return 0;
	}

	@Override
	public ArrayList<ReplyVO> replyList(String board_no) {
		return mapper.replyList(board_no);
	}

	@Override
	public void reply_delete(String board_no) {
		mapper.reply_delete(board_no);
		
	}
}
