package com.ssafy.ws.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@MapperScan("com.ssafy.ws.model.repo")
public class MybatisConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource);
		ssfb.setTypeAliasesPackage("com.ssafy.ws.dto");
		Resource[] r;
		try {
			r = applicationContext.getResources("classpath*:mappers/**/*.xml");
			
			ssfb.setMapperLocations(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ssfb;
	}
}
