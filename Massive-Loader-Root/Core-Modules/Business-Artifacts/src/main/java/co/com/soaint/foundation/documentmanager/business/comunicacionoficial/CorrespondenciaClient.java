package co.com.soaint.foundation.documentmanager.business.comunicacionoficial;

import co.com.soaint.foundation.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.soaint.foundation.documentmanager.infrastructure.ApiDelegator;
import co.com.soaint.foundation.infrastructure.exceptions.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@ApiDelegator
public class CorrespondenciaClient {

    private static Logger log = LogManager.getLogger(CorrespondenciaClient.class.getName());

    //@Value("${correspondencia.endpoint.url}")
    private String endpoint = "";

    public CorrespondenciaClient() {
        super();
    }

    public Response radicar(ComunicacionOficialDTO comunicacionOficialDTO) throws SystemException {
        log.info("Inicio invocacion servicio correspondencia");
        endpoint = System.getProperty("correspondencia.endpoint.url");
        log.info("Endpoint a consultar para el servicio de correspondencia " + endpoint);
        log.info("Se procede a radicar = " + comunicacionOficialDTO.getCorrespondencia().getNroRadicado());
        WebTarget wt = ClientBuilder.newClient().target(endpoint);

        Response response = wt.path("/correspondencia-web-api/correspondencia")
                .request()
                .post(Entity.json(comunicacionOficialDTO));
        if(response.getStatus() == Response.Status.OK.getStatusCode()){
            log.info("Fin exitoso de la invocacion servicio correspondencia");
            return response;
        }else if(response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            log.error("Error 400 en la invocacion al servicio de correspondencia con razon:  " + response.getStatusInfo().getReasonPhrase());
            throw new SystemException("Error 400: " + response.getStatusInfo().getReasonPhrase());
        } else{
            log.error("Error 500 en la invocacion al servicio de correspondencia con razon:  " + response.getStatusInfo().getReasonPhrase());
            throw new SystemException("Error 500: " + response.getStatusInfo().getReasonPhrase());
        }
    }
}
