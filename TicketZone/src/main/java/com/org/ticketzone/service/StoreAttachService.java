package com.org.ticketzone.service;

import java.util.ArrayList;

import com.org.ticketzone.domain.StoreAttachVO;

public interface StoreAttachService {
	
	public void StoreImgInsert(StoreAttachVO vo);
	
	public ArrayList<StoreAttachVO> getImage();
}
