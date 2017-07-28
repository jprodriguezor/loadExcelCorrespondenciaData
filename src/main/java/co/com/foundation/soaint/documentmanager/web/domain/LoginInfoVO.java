package co.com.foundation.soaint.documentmanager.web.domain;

public class LoginInfoVO {

    private String usuario;
    private String clave;

    public LoginInfoVO() {
    }

    public LoginInfoVO(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

}
