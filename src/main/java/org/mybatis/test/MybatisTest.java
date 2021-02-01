package org.mybatis.test;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.mybatis.test.entity.User;
import org.mybatis.test.service.RawMybatisService;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Random;

public class MybatisTest {

    private static DataSource dataSource;

    private static final Random random = new Random();

    public static void main(String[] args) {
        initial();
        RawMybatisService mybatisService = new RawMybatisService(dataSource);
        User user = randomUser();
        Integer inserted = mybatisService.insert(user);
        User selectUser = mybatisService.selectById(user.getId());
        System.out.println(selectUser);

    }

    private static User randomUser() {
        User user =  new User();
        user.setUsername(randomString(random.nextInt(10)+10));
        user.setPassword(randomString(random.nextInt(10)+30));
        user.setAvatar(randomString(random.nextInt(10)+40));
        user.setAddress(randomString(random.nextInt(10)+50));
        return user;
    }

    private static String randomString(int size) {
        char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[i] = (char) (random.nextInt(57)+65);
        }
        return new String(chars);
    }

    private static void initial() {
        UnpooledDataSource _dataSource = new UnpooledDataSource();
        String initSqlPath = MybatisTest.class.getClassLoader().getResource("initial.sql").getPath();
        String dbFilePath = MybatisTest.class.getClassLoader().getResource("test.db").getPath();
        _dataSource.setUrl("jdbc:h2:file:"+dbFilePath+ ";INIT=RUNSCRIPT FROM '" + initSqlPath + "'");
        _dataSource.setDriver("org.h2.Driver");
        MybatisTest.dataSource = _dataSource;
    }

}
