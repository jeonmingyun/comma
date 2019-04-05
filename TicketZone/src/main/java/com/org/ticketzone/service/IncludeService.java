package com.org.ticketzone.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.org.ticketzone.domain.ReplyVO;

public interface IncludeService {
	public int addReply(ReplyVO reply);
	public ArrayList<ReplyVO> replyList(String board_no);
	public void reply_delete(String board_no);
	public void reply_update(@Param("reply_content") String reply_content,@Param("board_no") String board_no);
}
