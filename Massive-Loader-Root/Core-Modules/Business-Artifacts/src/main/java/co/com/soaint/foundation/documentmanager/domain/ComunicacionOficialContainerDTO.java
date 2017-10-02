package co.com.soaint.foundation.documentmanager.domain;

import co.com.soaint.foundation.documentmanager.domain.bpm.EntradaProcesoDTO;
import co.com.soaint.foundation.documentmanager.domain.bd.ComunicacionOficialDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * Soaint Generic Artifact
 * Created:12-Jun-2017
 * Author: esanchez
 * Type: JAVA class Artifact
 * Purpose: DTO - Model Artifact
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

@Data
@Builder(builderMethodName = "newInstance")
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(namespace = "http://soaint.com/domain-artifacts/correspondencia/1.0.0")
public class ComunicacionOficialContainerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ComunicacionOficialDTO comunicacionOficialDTO;
    private EntradaProcesoDTO entradaProcesoDTO;

    @Override
    public String toString() {
        return "ComunicacionOficialContainerDTO{"
                + "comunicacionOficialDTO=" + comunicacionOficialDTO.toString()
                + ", entradaProcesoDTO='" + entradaProcesoDTO.toString() + '\''
                + '}';
    }
}
