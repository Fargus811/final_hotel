package by.sergeev.hotel.utils;

import java.util.ResourceBundle;

public class MappingPagesMannager {

    private static final String CONFIG_FILE = "mapping.properties";
    public static String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIG_FILE);
        return resourceBundle.getString(key);
    }
}
