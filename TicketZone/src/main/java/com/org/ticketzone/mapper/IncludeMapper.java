package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.ReplyVO;

public interface IncludeMapper {
	public int addReply(ReplyVO reply);
	public ArrayList<ReplyVO> replyList();
}
