package edu.ssafy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import edu.ssafy.dto.MemDTO;
import edu.ssafy.util.DBUtil;

@Repository("jdbc")
public class MemDAOImpl implements MemDAO{
	
	public boolean login(String id, String pw) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select 1 from members where id = ? and pw = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemDTO> list = new ArrayList();
		while(rs.next()) {
			return true;
		}
		return false;
		
	}
	
	
	public void regMember(MemDTO m) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into members value(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.getId());
		pstmt.setString(2, m.getPw());
		pstmt.setString(3, m.getName());
		pstmt.setString(4, m.getAddr());
		pstmt.setInt(5, Integer.parseInt(m.getAge()));
		pstmt.executeUpdate();
		DBUtil.close(pstmt,conn);
	}
	
	public ArrayList<MemDTO> listMember() throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select id, pw, name, addr ,age from members ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemDTO> list = new ArrayList();
		while(rs.next()) {
			list.add(new MemDTO(rs.getString("id")
					, rs.getString("pw"), rs.getString("name")
					, rs.getString("addr"), rs.getString("age")));
		}
		return list;
		
	}
	
	public void deleteMember(MemDTO m) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "delete from members where id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.getId());
		pstmt.executeUpdate();
		DBUtil.close(pstmt,conn);
	}
	
	public void updateMember(MemDTO m) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "update members set pw=?, name=?, addr=?, age=? where id=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.getPw());
		pstmt.setString(2, m.getName());
		pstmt.setString(3, m.getAddr());
		pstmt.setInt(4, Integer.parseInt(m.getAge()));
		pstmt.setString(5, m.getId());
		pstmt.executeUpdate();
		DBUtil.close(pstmt,conn);
	}


	@Override
	public boolean idCheck(String id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select id from members where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemDTO> list = new ArrayList();
		while(rs.next()) {
			return false;
		}
		return true;
	}
}
