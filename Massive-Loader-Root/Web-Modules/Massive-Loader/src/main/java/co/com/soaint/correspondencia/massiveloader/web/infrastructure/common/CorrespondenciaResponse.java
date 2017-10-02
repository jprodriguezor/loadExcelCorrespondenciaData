package co.com.soaint.correspondencia.massiveloader.web.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Jorge on 06/09/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class CorrespondenciaResponse implements Serializable {

    private int idCargaMasiva;
    private String nombreCargaMasiva;
    private Date fechaCreacionCargaMasiva;
    private int totalRegistrosCargaMasiva;
    private String estadoCargaMasiva;
    private int totalRegistrosExitososCargaMasiva;
    private int totalRegistrosErrorCargaMasiva;
    private List<RegistroCargaMasivaDTO> registrosCargaMasiva;
}
