/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
//package com.ge.dt.digitaltwin;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class HiveDBConfig {
//
//	@Bean(name = "postgres")
//	@Primary
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource primaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean(name = "hive")
//	@ConfigurationProperties(prefix = "spring.hive.datasource")
//	public DataSource secondaryDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//	
//	@Bean(name = "jdbcpostgres")
//    @Autowired
//    @Qualifier("postgres")
//    public JdbcTemplate slaveJdbcTemplate(DataSource dsSlave) {
//        return new JdbcTemplate(dsSlave);
//    }
//
//    @Bean(name = "jdbchive")
//    @Autowired
//    @Qualifier("hive")
//    public JdbcTemplate masterJdbcTemplate(DataSource dsMaster) {
//        return new JdbcTemplate(dsMaster);
//    }
//}
