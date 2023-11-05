
package com.example.database.frame.builder;


import com.example.database.frame.config.Configuration;
import com.example.database.frame.enums.SqlCommandType;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.parse.XNode;
import com.example.database.frame.util.ResolveUtil;

import java.util.Locale;

import static com.example.database.frame.constants.FrameConstants.HEAD_LINE;

public class XMLStatementBuilder {

    private final XNode context;
    private final String nameSpace;
    private final Configuration configuration;

    public XMLStatementBuilder(String nameSpace, XNode context, Configuration configuration) {
        this.nameSpace = nameSpace;
        this.context = context;
        this.configuration = configuration;
    }

    public void parseStatementNode() {
        String id = context.getStringAttribute("id");
        String nodeName = context.getNode().getNodeName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
        String parameterType = context.getStringAttribute("parameterType");
        Class<?> parameterTypeClass = ResolveUtil.resolveClass(parameterType);
        String resultType = context.getStringAttribute("resultType");
        Class<?> resultTypeClass = ResolveUtil.resolveClass(resultType);
        String key = nameSpace + HEAD_LINE + id;
        MapperData mapperData=new MapperData(context.getStringBody().trim(),resultTypeClass,parameterTypeClass,sqlCommandType);
        configuration.putMapperData(key,mapperData);
    }
}
