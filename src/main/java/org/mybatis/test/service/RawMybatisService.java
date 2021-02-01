package org.mybatis.test.service;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.test.entity.User;
import org.mybatis.test.mapper.UserMapper;

import javax.sql.DataSource;

public class RawMybatisService  implements MybatisService{

    private DataSource dataSource;

    private SqlSessionFactory sqlSessionFactory;

    public RawMybatisService(DataSource dataSource) {
        this.dataSource = dataSource;
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addMapper(UserMapper.class);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

    }

    public Integer insert(User user) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.insert(user);
        }
    }

    public User selectById(Integer id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            return userMapper.selectById(id);
        }
    }

}
