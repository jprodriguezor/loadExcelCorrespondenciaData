package co.com.soaint.correspondencia.massiveloader.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by g2o on 30-Jul-17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class CorrespondenciaVO {
    @NotNull
    String noRadicado;
    @NotNull
    Date fechaRadicacion;
    @NotNull
    String tipoComunicacion;
    @NotNull
    String tipologiaDocumental;

    @NotNull
    Double noFolios;
    @NotNull
    Double noAnexos;
    @NotNull
    String asunto;
    @NotNull
    Boolean requiereDigitalizar;
    @NotNull
    Boolean requiereDistribucionFisica;
}
