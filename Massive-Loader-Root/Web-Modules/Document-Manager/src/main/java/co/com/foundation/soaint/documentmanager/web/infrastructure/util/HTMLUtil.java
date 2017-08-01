package co.com.foundation.soaint.documentmanager.web.infrastructure.util;

/**
 * Created by administrador_1 on 18/09/2016.
 */
public class HTMLUtil {

    public static String generateStatusColumn(int value) {
        if (value == 1)
            return "<input type='checkbox' disabled checked='checked'>";
        return "<input type='checkbox' disabled/>";
    }

}
