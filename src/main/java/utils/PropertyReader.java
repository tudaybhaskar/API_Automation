package utils;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static String getPropertyValue(String key){

        String propertyValue;
        FileInputStream fis;
        File file;
        Properties prop;

        try{
            file = new File("./config.properties");
            fis = new FileInputStream(file);
            prop = new Properties();
            prop.load(fis);
            propertyValue = prop.getProperty(key);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return propertyValue;

    }
}
