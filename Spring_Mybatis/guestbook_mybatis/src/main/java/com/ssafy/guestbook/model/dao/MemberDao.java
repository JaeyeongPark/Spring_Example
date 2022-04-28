package com.ssafy.guestbook.model.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.guestbook.model.MemberDto;

public interface MemberDao {

	int idCheck(String id) throws SQLException;
	void registerMember(MemberDto memberDto) throws SQLException;
	MemberDto login(Map<String, String> map) throws SQLException;
	
//	MemberDto getMember(String id) throws SQLException;
//	void updateMember(MemberDto memberDto) throws SQLException;
//	void deleteMember(String id) throws SQLException;
	
}
