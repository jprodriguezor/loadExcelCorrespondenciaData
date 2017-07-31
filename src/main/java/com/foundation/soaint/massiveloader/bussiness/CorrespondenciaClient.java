package com.foundation.soaint.massiveloader.bussiness;

import com.foundation.soaint.massiveloader.web.domain.bd.ComunicacionOficialDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.ApiDelegator;
import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@ApiDelegator
public class CorrespondenciaClient {



    @Value("${backapi.endpoint.url}")
    private String endpoint = "";

    public CorrespondenciaClient() {
        super();
    }

    public Response radicar(ComunicacionOficialDTO comunicacionOficialDTO) {
        WebTarget wt = ClientBuilder.newClient().target(endpoint);
        return wt.path("/correspondencia-web-api/correspondencia")
                .request()
                .post(Entity.json(comunicacionOficialDTO));
    }
}
