package com.foundation.soaint.massiveloader.web.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jorge on 06/09/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ListadoCargasMasivasDTO implements Serializable {

    List<CargaMasiva> cargaMasiva;
}
