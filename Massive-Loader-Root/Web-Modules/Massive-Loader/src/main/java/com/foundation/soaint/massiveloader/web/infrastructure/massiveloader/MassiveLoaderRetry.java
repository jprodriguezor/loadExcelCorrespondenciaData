package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.documentmanager.persistence.entity.CmRegistroCargaMasiva;
import co.com.foundation.soaint.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;
import com.foundation.soaint.massiveloader.web.infrastructure.common.RegistroCargaMasivaDTO;
import com.foundation.soaint.massiveloader.web.infrastructure.common.StatusMassiveLoaderProcessResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.jgroups.conf.ProtocolConfiguration.log;

public class MassiveLoaderRetry {


    static final String NOT_FOUND="not_found";
    static final String FAILED_TO_CONNECT="Failed to connect to any server. Servers tried:";
    static final String JMS_MESSAGE="jms fail";

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected MassiveLoaderController massiveLoaderController;

    public void retryCall(){
        log.info("Se inicia el procesamiento de los mensajes con errores");

        massiveLoaderController.obtenerDataEstadoCargaMasivaCOmpletadoConErrores();
        log.info("Se obtienen los registros de cargas masiva");
//        List<RegistroCargaMasivaDTO> registroCargaMasiva = massiveLoaderController.obtenerDataEstadoCargaMasivaCOmpletadoConErrores().getCorrespondencia ().getRegistrosCargaMasiva ();
//
//        //TODO en caso de que se relaice correctamente
//        for (RegistroCargaMasivaDTO registro: registroCargaMasiva
//             ) {
//            String mensaje= registro.getMensajes ();
//            int id=registro.getId ();
//            log.info("Se obtienen los registros de cargas masiva con estado:" + mensaje);
//
//            if (mensaje.contains (FAILED_TO_CONNECT)){
//            //invocar servicio para carga masiva de nuevo
//                log.info("Se obtienen los registros de cargas masiva con estado:" + mensaje);
//            }
//            else if(mensaje.equals (NOT_FOUND)){
//                //invocar el servicio de nuevo
//            }
//            else if (mensaje.contains (JMS_MESSAGE)){
//                //encolar nuevamente
//            }
//        }


    }

    public void enExito(Long id) {

        CmRegistroCargaMasiva cmRegistroCargaMasiva = new CmRegistroCargaMasiva ( );
        cmRegistroCargaMasiva.setId (id);

        CmRegistroCargaMasiva cmRegistroCargaMass = em.find (CmRegistroCargaMasiva.class, cmRegistroCargaMasiva.getId ( ));
        em.getTransaction ( ).begin ( );
        cmRegistroCargaMass.setEstado (RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE);
        em.getTransaction ( ).commit ( );
    }

}
