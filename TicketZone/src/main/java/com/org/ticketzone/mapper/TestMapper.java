package com.org.ticketzone.mapper;

import java.util.List;

import com.org.ticketzone.domain.Criteria;
import com.org.ticketzone.domain.TestVO;



public interface TestMapper {
	public List<TestVO> getListWithPaging(Criteria cri);
}
