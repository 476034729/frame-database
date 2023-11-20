package com.example.database.demo;

import com.example.database.demo.entity.User;
import com.example.database.demo.mapper.UserMapper;
import com.example.database.frame.builder.AnnoMapperBuilder;
import com.example.database.frame.builder.XMLConfigBuilder;
import com.example.database.frame.config.Configuration;
import com.example.database.frame.session.SqlSession;
import com.example.database.frame.session.SqlSessionFactory;
import com.example.database.frame.session.SqlSessionFactoryBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public class DemoTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration("config.properties");
        AnnoMapperBuilder annoMapperBuilder = new AnnoMapperBuilder(configuration);
//        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(configuration);
        annoMapperBuilder.parseMapper();
//        xmlConfigBuilder.parseMapper();
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        User user = new User();
        user.setId(6L);
        user.setName("123");
        user.setAddress("123");
        user.setNum(2);
        user.setDelFlag(0);
        user.setCreateDate(new Date());
        user.setSchoolId(1L);
        user.setSchoolName("成都小学");
        user.setTeacherId(1L);
        user.setTeacherName("MISS wang");
        user.setType("good");
//        int a=userMapper.insert(user);
    }


}
