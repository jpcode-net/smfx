/*******************************************************
 * Copyright 2018 jpcode
 * contact http://www.jpcode.net/
 * 
 * --- smfx
 * 
 ********************************************************/

package net.jpcode.smfx.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.jpcode.smfx.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 应用Root配置类
 * @author Billy Zhang
 *
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages= {Constants.COMPONENT_PACKAGE},
		excludeFilters= {
				@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
		})
public class AppConfig {

	@Autowired
	private AppProperties appProperties;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName( appProperties.getString("jdbc.driverClassName") );
		dataSource.setUrl( appProperties.getString("jdbc.url") );
		dataSource.setUsername( appProperties.getString("jdbc.username") );
		dataSource.setPassword( appProperties.getString("jdbc.password") );
		dataSource.setMaxActive( appProperties.getInteger("jdbc.maxActive") );
		dataSource.setMaxWait( appProperties.getInteger("jdbc.maxWait") );
		dataSource.setMaxIdle( appProperties.getInteger("jdbc.maxIdle") );
		dataSource.setMinIdle( appProperties.getInteger("jdbc.minIdle") );
		
		return dataSource;
	}
	
}
