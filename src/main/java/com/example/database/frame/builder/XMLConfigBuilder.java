package com.example.database.frame.builder;


import com.example.database.frame.config.Configuration;
import com.example.database.frame.exception.DataBaseFrameException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author tz
 * @createDate 2023-11-03
 */
public class XMLConfigBuilder {


    private final Configuration configuration;

    public XMLConfigBuilder(Configuration configuration) {
        this.configuration = configuration;
    }


    public void parseMapper() {
        if (null == configuration.getMapperList()) {
            throw new DataBaseFrameException("mapper is not scan or mapper is null");
        }
        for (String mapperPath : configuration.getMapperList()) {
            String resource = "src/main/resources/" + mapperPath;
            try (InputStream inputStream = Files.newInputStream(Paths.get(resource))) {
                XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource);
                mapperParser.parse();
            } catch (Exception e) {
                throw new DataBaseFrameException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
            }
        }

    }


}
