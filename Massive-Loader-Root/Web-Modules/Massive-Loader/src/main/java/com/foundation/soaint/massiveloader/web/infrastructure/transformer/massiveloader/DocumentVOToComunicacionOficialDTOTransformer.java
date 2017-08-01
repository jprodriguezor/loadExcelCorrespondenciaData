package com.foundation.soaint.massiveloader.web.infrastructure.transformer.massiveloader;

import co.com.foundation.soaint.documentmanager.domain.bd.AgenteDTO;
import co.com.foundation.soaint.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.foundation.soaint.documentmanager.domain.bd.CorrespondenciaDTO;
import co.com.foundation.soaint.documentmanager.domain.bd.PpdDocumentoDTO;
import co.com.foundation.soaint.infrastructure.transformer.Transformer;
import com.foundation.soaint.massiveloader.web.domain.DocumentVO;
import com.foundation.soaint.massiveloader.web.infrastructure.builder.generic.ComunicacionOficialDTOBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by administrador_1 on 06/10/2016.
 */

@Component
public class DocumentVOToComunicacionOficialDTOTransformer implements Transformer<DocumentVO, ComunicacionOficialDTO> {


    /*
    this.valueRemitente = this.datosRemitente.form.value;
    this.valueDestinatario = this.datosDestinatario.form.value;
    this.valueGeneral = this.datosGenerales.form.value;
    const agentesList = [];
    const isRemitenteInterno = this.valueGeneral.tipoComunicacion.codigo === COMUNICACION_INTERNA;

    if (isRemitenteInterno) {
      agentesList.push(this.getTipoAgenteRemitenteInterno());
    } else {
      agentesList.push(this.getTipoAgenteRemitenteExterno());
    }

    agentesList.push(...this.getAgentesDestinatario));
    this.radicacion = {
      correspondencia: this.getCorrespondencia(),
      agenteList: agentesList,
      ppdDocumentoList: [this.getDocumento()],
      anexoList: this.getListaAnexos(),
      referidoList: this.getListaReferidos(),
      datosContactoList: this.getDatosContactos()
    };
    });
    */

    @Override
    public ComunicacionOficialDTO transform(DocumentVO documentVO) {

        AgenteDTO agenteDTO = new AgenteDTO();
        if (documentVO.getSedeAdministrativaRemitenteInterno().isEmpty()) {
            agenteDTO.setCodDependencia(documentVO.getDependenciaDestinatario());
            agenteDTO.setCodSede(documentVO.getSedeAdministrativaRemitenteInterno());
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
        ppdDocumentoDTO.setNroAnexos(Long.valueOf(documentVO.getNoAnexos().toString()));
        ppdDocumentoDTO.setNroFolios(Long.valueOf(documentVO.getNoFolios().toString()));


        return ComunicacionOficialDTOBuilder.newBuilder()
                .withAgenteList(Arrays.asList(agenteDTO))
                .withCorrespondencia(correspondenciaDTO)
                .withPpdDocumentoList(Arrays.asList(ppdDocumentoDTO))
                .build();
    }

}
