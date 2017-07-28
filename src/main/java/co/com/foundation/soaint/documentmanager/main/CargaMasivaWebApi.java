package co.com.foundation.soaint.documentmanager.main;

import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.transaction.SystemException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/carga-masiva-web-api")
@Produces({"application/json", "application/xml"})
@Consumes({"application/json", "application/xml"})
public class CargaMasivaWebApi {
    private static Logger logger = LogManager.getLogger(CargaMasivaWebApi.class.getName());

    //@Autowired
    //GestionarAgente boundary;

    public CargaMasivaWebApi() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @PUT
    @Path("/carga-masiva/cargar-fichero")
    public void cargaFichero(CargaMasivaDTO cargaMasivaDTO)throws BusinessException, SystemException {
        logger.info("processing rest request - actualizar estado agente");
        //boundary.cargaFichero(cargaMasivaDTO);
    }
}