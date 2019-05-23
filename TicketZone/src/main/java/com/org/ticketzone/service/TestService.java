package com.org.ticketzone.service;

import java.util.List;

import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.TestVO;

public interface TestService {
	public List<TestVO> getListWithPaging(Criteria cri);
}
