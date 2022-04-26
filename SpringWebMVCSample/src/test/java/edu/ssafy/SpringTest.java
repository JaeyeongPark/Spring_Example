package edu.ssafy;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.ssafy.controller.MemberController;
import edu.ssafy.dto.MemDTO;
import edu.ssafy.repository.MemDAO;
import edu.ssafy.repository.MemDAOMybatisImpl;
import edu.ssafy.service.MemService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:config/application-context.xml","file:src/main/webapp/WEB-INF/servlet-context.xml"})
public class SpringTest {
	@Autowired
	ApplicationContext context;
	
	@Autowired
	MemberController mcon;
	
	@Autowired
	MemService mser;
	
	@Autowired 
	@Qualifier("MemDAOMybatisImpl")
	MemDAO mdao;
	
	@Autowired
	SqlSession session ;
	
	@Autowired
	private DataSource source;
	
	
	@Test
	public void testApplicationContext() {
		assertNotNull(context);
	}
	@Test
	public void testMemberController() {
		assertNotNull(mcon);
		
	}
	@Test
	public void testMemService() {
		assertNotNull(mser);
		
	}
	@Test
	public void testMemDAO() {
		assertNotNull(mdao);
		//mdao.
	}
	
	@Test
	public void testSession() {
		assertNotNull(session);
		//List<MemDTO> list = session.selectList("edu.ssafy.repository.MemDAOMybatisImpl.listMember");
		//System.out.println(list);
	}
	
	@Test
	public void test1() {
		assertNotNull(session);
		String id = "둘리";
		Integer age = 31;
		Map map = new HashMap();
		map.put("id", id);
		map.put("age", age);
		MemDTO m = 
				session.selectOne("edu.ssafy.repository.MemDAOMybatisImpl.mapperTest1",map);
//		System.out.println("-----------------------------------------------------------------");
//		System.out.println(m);
//		System.out.println("------------------------------------------------------------------");
		//System.out.println(list);
	}
	
	@Test
	public void test2() {
		assertNotNull(session);
		Integer age = 31;
		Map map = new HashMap();
		map.put("condition", 2);
		
		map.put("col1", "id");
		map.put("data1", "둘리");
		
		map.put("col2", "addr");
		map.put("data2", "쌍문동");
		
		
		List<MemDTO> m = 
				session.selectList("edu.ssafy.repository.MemDAOMybatisImpl.mapperTest2",map);
		System.out.println("-----------------------------------------------------------------");
		System.out.println(m);
		System.out.println("------------------------------------------------------------------");
		//System.out.println(list);
	}
	
}
