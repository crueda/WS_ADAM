package com.deimos.adam.utils;

import java.io.IOException;
import java.util.Properties;
 
/**
 *
 * @author CARM
 */
public class Configuration {
 
    Properties properties = null;
 
    /** Configuration file name */
    public final static String CONFIG_FILE_NAME = "adam.properties";
 
    public final static String MYSQL_SERVER_IP   	= "mysqlServerIp";
    public final static String MYSQL_SERVER_PORT 	= "mysqlServerPort";
    public final static String MYSQL_SERVER_USER	= "mysqlUser";
    public final static String MYSQL_SERVER_PASSWD	= "mysqlPasswd";
    public final static String MYSQL_DBNAME			= "mysqlDBname";
 
       private Configuration() {
        this.properties = new Properties();
        try {
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//Configuration
 
    /**
     * Implementando Singleton
     *
     * @return
     */
    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }
 
    private static class ConfigurationHolder {
 
        private static final Configuration INSTANCE = new Configuration();
    }
 
    /**
     * Retorna la propiedad de configuración solicitada
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }//getProperty
}
