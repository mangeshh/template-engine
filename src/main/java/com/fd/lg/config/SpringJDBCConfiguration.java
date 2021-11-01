package com.fd.lg.config;

import javax.sql.DataSource;
import com.fd.lg.persist.DataSelector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringJDBCConfiguration {

    @Value( "${jdbc.driver}" )
    private String driver;

    @Value( "${jdbc.url}" )
    private String jdbcUrl;

    @Value( "${jdbc.user}" )
    private String user;

    @Value( "${jdbc.credential}" )
    private String credential;

    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(credential);
        return dataSource;
    }

    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public DataSelector selector(){
        DataSelector selector = new DataSelector();
        selector.setJdbcTemplate(jdbcTemplate());
        return selector;


    }

}
