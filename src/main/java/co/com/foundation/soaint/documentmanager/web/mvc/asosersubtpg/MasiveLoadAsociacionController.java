package co.com.foundation.soaint.documentmanager.web.mvc.asosersubtpg;


import co.com.foundation.soaint.documentmanager.business.asosersubtpg.interfaces.AsoSerSubTpglManagerProxy;
import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmSerSubserTpg;
import co.com.foundation.soaint.documentmanager.web.domain.AsociacionVO;
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
import static co.com.foundation.soaint.documentmanager.web.infrastructure.transformer.massiveloader.CsvRecordToAsociacionVOTransformer.*;

/**
 * Created by sarias on 13/10/2016.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/asociacion-ml")
public class MasiveLoadAsociacionController extends MassiveLoaderController<AsociacionVO, MassiveRecordContext<AdmSerSubserTpg>> {

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("asociacionVOToMassiveRecordContextTransformer")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("csvRecordToAsociacionVOTransformer")
    private Transformer voTransformer;

    public MasiveLoadAsociacionController(){

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "asoctiposdocumentales/asociacion-cargamasiva";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public MasiveLoaderResponse uploadFileHandler(@RequestParam("file") MultipartFile file)
    {

        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder();
        ccBuilder.withBeanName("asoSerSubTpgManager");
        ccBuilder.withMethodName("createAsoc");
        ccBuilder.withServiceInterface(AsoSerSubTpglManagerProxy.class);

        return processGenericLoad(file, genericExecutor, MassiveLoaderType.ADM_SER_SUBSER_TPG,
                voTransformer, massiveRecordTransformer, ccBuilder.build());
    }


    @Override
    public String[] getHeaderTemplate() {
        return new String[] {
            IDE_SERIE, IDE_SUBSERIE,IDE_TPG_DOC
        } ;

    }
}


