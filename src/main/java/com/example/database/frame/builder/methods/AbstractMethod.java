package com.example.database.frame.builder.methods;

import com.example.database.frame.config.Configuration;
import com.example.database.frame.constants.FrameConstants;
import com.example.database.frame.metadata.TableInfo;

/**
 * @author TANGZHEN
 * @createDate 2023-11-18
 */
public abstract class AbstractMethod {

    protected Configuration configuration;
    protected String name;
    public AbstractMethod(Configuration configuration,String name) {
        this.configuration = configuration;
        this.name=name;
    }


    public abstract String getSql(TableInfo tableInfo);
}
