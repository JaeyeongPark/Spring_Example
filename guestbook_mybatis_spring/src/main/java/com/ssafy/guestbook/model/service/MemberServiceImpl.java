package com.ssafy.guestbook.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int idCheck(String id) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).idCheck(id);
	}

	@Override
	public void registerMember(MemberDto memberDto) throws Exception {
		sqlSession.getMapper(MemberMapper.class).registerMember(memberDto);
	}

	@Override
	public MemberDto login(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).login(map);
	}

}
