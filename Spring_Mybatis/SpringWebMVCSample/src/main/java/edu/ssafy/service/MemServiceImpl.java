package edu.ssafy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ssafy.dto.MemDTO;

import edu.ssafy.repository.MemDAO;
import edu.ssafy.repository.MemDAOImpl;

@Service

public class MemServiceImpl implements MemService{
	@Autowired
	@Qualifier("MemDAOMybatisImpl")
	private MemDAO mdao;	
	
	@Override
	public boolean login(String id, String pw) throws SQLException {
		
		return mdao.login(id, pw);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, 
					rollbackFor = Exception.class, 
					value = "transactionManager")
	public void regMember(MemDTO m) throws Exception {
		//System.out.println("service : "+m);
		
		mdao.regMember(m);
		
	}
	
	public List<MemDTO> listMember() throws Exception {
		return mdao.listMember();	
	}
	
	public void deleteMember(MemDTO m) throws Exception {
		mdao.deleteMember(m);
	}
	
	public void updateMember(MemDTO m) throws Exception {
		mdao.updateMember(m);
	}


	@Override
	public boolean idCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return mdao.idCheck(id);
	}
}
