package edu.ssafy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ssafy.dto.MemDTO;

public interface MemDAO {
	public boolean idCheck(String id) throws SQLException ;
	public boolean login(String id, String pw) throws SQLException ;
	public void regMember(MemDTO m) throws SQLException ;
	public List<MemDTO> listMember() throws SQLException ;
	public void deleteMember(MemDTO m) throws SQLException ;
	public void updateMember(MemDTO m) throws SQLException ;
}
