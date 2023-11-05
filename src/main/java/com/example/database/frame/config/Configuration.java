package com.example.database.frame.config;

import com.example.database.frame.mapper.MapperData;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Configuration {
    private Properties configProperties = new Properties();

    private String configLocation;

    private DataSource dataSource;

    private List<String> mapperList;

    protected final Map<String, MapperData> mappers = new HashMap<>();

    protected final Set<String> loadedResources = new HashSet<>();

    public Configuration(String configLocation) {
        this.configLocation = configLocation;
        init();
    }

    private void init() {
        //记载配置文件，这里使用properties代替xml解析
        loadConfigProperties();
        //初始化数据源信息
        initDataSource();
        //扫描mapper文件
        scanMapper();
    }

    public void loadConfigProperties() {
        if (this.configLocation == null) {
            throw new RuntimeException("configLocation is not null!");
        }
        try (InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/" + configLocation))) {
            this.configProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanMapper() {
        if (this.mapperList == null) {
            mapperList = new ArrayList<>();
        }
        int index = 0;
        String value;
        while ((value = configProperties.getProperty("mapper.resources[" + index + "]")) != null) {
            mapperList.add(value);
            index++;
        }
    }

    public void initDataSource() {
        this.dataSource = new DataSource(configProperties.getProperty("jdbc.url"),
                                         configProperties.getProperty("jdbc.driver"),
                                         configProperties.getProperty("jdbc.username"),
                                         configProperties.getProperty("jdbc.password"));
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public void putMapperData(String key, MapperData mapperData) {
        mappers.put(key, mapperData);
    }

    public void getMapperData(String key) {
        mappers.get(key);
    }
    public Map<String, MapperData> getMappers() {
        return mappers;
    }

    public List<String> getMapperList() {
        return mapperList;
    }

    public void setMapperList(List<String> mapperList) {
        this.mapperList = mapperList;
    }

    public Properties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getConfigLocation() {
        return configLocation;
    }

/*    public static void main(String[] args) throws IOException {
        InputStream fileInputStream= Files.newInputStream(Paths.get("src/main/resources/mapper/UserMapper.xml"));
        XPathParser xPathParser=new XPathParser(fileInputStream);
        XNode xNode=xPathParser.evalNode("/mapper");
        fileInputStream.close();
        System.out.println("====================" +xNode.evalNode("select").getStringBody().trim());
    }*/
}
