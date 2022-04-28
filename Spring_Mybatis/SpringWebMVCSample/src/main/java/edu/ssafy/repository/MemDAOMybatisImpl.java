package edu.ssafy.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.dto.MemDTO;

@Repository("MemDAOMybatisImpl")
public class MemDAOMybatisImpl implements MemDAO{
	@Autowired
	SqlSession session;
	String path = "edu.ssafy.repository.MemDAOMybatisImpl.";
	@Override
	public boolean idCheck(String id) throws SQLException {
		Map m = new HashMap();
		m.put("id", id);
		Object selectOne = session.selectOne(path+"idCheck",m);
		if(selectOne == null) {
			return true;
		}
		return false;
	}
	@Override
	public boolean login(String id, String pw) throws SQLException {
		Map m = new HashMap();
		m.put("id", id);
		m.put("pw", pw);
		Object selectOne = session.selectOne(path+"login",m);
		if(selectOne != null) {
			return true;
		}
		return false;
		
	}

	@Override
	public void regMember(MemDTO m) throws SQLException {
		
		session.insert(path+"regMember",m);
	}

	@Override
	public List<MemDTO> listMember() throws SQLException {
		
		List<MemDTO> list = session.selectList(path+"listMember");
		return list;
	}

	@Override
	public void deleteMember(MemDTO m) throws SQLException {
		session.delete(path+"deleteMember",m);
	}

	@Override
	public void updateMember(MemDTO m) throws SQLException {
		// TODO Auto-generated method stub
		session.update(path+"updateMember",m);
	}

}
