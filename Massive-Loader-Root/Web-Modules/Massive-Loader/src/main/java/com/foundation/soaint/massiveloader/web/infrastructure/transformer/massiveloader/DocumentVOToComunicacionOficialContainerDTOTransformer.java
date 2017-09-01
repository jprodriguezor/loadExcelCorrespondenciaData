package com.foundation.soaint.massiveloader.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.foundation.soaint.documentmanager.domain.bd.AgenteDTO;
import co.com.foundation.soaint.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.foundation.soaint.documentmanager.domain.bd.CorrespondenciaDTO;
import co.com.foundation.soaint.documentmanager.domain.bd.PpdDocumentoDTO;
import co.com.foundation.soaint.documentmanager.domain.bpm.EntradaProcesoDTO;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.ComunicacionOficialContainerDTOBuilder;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.ComunicacionOficialDTOBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by administrador_1 on 06/10/2016.
 */

@Component
public class DocumentVOToComunicacionOficialContainerDTOTransformer implements Transformer<DocumentVO, MassiveRecordContext<ComunicacionOficialContainerDTO>> {


    @Override
    public MassiveRecordContext<ComunicacionOficialContainerDTO> transform(DocumentVO documentVO) {
        ComunicacionOficialDTO comunicacionOficialDTO = builComunicacionOficialDTO(documentVO);
        EntradaProcesoDTO entradaProcesoDTO = buildEntradaProcesoDTO(documentVO);
        ComunicacionOficialContainerDTOBuilder builder = ComunicacionOficialContainerDTOBuilder.newBuilder()
                .withComunicacionOficialContainerDTO(comunicacionOficialDTO)
                .withEntradaProcesoDTO(entradaProcesoDTO);
        MassiveRecordContext<ComunicacionOficialContainerDTO>  recordContext = new MassiveRecordContext<>(documentVO.toString(), builder.build());
        return recordContext;
    }

    private EntradaProcesoDTO buildEntradaProcesoDTO(DocumentVO documentVO) {
        EntradaProcesoDTO entradaProcesoDTO = new EntradaProcesoDTO();
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("nroRadicado",documentVO.getNoRadicado());
        parametros.put("requiereDigitalizacion",documentVO.getRequiereDigitalizar());
        parametros.put("requiereDistribucion",documentVO.getRequiereDistribucionFisica());
        entradaProcesoDTO.setParametros(parametros);
        return entradaProcesoDTO;
    }

    private ComunicacionOficialDTO builComunicacionOficialDTO(DocumentVO documentVO) {
        AgenteDTO agenteDTO = new AgenteDTO();
        String sedeAdministrativaRemitenteInterno = documentVO.getSedeAdministrativaRemitenteInterno();
        if (sedeAdministrativaRemitenteInterno != null && sedeAdministrativaRemitenteInterno.isEmpty()) {
            agenteDTO.setCodDependencia(documentVO.getDependenciaDestinatario());
            agenteDTO.setCodSede(sedeAdministrativaRemitenteInterno);
        } else {
            agenteDTO.setRazonSocial(documentVO.getRazonSocial());
            agenteDTO.setNombre(documentVO.getNombre());
            agenteDTO.setCodTipoRemite(documentVO.getPersonaRemite());
        }

        CorrespondenciaDTO correspondenciaDTO = new CorrespondenciaDTO();
        correspondenciaDTO.setNroRadicado(documentVO.getNoRadicado());
        correspondenciaDTO.setFecRadicado(documentVO.getFechaRadicacion());
        correspondenciaDTO.setCodTipoCmc(documentVO.getTipoComunicacion());
        correspondenciaDTO.setCodTipoDoc(documentVO.getTipologiaDocumental());
        correspondenciaDTO.setReqDigita(documentVO.getRequiereDigitalizar());
        correspondenciaDTO.setReqDistFisica(documentVO.getRequiereDistribucionFisica());


        PpdDocumentoDTO ppdDocumentoDTO = new PpdDocumentoDTO();
        ppdDocumentoDTO.setAsunto(documentVO.getAsunto());
        ppdDocumentoDTO.setNroAnexos(documentVO.getNoAnexos().longValue());
        ppdDocumentoDTO.setNroFolios(documentVO.getNoFolios().longValue());


        return ComunicacionOficialDTOBuilder.newBuilder()
                .withAgenteList(Arrays.asList(agenteDTO))
                .withCorrespondencia(correspondenciaDTO)
                .withPpdDocumentoList(Arrays.asList(ppdDocumentoDTO))
                .build();
    }

}
