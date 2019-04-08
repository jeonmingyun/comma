package com.org.ticketzone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.TestVO;
import com.org.ticketzone.mapper.TestMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestSetviceImplement implements TestService {
	
	private TestMapper mapper;
	
	@Override
	public List<TestVO> getListWithPaging(Criteria cri) {
		
		return mapper.getListWithPaging(cri);
	}

}
