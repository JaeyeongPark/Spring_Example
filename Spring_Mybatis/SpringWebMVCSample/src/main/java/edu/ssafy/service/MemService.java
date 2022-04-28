package edu.ssafy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ssafy.dto.MemDTO;


public interface MemService {
	public boolean idCheck(String id) throws Exception ;
	public boolean login(String id, String pw) throws Exception ;
	public void regMember(MemDTO m) throws Exception ;
	public List<MemDTO> listMember() throws Exception ;
	public void deleteMember(MemDTO m) throws Exception ;
	public void updateMember(MemDTO m) throws Exception ;
}
