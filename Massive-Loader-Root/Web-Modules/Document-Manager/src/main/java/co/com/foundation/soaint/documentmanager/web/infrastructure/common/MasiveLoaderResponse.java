package co.com.foundation.soaint.documentmanager.web.infrastructure.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by administrador_1 on 02/10/2016.
 */
public class MasiveLoaderResponse {

    private String error;
    private boolean success;

    private MasiveLoaderResponse() {
        success = true;
    }

    private MasiveLoaderResponse(String error) {
        this.error = error;
        success = false;
    }

    public static MasiveLoaderResponse newInstance(String error) {
        if (StringUtils.isEmpty(error))
            return new MasiveLoaderResponse();
        else
            return new MasiveLoaderResponse(error);
    }

    public static MasiveLoaderResponse newInstance() {
        return new MasiveLoaderResponse();
    }

    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }
}
