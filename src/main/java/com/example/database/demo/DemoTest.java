package com.example.database.demo;

import com.example.database.demo.entity.User;
import com.example.database.frame.builder.XMLConfigBuilder;
import com.example.database.frame.config.Configuration;
import com.example.database.frame.session.SqlSession;
import com.example.database.frame.session.SqlSessionFactory;
import com.example.database.frame.session.SqlSessionFactoryBuilder;

import java.util.List;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public class DemoTest {

    public static void main(String[] args) {
        Configuration configuration=new Configuration("config.properties");
        XMLConfigBuilder xmlConfigBuilder=new XMLConfigBuilder(configuration);
        xmlConfigBuilder.parseMapper();
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session=sqlSessionFactory.openSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        List<User> users=userMapper.queryList("1","1");
//        System.out.println("====================" +users.get(0));
        List<User> users2=userMapper.queryList("1","1");
//        System.out.println("====================" +users2.get(0));
    }
}
