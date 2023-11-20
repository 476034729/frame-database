package com.example.database.frame.config;

import com.example.database.frame.annotation.Mapper;
import com.example.database.frame.annotation.TableName;
import com.example.database.frame.exception.DataBaseFrameException;
import com.example.database.frame.mapper.BaseMapper;
import com.example.database.frame.mapper.MapperData;
import com.example.database.frame.metadata.TableFieldInfo;
import com.example.database.frame.metadata.TableInfoHelper;
import com.example.database.frame.util.ScannerUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class Configuration {
    private Properties configProperties = new Properties();

    private String configLocation;

    private DataSource dataSource;

    private List<String> mapperList;

    private Set<Class<?>> annoMapperList;

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
        //扫描实体
        scanEntity();
        //扫描注解mapper
        scanAnnoMapper();
    }

    public void loadConfigProperties() {
        if (this.configLocation == null) {
            throw new DataBaseFrameException("configLocation is not null!");
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

    public void scanEntity() {
        String packageName = configProperties.getProperty("package.entity");
        Set<Class<?>> classes = ScannerUtil.scanClass(packageName);
        classes = classes.stream().filter(it -> it.isAnnotationPresent(TableName.class)).collect(Collectors.toSet());
        if (classes.isEmpty()) {
            return;
        }
        TableInfoHelper.initTableInfo(this, classes);
    }

    public void scanAnnoMapper() {
        String packageName = configProperties.getProperty("package.mapper");
        Set<Class<?>> classes = ScannerUtil.scanClass(packageName);
        this.annoMapperList = classes.stream()
                                     .filter(it -> it.isAnnotationPresent(Mapper.class))
                                     .filter(it -> it.getInterfaces()[0].isAssignableFrom(BaseMapper.class))
                                     .collect(Collectors.toSet());
    }

    public void initDataSource() {
        this.dataSource = new DataSource(configProperties.getProperty("jdbc.url"), configProperties.getProperty("jdbc.driver"),
                                         configProperties.getProperty("jdbc.username"), configProperties.getProperty("jdbc.password"));
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
    public boolean checkIsExist(String key) {
        return mappers.containsKey(key);
    }

    public MapperData getMapperData(String key) {
        return mappers.get(key);
    }

    public Map<String, MapperData> getMappers() {
        return mappers;
    }

    public List<String> getMapperList() {
        return mapperList;
    }

    public Set<Class<?>> getAnnoMapperList() {
        return annoMapperList;
    }

    public void setAnnoMapperList(Set<Class<?>> annoMapperList) {
        this.annoMapperList = annoMapperList;
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
