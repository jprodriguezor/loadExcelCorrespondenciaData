package co.com.foundation.soaint.documentmanager.business.series;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.infrastructure.annotations.InfrastructureService;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CmRegistroCargaMasivaBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.infrastructure.proxy.ProxyBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by administrador_1 on 02/10/2016.
 */

@InfrastructureService
public class SeriesMasiveLoader extends LoaderExecutor<MassiveRecordContext<AdmSerie>> {

    // [fields] -----------------------------------

     private SeriesManagerProxy proxy;

    // [constructor] -----------------------------------

    @Autowired
    public SeriesMasiveLoader(SeriesManagerProxy boundary) {
        proxy = ProxyBuilder.getInstance().newProxy(boundary,SeriesManagerProxy.class);
    }


    // [execute service] -----------------------------------

    @Override
    public boolean processRecord( MassiveRecordContext<AdmSerie> input, CmCargaMasiva cm, CallerContext callerContext ) {

        LOGGER.info("start processing record: " + input.getRaw());
        CmRegistroCargaMasivaBuilder builder = CmRegistroCargaMasivaBuilder.newBuilder();
        boolean success = true;

        try {

            builder.withCargaMasiva(cm);
            builder.withContenido( input.getRaw() );
            proxy.createSeries( input.getDomainItem() );
            builder.withEstadoOk();

        }catch (SystemException | BusinessException e) {
            builder.withEstadoError();
            builder.withMensajes(e.getMessage());
            success = false;
        }catch (Exception e){
            builder.withEstadoError();
            builder.withMensajes(getExceptionMessage(e,"",0));
            success = false;
        }

        em.persist( builder.build() );
        LOGGER.info("end processing record: " + input.getRaw());
        return success;
    }
}
