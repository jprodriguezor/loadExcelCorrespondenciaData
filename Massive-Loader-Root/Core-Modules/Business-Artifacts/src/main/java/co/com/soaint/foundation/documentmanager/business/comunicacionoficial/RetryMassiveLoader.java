package co.com.soaint.foundation.documentmanager.business.comunicacionoficial;

import co.com.soaint.foundation.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;
import co.com.soaint.foundation.infrastructure.annotations.BusinessBoundary;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Jorge on 30/09/2017.
 */
@BusinessBoundary
@Log4j2
public class RetryMassiveLoader {

    @PersistenceContext
    public EntityManager em;

    @Transactional
    public void actualizarEstadoExito(int id, RegistroCargaMasivaStatus estado, String mensaje) {
        log.info ("Iniciando actualizarEstado con ESTADO = " + RegistroCargaMasivaStatus.COMPLETADO_CORRECTAMENTE);
        em.createNamedQuery ("CmRegistroCargaMasiva.updateEstadoRegistroCargaMasiva")
                .setParameter ("ESTADO", estado)
                .setParameter ("ID", Long.valueOf (id))
                .setParameter ("MENSAJE", mensaje)
                .executeUpdate ( );
    }

}
