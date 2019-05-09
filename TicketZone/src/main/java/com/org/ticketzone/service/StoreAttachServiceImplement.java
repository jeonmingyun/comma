package com.org.ticketzone.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.StoreAttachVO;
import com.org.ticketzone.mapper.StoreAttachMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreAttachServiceImplement implements StoreAttachService {

	private StoreAttachMapper mapper;
	
	@Override
	public void StoreImgInsert(StoreAttachVO vo) {
		
		mapper.StoreImgInsert(vo);
		
	}

	@Override
	public ArrayList<StoreAttachVO> getImage() {

		return mapper.getImage();
	}

}
