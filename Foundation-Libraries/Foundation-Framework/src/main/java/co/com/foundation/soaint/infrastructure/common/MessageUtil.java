package co.com.foundation.soaint.infrastructure.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class MessageUtil {

    private static Logger LOGGER = LogManager.getLogger(MessageUtil.class.getName());
    private static ResourceBundle bundle;

    private MessageUtil() {
    }

    static {
        try {
            bundle = ResourceBundle.getBundle("messages");
        } catch (Exception e) {
            LOGGER.error("Message Util - a system error has occurred", e);
            throw new RuntimeException(e);
        }
    }

    // ----------------------------

    public static String getMessage(String key) {
        return bundle.getString(key);
    }

}
