package com.soaint.massive.loader.web.infrastructure.massiveloader;

import com.soaint.massive.loader.main.CargaMasivaDTO;
import com.soaint.massive.loader.main.CargaMasivaResponse;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.transaction.SystemException;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/carga-masiva-web-api")
@Produces({"application/json", "application/xml"})
@Consumes({"application/json", "application/xml"})
public class MassiveLoaderWebApi {
    private static Logger logger = LogManager.getLogger(MassiveLoaderWebApi.class.getName());

    //@Autowired
    //GestionarAgente boundary;

    public MassiveLoaderWebApi() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @PUT
    @Path("/carga-masiva/cargar-fichero")
    public CargaMasivaResponse cargaFichero(CargaMasivaDTO cargaMasivaDTO)throws BusinessException, SystemException {
        logger.info("processing rest request - actualizar estado agente");
        //boundary.cargaFichero(cargaMasivaDTO);
        return new CargaMasivaResponse();
    }
}