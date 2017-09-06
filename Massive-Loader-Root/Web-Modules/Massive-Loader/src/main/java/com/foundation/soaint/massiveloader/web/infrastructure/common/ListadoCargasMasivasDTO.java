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

    public static class ListadoCargasMasivasDTOBuilder {
        private List<CargaMasiva> cargaMasiva;


        ListadoCargasMasivasDTOBuilder() {
        }

        public ListadoCargasMasivasDTO.ListadoCargasMasivasDTOBuilder cargamasiva(List<CargaMasiva> cargaMasiva) {
            this.cargaMasiva = cargaMasiva;
            return this;
        }

        public String toString() {
            return "ListadoCargasMasivasDTO.ListadoCargasMasivasDTOBuilder(cargamasiva=" + this.cargaMasiva +")";
        }
    }

}
