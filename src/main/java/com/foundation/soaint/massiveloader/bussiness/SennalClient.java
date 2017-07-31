package com.foundation.soaint.massiveloader.bussiness;

import com.foundation.soaint.massiveloader.web.domain.CorrespondenciaVO;
import com.foundation.soaint.massiveloader.web.domain.bpm.EntradaProcesoDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.ApiDelegator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by g2o on 30-Jul-17.
 */
@ApiDelegator
public class SennalClient {
    public static void main(String[] argv) {
        final String uri = "http://192.168.3.242:28080/correspondencia-business-services/services/correspondencia-web-api/correspondencia";

    /*numeroRadicado = jsonObject.getString("numeroRadicado");
    requiereDigitalizacion = jsonObject.getInt("requiereDigitalizacion");
    requiereDistribucion = jsonObject.getInt("requiereDistribucion");
    kcontext.setVariable("numeroRadicado", numeroRadicado);
    kcontext.setVariable("requiereDigitalizacion", requiereDigitalizacion);
    */

        CorrespondenciaVO newEmployee = new CorrespondenciaVO();

        RestTemplate restTemplate = new RestTemplate();
        CorrespondenciaVO result = restTemplate.postForObject(uri, newEmployee, CorrespondenciaVO.class);

        System.out.println(result);
    }

    @Value("${backapi.endpoint.url}")
    private String endpoint = "";

    public SennalClient() {
        super();
    }

    public Response sennalInicio(EntradaProcesoDTO entradaProcesoDTO) {
        WebTarget wt = ClientBuilder.newClient().target(endpoint);
        return wt.path("/proceso/sennal/inicio")
                .request()
                .post(Entity.json(entradaProcesoDTO));
    }


}
