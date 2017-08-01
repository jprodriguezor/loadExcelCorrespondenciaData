package co.com.foundation.soaint.documentmanager.web.mvc.subseries;

import co.com.foundation.soaint.documentmanager.business.subserie.interfaces.SubserieManagerProxy;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSubserie;
import co.com.foundation.soaint.documentmanager.web.domain.SubserieVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.MasiveLoaderResponse;
import co.com.foundation.soaint.documentmanager.web.infrastructure.massiveloader.MassiveLoaderController;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import static co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader.CsvRecordToSubserieVOTransformer.*;

@Controller
@Scope("request")
@RequestMapping(value = "/subseries-ml")
public class MasiveLoadSubseriesController extends MassiveLoaderController<SubserieVO, MassiveRecordContext<AdmSubserie>> {

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("subserieVOToMassiveRecordContextTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("csvRecordToSubserieVOTransformer")
    private Transformer voTransformer;

    //[constructor] ------------------------------

    public MasiveLoadSubseriesController() {
    }


    //[init] ------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "subseries/subseries-cargamasiva";
    }


    //[upload service] ------------------------------

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public MasiveLoaderResponse uploadFileHandler(@RequestParam("file") MultipartFile file)
    {

        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
        ccBuilder.withBeanName("subserieManager");
        ccBuilder.withMethodName("createSubseriesByMassiveLoader");
        ccBuilder.withServiceInterface(SubserieManagerProxy.class);

        return processGenericLoad(file, genericExecutor, MassiveLoaderType.ADM_SUBSERIES,
                voTransformer, massiveRecordTransformer, ccBuilder.build());
    }

    //[template] ------------------------------

    public String[] getHeaderTemplate() {
        return new String[]{COD_SERIE, COD_SUBSERIE, NOM_SUBSERIE, ACT_ADMINISTRATIVO, ID_MOTIVO, NOT_ALCANCE,
                FUE_BIBLIOGRAFICA, EST_SUBSERIE, CAR_TECNICA, CAR_LEGAL, CAR_ADMINISTRATIVA};
    }

}
