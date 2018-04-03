package com.wuyongfa.yifa.financialsystem.page;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * mybatis插件，拦截器配置类
 *	
 * @author wuyongfa@chinasoftinc.com
 * 2017年6月26日
 */
@Configuration
@EnableTransactionManagement//支持事务
public class MybatisConfig implements TransactionManagementConfigurer{

	private static final Logger logger = Logger.getLogger(MybatisConfig.class);
	
	@Autowired
	private DataSource dataSource;


	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(PageInterceptor pageInterceptor) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

		//自定义数据库配置的时候，需要将pageHelper的bean注入到Plugins中，如果采用系统默认的数据库配置，则只需要定义pageHelper的bean，会自动注入
		bean.setPlugins(new Interceptor[] { pageInterceptor });
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:mybatis/*.xml"));
		} catch (IOException e1) {
			logger.debug("mybatis 映射xml地址出差");
			e1.printStackTrace();
		}
		try {
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	@Bean
	public PageInterceptor pageInterceptor(){
		PageInterceptor pageInterceptor = new PageInterceptor();
		
		//配置属性
		Properties properties = new Properties();
		//数据库类型 mysql/oracle
		properties.setProperty("dialect", "mysql");
		//分页查询的mapper id所包含的关键字
		properties.setProperty("pageSqlId", "ByPage");
		
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}

}
