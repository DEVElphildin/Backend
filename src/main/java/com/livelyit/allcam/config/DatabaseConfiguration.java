package com.livelyit.allcam.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.livelyit.allcam.mapper", sqlSessionFactoryRef="allcamSqlSessionFactory")
@EnableTransactionManagement
public class DatabaseConfiguration {
    @Bean(name = "allcamDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.allcam.datasource")
    public DataSource allcamDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "allcamSqlSessionFactory")
    @Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("allcamDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	
    @Bean(name = "allcamSqlSessionTemplate")
    @Primary
	public SqlSessionTemplate sqlSession(@Qualifier("allcamSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}