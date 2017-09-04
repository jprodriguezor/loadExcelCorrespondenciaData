package com.foundation.soaint.massiveloader.web.rest;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces.ComunicacionOficialManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.massiveloader.MassiveLoaderController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MassiveLoaderWebApi extends MassiveLoaderController<DocumentVO, MassiveRecordContext<ComunicacionOficialContainerDTO>> {

    private static Logger log = LogManager.getLogger(MassiveLoaderWebApi.class.getName());

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("documentToComunicacionOficialTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("excelRecordToDocumentVOTransformer")
    private Transformer voTransformer;

    public MassiveLoaderWebApi() {
        //Constructor por defecto.

    }


    //[upload service] ------------------------------
    @ResponseBody
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
}
