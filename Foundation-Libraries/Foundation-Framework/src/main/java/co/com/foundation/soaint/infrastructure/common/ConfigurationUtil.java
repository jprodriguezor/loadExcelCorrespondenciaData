package co.com.foundation.soaint.infrastructure.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class ConfigurationUtil {

    private static Logger LOGGER = LogManager.getLogger(ConfigurationUtil.class.getName());
    private static ResourceBundle bundle;

    private ConfigurationUtil() {
    }

    public static String getProperty(String nameFile,String key) {
        try {
            bundle = ResourceBundle.getBundle(nameFile);
        } catch (Exception e) {
            LOGGER.error("Configuration Util - a system error has occurred", e);
            throw new RuntimeException(e);
        }

        return bundle.getString(key);
    }

}
