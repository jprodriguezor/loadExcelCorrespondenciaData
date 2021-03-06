package co.com.soaint.foundation.documentmanager.domain.bpm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

/**
 * Created by Arce on 6/8/2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
@XmlRootElement(namespace = "http://soaint.com/domain-artifacts/entradaproceso/1.0.0")
public class EntradaProcesoDTO {
    private String idDespliegue;
    private String idProceso;
    private Long instanciaProceso;
    private Long idTarea;
    private String usuario;
    private String pass;
    private Map<String, Object> parametros;
    private List<EstadosEnum> estados;


}
