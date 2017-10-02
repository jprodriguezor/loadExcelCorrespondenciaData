package co.com.soaint.correspondencia.massiveloader.web.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jorge on 06/09/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class CargaMasiva {

    int id;
    String nombreCarga;
}
