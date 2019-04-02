package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.ReplyVO;

public interface IncludeService {
	public int addReply(ReplyVO reply);
	public ArrayList<ReplyVO> replyList();
}
