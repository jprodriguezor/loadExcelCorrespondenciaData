package com.foundation.soaint.massiveloader.web.rest;

import co.com.foundation.soaint.documentmanager.business.comunicacionoficial.interfaces.ComunicacionOficialManagerProxy;
import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.massiveloader.MassiveLoaderController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Controller
@Scope("request")
@RequestMapping(value = "/carga-masiva-web-api")
public class MassiveLoaderWebApi extends MassiveLoaderController<DocumentVO, MassiveRecordContext<ComunicacionOficialDTO>> {

    private static Logger logger = LogManager.getLogger(MassiveLoaderWebApi.class.getName());

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("documentVOToComunicacionOficialDTOTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("excelRecordToDocumentVOTransformer")
    private Transformer voTransformer;

    public MassiveLoaderWebApi() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @ResponseBody
    @RequestMapping(value = "/carga-fichero", method = RequestMethod.POST)
    public MasiveLoaderResponse cargaFichero(final MultipartFile file) {
        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
        ccBuilder.withBeanName("comunicacionOficialManager");
        ccBuilder.withMethodName("gestionarComunicacionOficial");
        ccBuilder.withServiceInterface(ComunicacionOficialManagerProxy.class);

        return processGenericLoad(file, genericExecutor, MassiveLoaderType.COMUNICACION_OFICIAL, voTransformer,
                massiveRecordTransformer, ccBuilder.build());
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        logger.warn("donde estoy");
        // Return some cliched textual content
        return "Hello World";
    }
}