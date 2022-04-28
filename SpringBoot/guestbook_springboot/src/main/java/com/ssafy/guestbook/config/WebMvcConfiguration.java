package com.ssafy.guestbook.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.guestbook.interceptor.ConfirmInterceptor;

@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer{

	@Autowired
	private ConfirmInterceptor confirm;
	
	//private final List<String> patterns =Arrays.asList("/guestbook/*","/admin/*","/user/list"); 전략적으로 선택
	private final List<String> patterns =Arrays.asList("/guestbook/*","/user/list");
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(confirm).addPathPatterns(patterns);
	}

	
	
//	@Bean
//  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//      sqlSessionFactoryBean.setDataSource(dataSource);
//      Resource[] arrResource = new PathMatchingResourcePatternResolver()
//              .getResources("classpath:mapper/**/*Mapper.xml");
//      sqlSessionFactoryBean.setMapperLocations(arrResource);
//      sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//      return sqlSessionFactoryBean.getObject();
//  }
	
//	@Bean
//	public ViewResolver internalResourceViewResolver() {
//	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
//	    bean.setPrefix("/WEB-INF/views/");
//	    bean.setSuffix(".jsp");
//	    return bean;
//	}
//	
//	@Bean
//	public BeanNameViewResolver beanNameViewResolver() {
//		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
//		beanNameViewResolver.setOrder(0);
//		return beanNameViewResolver;
//	}

}
