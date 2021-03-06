package co.com.soaint.foundation.documentmanager.domain.bd;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * Soaint Generic Artifact
 * Created:2-Jun-2017
 * Author: esanchez
 * Type: JAVA class Artifact
 * Purpose: DTO - Model Artifact
 * ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

@Data
@Builder(builderMethodName = "newInstance")
@XmlRootElement(namespace = "http://soaint.com/domain-artifacts/cor-referido/1.0.0")
public class ReferidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigInteger ideReferido;
    private String nroRadRef;

    public ReferidoDTO(){super();}

    public ReferidoDTO(BigInteger ideReferido, String nroRadRef){
        this.ideReferido = ideReferido;
        this.nroRadRef = nroRadRef;
    }
}
