package com.foundation.soaint.massiveloader.web.rest;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces.ComunicacionOficialManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.ListadoCargasMasivasDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.common.StatusMassiveLoaderProcessResponseDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.massiveloader.MassiveLoaderController;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.text.ParseException;


//@Controller
@Log4j2
@RestController
@RequestMapping("/massiveloaderapi")
public class MassiveLoaderWebApi extends MassiveLoaderController<DocumentVO, MassiveRecordContext<ComunicacionOficialContainerDTO>> {

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("documentToComunicacionOficialTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("excelToDocVOTransformer")
    private Transformer voTransformer;


    public MassiveLoaderWebApi() {
        //Constructor por defecto.

    }


    //[upload service] ------------------------------
    //@ResponseBody
    @RequestMapping(value = "/cargar-fichero", method = RequestMethod.POST, produces ="application/json;charset=utf-8")
    public MasiveLoaderResponse cargarFichero(@RequestParam("file") MultipartFile file,@RequestParam("codigoSede") String codigoSede, @RequestParam("codigoDependencia") String codigoDependencia) {
        log.info("Cargando el fichero");
        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
        ccBuilder.withBeanName("comunicacionOficialManager");
        ccBuilder.withMethodName("gestionarComunicacionOficial");
        ccBuilder.withServiceInterface(ComunicacionOficialManagerProxy.class);

        return processGenericLoad(file, genericExecutor, MassiveLoaderType.COMUNICACION_OFICIAL, voTransformer,
                massiveRecordTransformer, ccBuilder.build(),codigoSede,codigoDependencia);
    }

    //@ResponseBody
    @RequestMapping(value = "/estadocargamasiva", method = RequestMethod.GET, produces ="application/json;charset=utf-8")
    public StatusMassiveLoaderProcessResponseDTO obtenerEstadoCargaMasiva(){
       return obtenerDataEstadoCargaMasiva();
    }

    //@ResponseBody
    @RequestMapping(value = "/estadocargamasiva/{idCarga}", method = RequestMethod.GET, produces ="application/json;charset=utf-8")
    public StatusMassiveLoaderProcessResponseDTO obtenerEstadoCargaMasivabyID(@PathVariable int idCarga){
        return obtenerDataEstadoCargaMasivabyID(idCarga);
    }

    //@ResponseBody
    @RequestMapping(value = "/listadocargamasiva", method = RequestMethod.GET, produces ="application/json;charset=utf-8")
    public ListadoCargasMasivasDTO obtenerlistadoCargaMasiva(){
        return obtenerDataListadoCargaMasiva();
    }

    //@ResponseBody
    @RequestMapping(value = "/estadocargamasivaAA/{idCarga}", method = RequestMethod.GET, produces ="application/json;charset=utf-8")
    public String obtenerEstadoCargaMasivabyIDAA(@PathVariable int idCarga) throws ParseException, NamingException, JMSException, BusinessException, SystemException {
//         actualizarEstadoExito (idCarga);
       return obtenerDataEstadoCargaMasivabyIDAA(idCarga);

//        fillDocumentVO("DocumentVO(noRadicado=12EE2016420100000900167, fechaRadicacion=Thu May 12 22:00:00 CDT 2016, tipoComunicacion=TP-CMCOE/Comunicación Oficial Externa Recibida, tipologiaDocumental=TL-DOCOF/Oficio, noFolios=1.0, noAnexos=0.0, asunto=Tutela , requiereDigitalizar=TRUE, requiereDistribucionFisica=TRUE, personaRemite=, razonSocial=, nombre=, sedeAdministrativaRemitenteInterno=1040_VICEPRESIDENCIA DE BENEFICIOS ECONOMICOS PERIODICOS/1040, dependenciaRemitenteInterno=1040.1040_VICEPRESIDENCIA DE BENEFICIOS ECONOMICOS PERIODICOS/10401040, sedeAdministrativaDestinatario=200_VICEPRESIDENCIA DE FINANCIAMIENTO E INVERSIONES/200, dependenciaDestinatario=1040.1042_GERENCIA NACIONAL DE LA GESTION DE RED BEPS/10401042)");
    }
}
