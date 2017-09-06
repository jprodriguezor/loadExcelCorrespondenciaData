package com.foundation.soaint.massiveloader.web.infrastructure.common;

import co.com.foundation.soaint.documentmanager.persistence.entity.constants.RegistroCargaMasivaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Jorge on 06/09/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class RegistroCargaMasivaDTO implements Serializable {

    private int id;

    private String contenido;

    private String mensajes;

    private RegistroCargaMasivaStatus estado;
}
