package co.com.foundation.soaint.documentmanager.business.comunicacionoficial;

import co.com.foundation.soaint.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.ApiDelegator;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpServerErrorException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@ApiDelegator
public class CorrespondenciaClient {

    private static Logger log = LogManager.getLogger(CorrespondenciaClient.class.getName());

    @Value("${correspondencia.endpoint.url}")
    private String endpoint = "";

    public CorrespondenciaClient() {
        super();
    }

    public Response radicar(ComunicacionOficialDTO comunicacionOficialDTO) throws SystemException {
        WebTarget wt = ClientBuilder.newClient().target(endpoint);

        Response response = wt.path("/correspondencia-web-api/correspondencia")
                .request()
                .post(Entity.json(comunicacionOficialDTO));
        if (response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) { // 500
            //HttpServerErrorException e = response.readEntity(HttpServerErrorException.class);
            log.error(response.getStatusInfo().getReasonPhrase());
            throw new SystemException(response.getStatusInfo().getReasonPhrase());
            // do stuff
        }
        return response;
    }
}
