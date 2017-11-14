package co.com.soaint.correspondencia.massiveloader.web.rest;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.artemis.utils.json.JSONArray;
import org.apache.activemq.artemis.utils.json.JSONException;
import org.apache.activemq.artemis.utils.json.JSONObject;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jorge on 13/11/2017.
 */
@Log4j2
public class ClienteReglaMedioRecepcion {

    private static String droolsEndpoint = System.getProperty("drools-endpoint");
    private static String droolsAuthToken = System.getProperty("drools-token");
    private static final String REGLA = "/regla";
    private static final String COD_MEDIO_RECEPCION = "codMedioRecepcion";
    private static final String TIEMPO_RESPUESTA = "tiempoRespuesta";
    private static final String COD_UNIDA_TIEMPO = "codUnidaTiempo";
    private static final String INICIO_CONTEO = "inicioConteo";

    public static void main(String[] args) {
        getmetricasTiempoDrools("TL-DOCOF");
    }

    public static Map<String, String> getmetricasTiempoDrools(String code) {
        Map<String, String> datos = new HashMap<>();
        String payload = "{\"lookup\":\"ksession-rules\",\"commands\": [{\"insert\": { \"return-object\": true,\"out-identifier\":\"MedioRecepcion\",\"object\":{\"co.com.soaint.sgd.model.MedioRecepcion\":{\"codMedioRecepcion\":";
        payload = payload + '"' + code + '"';
        payload = payload + "}}}},{\"fire-all-rules\":\"\"}]}";
        log.info("PAYLOAD de Entrada = " + payload);
        log.info("**************************Invocando al Servicio: " + droolsEndpoint + REGLA + "**************************");
        WebTarget wt = ClientBuilder.newClient().target(droolsEndpoint);
        Response response = wt.path(REGLA)
                .request()
                .header("Authorization", "Basic " + droolsAuthToken)
                .header("X-KIE-ContentType", "json")
                .header("Content-Type", "application/json")
                .post(Entity.json(payload));


        JSONObject data = null;
        try {
            JSONObject jsonObject = new JSONObject(response.readEntity(String.class));
            log.info("PAYLOAD de salida = " + jsonObject.toString());
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject executionresults = result.getJSONObject("execution-results");
            JSONArray results = executionresults.getJSONArray("results");
            data = results.getJSONObject(0);
            JSONObject dataFinal = data.getJSONObject("value").getJSONObject("co.com.soaint.sgd.model.MedioRecepcion");
            log.info(COD_MEDIO_RECEPCION + "=" +  dataFinal.getString(COD_MEDIO_RECEPCION));
            log.info(TIEMPO_RESPUESTA + "=" +dataFinal.getString(TIEMPO_RESPUESTA));
            log.info(COD_UNIDA_TIEMPO + "=" +dataFinal.getString(COD_UNIDA_TIEMPO));
            log.info(INICIO_CONTEO + "=" +dataFinal.getString(INICIO_CONTEO));

            datos.put(COD_MEDIO_RECEPCION, dataFinal.getString(COD_MEDIO_RECEPCION));
            datos.put(TIEMPO_RESPUESTA, dataFinal.getString(TIEMPO_RESPUESTA));
            datos.put(COD_UNIDA_TIEMPO, dataFinal.getString(COD_UNIDA_TIEMPO));
            datos.put(INICIO_CONTEO, dataFinal.getString(INICIO_CONTEO));

        } catch (JSONException e) {
            log.error("Error invocando al servicio de regla", e);
            //TODO: pendiente definir si se debe relanzar la excepcion.
        }
        log.info("**************************Fin de la invocacion al Servicio: " + droolsEndpoint + "/regla" + "**************************");
        return datos;
    }
}
