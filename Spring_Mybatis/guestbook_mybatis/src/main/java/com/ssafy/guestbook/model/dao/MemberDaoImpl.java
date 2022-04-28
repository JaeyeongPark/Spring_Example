package com.ssafy.guestbook.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.util.SqlMapConfig;

@Repository
public class MemberDaoImpl implements MemberDao {

	private final String NAMESPACE = "com.ssafy.guestbook.model.dao.MemberDao.";
	
	@Override
	public int idCheck(String id) throws SQLException {
		return 0;
	}

	@Override
	public void registerMember(MemberDto memberDto) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE+"registerMember", memberDto);
			sqlSession.commit();
		}
	}

	@Override
	public MemberDto login(Map<String, String> map) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			return sqlSession.selectOne(NAMESPACE+"login", map);	
		}
	}

}
