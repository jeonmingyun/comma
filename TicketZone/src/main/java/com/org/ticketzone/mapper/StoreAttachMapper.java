package com.org.ticketzone.mapper;

import java.util.ArrayList;

import com.org.ticketzone.domain.StoreAttachVO;

public interface StoreAttachMapper {

	public void StoreImgInsert(StoreAttachVO vo);
	
	public ArrayList<StoreAttachVO> getImage();
}
