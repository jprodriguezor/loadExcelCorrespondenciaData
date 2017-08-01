package co.com.foundation.soaint.documentmanager.web.mvc.tipologiadocumental;


import co.com.foundation.soaint.documentmanager.business.tipologiadocumental.interfaces.TipologiasDocManagerProxy;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmTpgDoc;
import co.com.foundation.soaint.documentmanager.web.domain.TipologiaDocVO;
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

import static co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader.CsvRecordToTipologiasDocVOTransformer.*;

@Controller
@Scope("request")
@RequestMapping(value = "/tipologiasdoc-ml")
public class MasiveLoadTipologiaDocController extends MassiveLoaderController<TipologiaDocVO, MassiveRecordContext<AdmTpgDoc>> {

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("tipologiaDocVOToMassiveRecordContextTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("csvRecordToTipologiasDocVOTransformer")
    private Transformer voTransformer;

    //[constructor] ------------------------------

    public MasiveLoadTipologiaDocController() {
    }


    //[init] ------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "tipologiasdoc/tipologiadoc-cargamasiva";
    }


    //[upload service] ------------------------------

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public MasiveLoaderResponse uploadFileHandler(@RequestParam("file") MultipartFile file)
    {

        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
        ccBuilder.withBeanName("tipologiasDocManager");
        ccBuilder.withMethodName("createTpgDoc");
        ccBuilder.withServiceInterface(TipologiasDocManagerProxy.class);

        return processGenericLoad(file, genericExecutor, MassiveLoaderType.ADM_TIPOLOGIAS_DOCUMENTALES,
                voTransformer, massiveRecordTransformer, ccBuilder.build());
    }

    //[template] ------------------------------

    public String[] getHeaderTemplate() {
        return new String[]{NOM_TPG_DOC, NOT_ALCANCE, FUE_BIBLIOGRAFICA, IDE_TRA_DOCUMENTAL,IDE_SOPORTE,
                EST_TPG_DOC, CAR_TECNICA, CAR_LEGAL, CAR_ADMINISTRATIVA };

    }

}
