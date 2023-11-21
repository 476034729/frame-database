package com.example.database.frame.builder;

import com.example.database.demo.mapper.UserMapper;
import com.example.database.frame.builder.methods.AbstractMethod;
import com.example.database.frame.builder.methods.Insert;
import com.example.database.frame.builder.methods.SelectById;
import com.example.database.frame.config.Configuration;
import com.example.database.frame.constants.FrameConstants;
import com.example.database.frame.enums.SqlCommandType;
import com.example.database.frame.enums.SqlMethod;
import com.example.database.frame.enums.SqlType;
import com.example.database.frame.exception.DataBaseFrameException;
import com.example.database.frame.mapper.BaseMapper;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.metadata.TableInfo;
import com.example.database.frame.metadata.TableInfoHelper;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public class AnnoMapperBuilder {

    private final Configuration configuration;

    public AnnoMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper() {
        if (configuration.getAnnoMapperList().isEmpty()) {
            return;
        }
        Method[] methods = BaseMapper.class.getMethods();
        for (Class<?> clazz : configuration.getAnnoMapperList()) {
            Class currentEntityClass = (Class) (((ParameterizedType) clazz.getGenericInterfaces()[0]).getActualTypeArguments())[0];
            for (Method method : methods) {
                String key = clazz.getName() + FrameConstants.HEAD_LINE + method.getName();
                if (configuration.checkIsExist(key)) {
                    continue;
                }
                Class<?> paramClass = method.getParameterTypes()[0];
                TableInfo tableInfo = TableInfoHelper.getTableInfo(currentEntityClass);
                if (null == tableInfo) {
                    throw new DataBaseFrameException("table entity not exist");
                }
                MapperData mapperData = new MapperData(getSql(tableInfo, method.getName()),
                        method.getReturnType(),
                        paramClass.getName().equals(Object.class.getName()) ? currentEntityClass : paramClass,
                        SqlCommandType.getType(method.getName()), SqlType.ENTITY);
                configuration.putMapperData(key, mapperData);
            }
        }
    }

    private String getSql(TableInfo tableInfo, String methodName) {
        AbstractMethod abstractMethod;
        if (methodName.equals(SqlMethod.INSERT_ONE.getMethod())) {
            abstractMethod = new Insert(configuration);
            return abstractMethod.getSql(tableInfo);
        } else if (methodName.equals(SqlMethod.SELECT_BY_ID.getMethod())) {
            abstractMethod = new SelectById(configuration);
            return abstractMethod.getSql(tableInfo);
        }
        return "";
    }

}
