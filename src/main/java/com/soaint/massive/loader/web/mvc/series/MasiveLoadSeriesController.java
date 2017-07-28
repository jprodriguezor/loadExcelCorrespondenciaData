package com.soaint.massive.loader.web.mvc.series;

import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerie;
import co.com.foundation.soaint.documentmanager.business.series.interfaces.SeriesManagerProxy;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import com.soaint.massive.loader.web.infrastructure.massiveloader.MassiveLoaderController;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import com.soaint.massive.loader.web.infrastructure.common.MasiveLoaderResponse;
import com.soaint.massive.loader.web.domain.SerieVO;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static com.soaint.massive.loader.web.infrastructure.transformer.massiveloader.CsvRecordToSerieVOTransformer.*;

@Controller
@Scope("request")
@RequestMapping(value = "/series-ml")
public class MasiveLoadSeriesController extends MassiveLoaderController<SerieVO, MassiveRecordContext<AdmSerie>> {

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("serieVOToMassiveRecordContextTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("csvRecordToSerieVOTransformer")
    private Transformer voTransformer;

    //[constructor] ------------------------------

    public MasiveLoadSeriesController() {
    }


    //[init] ------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "series/series-cargamasiva";
    }


    //[upload service] ------------------------------

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public MasiveLoaderResponse uploadFileHandler(@RequestParam("file") MultipartFile file)
    {

        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
        ccBuilder.withBeanName("seriesManager");
        ccBuilder.withMethodName("createSeries");
        ccBuilder.withServiceInterface(SeriesManagerProxy.class);

        return processGenericLoad(file, genericExecutor, MassiveLoaderType.ADM_SERIES,
                voTransformer, massiveRecordTransformer, ccBuilder.build());
    }


    //[template] ------------------------------

    public String[] getHeaderTemplate() {

        return new String[]{COD_SERIE, NOMB_SERIE, ACT_ADMINISTRATIVO, ID_MOTIVO, NOT_ALCANCE, FUE_BIBLIOGRAFICA, EST_SERIE,
                CAR_TECNICA, CAR_LEGAL, CAR_ADMINISTRATIVA};
    }


}
