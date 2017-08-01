package com.foundation.soaint.massiveloader.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by g2o on 24-Jul-17.
 */
@Data
@NoArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DocumentVO {
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
    String requiereDigitalizar;
    @NotNull
    String requiereDistribucionFisica;

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

    public DocumentVO(String noRadicado, Date fechaRadicacion, String tipoComunicacion, String tipologiaDocumental,
                      Double noFolios, Double noAnexos, String asunto, String requiereDigitalizar,
                      String requiereDistribucionFisica, String personaRemite, String razonSocial, String nombre,
                      String sedeAdministrativaRemitenteInterno, String dependenciaRemitenteInterno,
                      String sedeAdministrativaDestinatario, String dependenciaDestinatario) {
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
    }
}
