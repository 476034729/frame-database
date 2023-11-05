package com.example.database.frame.builder;



import com.example.database.frame.exception.DataBaseFrameException;
import com.example.database.frame.config.Configuration;
import com.example.database.frame.parse.XNode;
import com.example.database.frame.parse.XPathParser;

import java.io.InputStream;
import java.util.List;


public class XMLMapperBuilder {

    private final XPathParser parser;
    private final String resource;
    private final Configuration configuration;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) {
        this(new XPathParser(inputStream), configuration, resource);
    }

    public XMLMapperBuilder(XPathParser parser, Configuration configuration, String resource) {
        this.parser = parser;
        this.resource = resource;
        this.configuration = configuration;
    }


    public void parse() {
        if (!configuration.isResourceLoaded(resource)) {
            configurationElement(parser.evalNode("/mapper"));
            configuration.addLoadedResource(resource);
        }
    }


    private void configurationElement(XNode context) {
        try {
            String namespace = context.getStringAttribute("namespace");
            if (namespace == null || namespace.isEmpty()) {
                throw new DataBaseFrameException("Mapper's namespace cannot be empty");
            }
            List<XNode> list=context.evalNodes("select|insert|update|delete");
            for (XNode xNode : list) {
                final XMLStatementBuilder statementParser = new XMLStatementBuilder(namespace,xNode,configuration);
                statementParser.parseStatementNode();
            }
        } catch (Exception e) {
            throw new DataBaseFrameException("Error parsing Mapper XML. The XML location is '" + resource + "'. Cause: " + e, e);
        }
    }


}
