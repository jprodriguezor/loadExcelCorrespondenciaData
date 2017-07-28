package co.com.foundation.soaint.documentmanager.web.infrastructure.common;

/**
 * Created by administrador_1 on 04/09/2016.
 */
public class HTTPValidResponse {

    private boolean valid;

    public HTTPValidResponse(boolean valid) {
        this.valid = valid;
    }

    public static HTTPValidResponse newInstance(boolean valid){
        return new HTTPValidResponse(valid);
    }

    public boolean isvalid() {
        return valid;
    }

}
