package com.foundation.soaint.massiveloader.web.infrastructure.builder.generic;

import com.foundation.soaint.massiveloader.web.domain.bd.*;

import java.util.List;

public class ComunicacionOficialDTOBuilder {
    private CorrespondenciaDTO correspondencia;
    private List<AgenteDTO> agenteList;
    private List<PpdDocumentoDTO> ppdDocumentoList;
    private List<AnexoDTO> anexoList;
    private List<ReferidoDTO> referidoList;
    private List<DatosContactoDTO> datosContactoList;

    public ComunicacionOficialDTOBuilder withCorrespondencia(CorrespondenciaDTO correspondencia) {
        this.correspondencia = correspondencia;
        return this;
    }

    public ComunicacionOficialDTOBuilder withAgenteList(List<AgenteDTO> agenteList) {
        this.agenteList = agenteList;
        return this;
    }

    public ComunicacionOficialDTOBuilder withPpdDocumentoList(List<PpdDocumentoDTO> ppdDocumentoList) {
        this.ppdDocumentoList = ppdDocumentoList;
        return this;
    }

    public ComunicacionOficialDTOBuilder withAnexoList(List<AnexoDTO> anexoList) {
        this.anexoList = anexoList;
        return this;
    }

    public ComunicacionOficialDTOBuilder withReferidoList(List<ReferidoDTO> referidoList) {
        this.referidoList = referidoList;
        return this;
    }

    public ComunicacionOficialDTOBuilder withDatosContactoList(List<DatosContactoDTO> datosContactoList) {
        this.datosContactoList = datosContactoList;
        return this;
    }

    public static ComunicacionOficialDTOBuilder newBuilder() {
        return new ComunicacionOficialDTOBuilder();
    }

    public ComunicacionOficialDTO build() {
        return new ComunicacionOficialDTO(correspondencia, agenteList, ppdDocumentoList, anexoList, referidoList, datosContactoList);
    }
}