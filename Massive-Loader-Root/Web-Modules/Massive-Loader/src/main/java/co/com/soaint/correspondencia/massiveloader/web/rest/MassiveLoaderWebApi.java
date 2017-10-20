package co.com.soaint.correspondencia.massiveloader.web.rest;

import co.com.soaint.correspondencia.massiveloader.web.domain.DocumentVO;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.common.ListadoCargasMasivasDTO;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.common.MasiveLoaderResponse;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.common.StatusMassiveLoaderProcessResponseDTO;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.massiveloader.MassiveLoaderController;
import co.com.soaint.foundation.documentmanager.business.comunicacionoficial.interfaces.ComOficialMgtProxy;
import co.com.soaint.foundation.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.soaint.foundation.documentmanager.infrastructure.builder.massiveloader.CallerContextBuilder;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.MassiveLoaderType;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.executor.LoaderExecutor;
import co.com.soaint.foundation.infrastructure.transformer.Transformer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/massiveloaderapi")
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


    /**
     * Servicio para la carga masiva del excel
     * @param file contenido del excel
     * @param codigoSede codigo de la sede que se recibe desde UI
     * @param codigoDependencia codigo de la dependencia que se recibe desde UI
     * @return Retorna una respuesta que contiene el estado de la operacion
     */
    @CrossOrigin
    @RequestMapping(value = "/cargar-fichero", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public MasiveLoaderResponse cargarFichero(@RequestPart("file") MultipartFile file, @RequestParam("codigoSede") String codigoSede, @RequestParam("codigoDependencia") String codigoDependencia) {
        log.info ("Cargando el fichero");
        CallerContextBuilder ccBuilder = CallerContextBuilder.newBuilder ( );
        ccBuilder.withBeanName ("comunicacionOficialManager");
        ccBuilder.withMethodName ("gestionarComunicacionOficial");
        ccBuilder.withServiceInterface (ComOficialMgtProxy.class);

        return processGenericLoad (file, genericExecutor, MassiveLoaderType.COMUNICACION_OFICIAL, voTransformer,
                massiveRecordTransformer, ccBuilder.build ( ), codigoSede, codigoDependencia);
    }

    /**
     * Operacion para obtener el estado de la ultima carga masiva
     * @return retorna un mensaje con el estado de la ultima carga masiva
     */
    @CrossOrigin
    @RequestMapping(value = "/estadocargamasiva", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public StatusMassiveLoaderProcessResponseDTO obtenerEstadoCargaMasiva() {
        return obtenerDataEstadoCargaMasiva ( );
    }

    /**
     * Operacion para obtener el estado de una operacion de carga masiva
     * @param idCarga id de la carga masiva de la que se quiere ver su estado y contenido
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/estadocargamasiva/{idCarga}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public StatusMassiveLoaderProcessResponseDTO obtenerEstadoCargaMasivabyID(@PathVariable int idCarga) {
        return obtenerDataEstadoCargaMasivabyID (idCarga);
    }

    /**
     * Operacion que lista las cargas masivas
     * @return retorna un objeto con el listado de las cargas masivas realizadas
     */
    @CrossOrigin
    @RequestMapping(value = "/listadocargamasiva", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ListadoCargasMasivasDTO obtenerlistadoCargaMasiva() {
        return obtenerDataListadoCargaMasiva ( );

    }


}
