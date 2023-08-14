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
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.livelyit.allcam.mapper.sms", sqlSessionFactoryRef="smsSqlSessionFactory")
@EnableTransactionManagement
public class SMSDatabaseConfiguration {
    @Bean(name = "smsDataSource")
    @ConfigurationProperties(prefix = "spring.sms.datasource")
    public DataSource smsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "smsSqlSessionFactory")
	public SqlSessionFactory smsSqlSessionFactory(@Qualifier("smsDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
    
    @Bean(name = "smsSqlSessionTemplate")
	public SqlSessionTemplate smsSqlSession(@Qualifier("smsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}