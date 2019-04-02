package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.ReplyVO;

@Service
public class IncludeServiceImplement implements IncludeService{

	@Override
	public int addReply(ReplyVO reply) {
		return 0;
	}

	@Override
	public ArrayList<ReplyVO> replyList() {
		return null;
	}

}
