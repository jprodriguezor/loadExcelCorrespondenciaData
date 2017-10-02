package co.com.soaint.correspondencia.massiveloader.web.infrastructure.builder.generic;

import co.com.soaint.foundation.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.soaint.foundation.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.soaint.foundation.documentmanager.domain.bpm.EntradaProcesoDTO;

public class ComunicacionOficialContainerDTOBuilder {
    private ComunicacionOficialDTO comunicacionOficialDTO;
    private EntradaProcesoDTO entradaProcesoDTO;

    public ComunicacionOficialContainerDTOBuilder withComunicacionOficialContainerDTO(ComunicacionOficialDTO comunicacionOficialDTO) {
        this.comunicacionOficialDTO = comunicacionOficialDTO;
        return this;
    }

    public ComunicacionOficialContainerDTOBuilder withEntradaProcesoDTO(EntradaProcesoDTO entradaProcesoDTO) {
        this.entradaProcesoDTO = entradaProcesoDTO;
        return this;
    }

    public static ComunicacionOficialContainerDTOBuilder newBuilder() {
        return new ComunicacionOficialContainerDTOBuilder();
    }


    public ComunicacionOficialContainerDTO build() {
        return new ComunicacionOficialContainerDTO(comunicacionOficialDTO, entradaProcesoDTO);
    }
}