package edu.ssafy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.ssafy.dto.MemDTO;

@Repository("MemDAOJDBCTemplatImpl")
public class MemDAOJDBCTemplatImpl implements MemDAO{
	
//	@Autowired
//	private DataSource  source;
	@Autowired
	private JdbcTemplate jt;
	
	
	public boolean login(String id, String pw) throws SQLException {
		MemDTO m = jt.queryForObject("select id, pw, name, addr, age from members where id=? and pw=? " 
				, new Object[] {id,pw}, new MemberMapper());
		if(m == null) {
			return false;
		}else {
			return true;
		}
		
	}
	
	class MemberMapper implements RowMapper<MemDTO>{

		@Override
		public MemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MemDTO m = new MemDTO();
			m.setId(rs.getString("id"));
			m.setPw(rs.getString("pw"));
			m.setName(rs.getString("name"));
			m.setAddr(rs.getString("addr"));
			m.setAge(rs.getString("age"));
			return m;
		}
		
	}
	
	public void regMember(MemDTO m) throws SQLException {
		jt.update("insert into members (id,pw,name,addr,age) values(?,?,?,?,?)"
				,new Object[] {m.getId(),m.getPw(),m.getName(),m.getAddr(),m.getAge()} );
	}
	
	public List<MemDTO> listMember() throws SQLException {
		
		List<MemDTO> list = jt.query("select id, pw, name, addr, age from members", new MemberMapper());
		System.out.println(list);
		return list;
		
	}
	
	public void deleteMember(MemDTO m) throws SQLException {
		jt.update("delete from members where id = ?"
				,new Object[] {m.getId()} );
	}
	
	public void updateMember(MemDTO m) throws SQLException {
		jt.update("update members set pw=? , name=?, addr=?, age=? where id=?"
				,new Object[] {m.getPw(),m.getName(),m.getAddr(),m.getAge(),m.getId()} );
	}


	@Override
	public boolean idCheck(String id) throws SQLException {
		MemDTO m = jt.queryForObject("select id, pw, name, addr, age from members where id=? " 
				, new Object[] {id}, new MemberMapper());
		if(m == null) {
			return true;
		}else {
			return false;
		}
	}
}
