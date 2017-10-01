package com.foundation.soaint.massiveloader.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by g2o on 24-Jul-17.
 */
@Data
@NoArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DocumentVO {
    @NotNull
    @Size(min = 1, message = "El campo de radicado no puede estar vacio")
    String noRadicado;
    @NotNull
    @Past
    Date fechaRadicacion;
    @NotNull
    @Size(min = 1, message = "El campo tipoComunicacion no puede estar vacio")
    String tipoComunicacion;
    @NotNull
    @Size(min = 1, message = "El campo tipologiaDocumental no puede estar vacio")
    String tipologiaDocumental;
    @NotNull
    @Min(value = 0, message = "El campo noFolios no puede estar vacio")
    Double noFolios;
    @NotNull
    @Min(value = 0, message = "El campo noAnexos no puede estar vacio")
    Double noAnexos;
    @NotNull
    @Size(min = 1, message = "El campo asunto no puede estar vacio")
    String asunto;
    @NotNull
    @Size(min = 1, message = "El campo requiereDigitalizar no puede estar vacio")
    String requiereDigitalizar;
    @NotNull
    @Size(min = 1, message = "El campo requiereDistribucionFisica no puede estar vacio")
    String requiereDistribucionFisica;
//    @NotNull
//    @Size(min = 1, message = "El campo requiereDistribucionFisica no puede estar vacio")
//    String sede;
//    @NotNull
//    @Size(min = 1, message = "El campo requiereDistribucionFisica no puede estar vacio")
//    String dependencia;


    //Remitente externo
    String personaRemite;
    String razonSocial;
    String nombre;

    //Remitente interno
    String sedeAdministrativaRemitenteInterno;
    String dependenciaRemitenteInterno;

    //Destinatario
    String sedeAdministrativaDestinatario;
    String dependenciaDestinatario;

    //Sede y depenencia
    String sede;
    String dependencia;

    public DocumentVO(String noRadicado, Date fechaRadicacion, String tipoComunicacion, String tipologiaDocumental,
                      Double noFolios, Double noAnexos, String asunto, String requiereDigitalizar,
                      String requiereDistribucionFisica, String personaRemite, String razonSocial, String nombre,
                      String sedeAdministrativaRemitenteInterno, String dependenciaRemitenteInterno,
                      String sedeAdministrativaDestinatario, String dependenciaDestinatario, String sede, String dependencia) {
        this.noRadicado = noRadicado;
        this.fechaRadicacion = fechaRadicacion;
        this.tipoComunicacion = tipoComunicacion;
        this.tipologiaDocumental = tipologiaDocumental;
        this.noFolios = noFolios;
        this.noAnexos = noAnexos;
        this.asunto = asunto;
        this.requiereDigitalizar = requiereDigitalizar;
        this.requiereDistribucionFisica = requiereDistribucionFisica;
        this.personaRemite = personaRemite;
        this.razonSocial = razonSocial;
        this.nombre = nombre;
        this.sedeAdministrativaRemitenteInterno = sedeAdministrativaRemitenteInterno;
        this.dependenciaRemitenteInterno = dependenciaRemitenteInterno;
        this.sedeAdministrativaDestinatario = sedeAdministrativaDestinatario;
        this.dependenciaDestinatario = dependenciaDestinatario;
        this.sede = sede;
        this.dependencia = dependencia;
    }
}
