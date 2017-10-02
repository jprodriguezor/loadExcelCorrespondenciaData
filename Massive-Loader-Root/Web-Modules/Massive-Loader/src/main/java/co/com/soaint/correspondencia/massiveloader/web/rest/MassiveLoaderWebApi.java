package co.com.soaint.correspondencia.massiveloader.web.rest;

import co.com.soaint.correspondencia.massiveloader.web.domain.DocumentVO;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.massiveloader.MassiveLoaderController;
import co.com.soaint.foundation.documentmanager.business.comunicacionoficial.interfaces.ComOficialMgtProxy;
import co.com.soaint.foundation.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.soaint.foundation.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.soaint.foundation.infrastructure.transformer.Transformer;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.common.ListadoCargasMasivasDTO;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.common.StatusMassiveLoaderProcessResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@Log4j2
public class MassiveLoaderWebApi extends MassiveLoaderController<DocumentVO, MassiveRecordContext <ComunicacionOficialContainerDTO>> {

    @Autowired
    @Qualifier("genericLoaderExecutor")
    private LoaderExecutor genericExecutor;

    @Autowired
    @Qualifier("docToComOficTransf")
    private Transformer massiveRecordTransformer;

    @Autowired
    @Qualifier("excelToDocVOTransformer")
    private Transformer voTransformer;

    public MassiveLoaderWebApi() {
        //Constructor por defecto.

    }


    //[upload service] ------------------------------
    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/cargar-fichero", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public MasiveLoaderResponse cargarFichero(@RequestParam("file") MultipartFile file, @RequestParam("codigoSede") String codigoSede, @RequestParam("codigoDependencia") String codigoDependencia) {
        log.info ("Cargando el fichero");
        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder ( );
        ccBuilder.withBeanName ("comunicacionOficialManager");
        ccBuilder.withMethodName ("gestionarComunicacionOficial");
        ccBuilder.withServiceInterface (ComOficialMgtProxy.class);

        return processGenericLoad (file, genericExecutor, MassiveLoaderType.COMUNICACION_OFICIAL, voTransformer,
                massiveRecordTransformer, ccBuilder.build ( ), codigoSede, codigoDependencia);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/estadocargamasiva", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public StatusMassiveLoaderProcessResponseDTO obtenerEstadoCargaMasiva() {
        return obtenerDataEstadoCargaMasiva ( );
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/estadocargamasiva/{idCarga}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public StatusMassiveLoaderProcessResponseDTO obtenerEstadoCargaMasivabyID(@PathVariable int idCarga) {
        return obtenerDataEstadoCargaMasivabyID (idCarga);
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/listadocargamasiva", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ListadoCargasMasivasDTO obtenerlistadoCargaMasiva() {
        return obtenerDataListadoCargaMasiva ( );

    }


}
