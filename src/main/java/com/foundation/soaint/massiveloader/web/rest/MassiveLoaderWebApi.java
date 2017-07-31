package com.foundation.soaint.massiveloader.web.rest;

import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.bussiness.GestionarCargaMasiva;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import com.foundation.soaint.massiveloader.web.infrastructure.massiveloader.MassiveLoaderController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/carga-masiva-web-api")
@Produces({"application/json", "application/xml"})
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class MassiveLoaderWebApi extends MassiveLoaderController<DocumentVO, MassiveRecordContext<AdmSerie>> {

    private static Logger logger = LogManager.getLogger(MassiveLoaderWebApi.class.getName());

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("serieVOToMassiveRecordContextTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("excelRecordToDocumentVOTransformer")
    private Transformer voTransformer;

    @Autowired
    GestionarCargaMasiva gestionarCargaMasiva;

    public MassiveLoaderWebApi() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @POST
    @Path("/carga-masiva/cargar-fichero")
    public MasiveLoaderResponse cargaFichero(final MultipartFile file) {
        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
        ccBuilder.withBeanName("seriesManager");
        ccBuilder.withMethodName("createSeries");
        ccBuilder.withServiceInterface(SeriesManagerProxy.class);

        return processGenericLoad(file, genericExecutor, voTransformer, massiveRecordTransformer, ccBuilder.build());
    }
}