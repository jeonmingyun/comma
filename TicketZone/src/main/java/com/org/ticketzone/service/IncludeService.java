package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.ReplyVO;

public interface IncludeService {
	public int addReply(ReplyVO reply);
	public ArrayList<ReplyVO> replyList(String board_no);
	public void reply_delete(String board_no);
	public void reply_update(String reply_content, String board_no);
}
