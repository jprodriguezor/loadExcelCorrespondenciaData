package co.com.soaint.correspondencia.massiveloader.web.infrastructure.transformer.massiveloader;

import co.com.soaint.correspondencia.massiveloader.web.domain.DocumentVO;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.builder.generic.ComunicacionOficialContainerDTOBuilder;
import co.com.soaint.foundation.documentmanager.domain.ComunicacionOficialContainerDTO;
import co.com.soaint.foundation.documentmanager.domain.bd.AgenteDTO;
import co.com.soaint.foundation.documentmanager.domain.bd.ComunicacionOficialDTO;
import co.com.soaint.foundation.documentmanager.domain.bd.CorrespondenciaDTO;
import co.com.soaint.foundation.documentmanager.domain.bd.PpdDocumentoDTO;
import co.com.soaint.foundation.documentmanager.domain.bpm.EntradaProcesoDTO;
import co.com.soaint.foundation.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.soaint.correspondencia.massiveloader.web.infrastructure.builder.generic.ComunicacionOficialDTOBuilder;
import co.com.soaint.foundation.infrastructure.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Jorge on 01/09/2017.
 */
@Component
public class DocToComOficTransf implements Transformer<DocumentVO, MassiveRecordContext<ComunicacionOficialContainerDTO>> {
    private static final String BEFORE = "before";
    private static final String AFTER = "after";

    public DocToComOficTransf() {
    }

    public MassiveRecordContext<ComunicacionOficialContainerDTO> transform(DocumentVO documentVO) {
        ComunicacionOficialDTO comunicacionOficialDTO = this.builComunicacionOficialDTO(documentVO);
        EntradaProcesoDTO entradaProcesoDTO = this.buildEntradaProcesoDTO(documentVO);
        ComunicacionOficialContainerDTO builder = ComunicacionOficialContainerDTOBuilder.newBuilder().withComunicacionOficialContainerDTO(comunicacionOficialDTO).withEntradaProcesoDTO(entradaProcesoDTO).build();
        return new MassiveRecordContext(documentVO.toString(), builder);
    }

    private EntradaProcesoDTO buildEntradaProcesoDTO(DocumentVO documentVO) {
        EntradaProcesoDTO entradaProcesoDTO = new EntradaProcesoDTO();
        HashMap parametros = new HashMap();
        parametros.put("numeroRadicado", documentVO.getNoRadicado());
        if ("TRUE".equalsIgnoreCase(documentVO.getRequiereDigitalizar())) {
            parametros.put("requiereDigitalizacion", "1");
        } else {
            parametros.put("requiereDigitalizacion", "0");
        }

        if ("TRUE".equalsIgnoreCase(documentVO.getRequiereDistribucionFisica())) {
            parametros.put("requiereDistribucion", "1");
        } else {
            parametros.put("requiereDistribucion", "0");
        }
        entradaProcesoDTO.setParametros(parametros);
        return entradaProcesoDTO;
    }

    private String getValueStringCode(String data, String position) {
        String[] split = data.split("/");
        return BEFORE.equalsIgnoreCase(position) ? split[0] : split[1];
    }

    private ComunicacionOficialDTO builComunicacionOficialDTO(DocumentVO documentVO) {
        ArrayList<AgenteDTO> listadoAgentes = new ArrayList();
        AgenteDTO agenteDTORemitente = new AgenteDTO();
        //TODO: externalizar
        agenteDTORemitente.setCodTipAgent("TP-AGEE");
        String sedeAdministrativaRemitenteInterno = documentVO.getSedeAdministrativaRemitenteInterno();
        if (sedeAdministrativaRemitenteInterno != null && !sedeAdministrativaRemitenteInterno.isEmpty()) {
            //Remitente interno
            agenteDTORemitente.setCodDependencia(this.getValueStringCode(documentVO.getDependenciaRemitenteInterno(), AFTER));
            agenteDTORemitente.setCodSede(this.getValueStringCode(documentVO.getSedeAdministrativaRemitenteInterno(), AFTER));
            //TODO: externalizar
            agenteDTORemitente.setCodTipoRemite("TP-REMI");
        } else {
            //Remitente externo
            agenteDTORemitente.setRazonSocial(this.getValueStringCode(documentVO.getRazonSocial(), BEFORE));
            agenteDTORemitente.setNombre(documentVO.getNombre());
            agenteDTORemitente.setCodTipoPers(this.getValueStringCode(documentVO.getPersonaRemite(), BEFORE));
            agenteDTORemitente.setCodDependencia("");
            agenteDTORemitente.setCodSede("");
            //TODO: externalizar
            agenteDTORemitente.setCodTipoRemite("TP-REME");

        }

        listadoAgentes.add(agenteDTORemitente);
        AgenteDTO agenteDTODestinatario = new AgenteDTO();
        //TODO: externalizar
        agenteDTODestinatario.setCodTipAgent("TP-AGEI");
        agenteDTODestinatario.setCodSede(this.getValueStringCode(documentVO.getSedeAdministrativaDestinatario(), AFTER));
        agenteDTODestinatario.setCodDependencia(this.getValueStringCode(documentVO.getDependenciaDestinatario(), AFTER));
        //TODO: externalizar
        agenteDTODestinatario.setIndOriginal("TP-DESP");
        listadoAgentes.add(agenteDTODestinatario);
        CorrespondenciaDTO correspondenciaDTO = new CorrespondenciaDTO();
        correspondenciaDTO.setDescripcion("Procesamiento Masivo de Datos");
        correspondenciaDTO.setNroRadicado(documentVO.getNoRadicado());
        correspondenciaDTO.setFecRadicado(documentVO.getFechaRadicacion());
        correspondenciaDTO.setCodTipoCmc(this.getValueStringCode(documentVO.getTipoComunicacion(), BEFORE));
        correspondenciaDTO.setCodTipoDoc(this.getValueStringCode(documentVO.getTipologiaDocumental(), BEFORE));

        //TODO: aqui se debe invocar a la regla para llenar los 3 campos debajo
        correspondenciaDTO.setTiempoRespuesta("27");
        correspondenciaDTO.setCodUnidadTiempo("UNID-TIH");
        correspondenciaDTO.setInicioConteo("DHR");

        if ("TRUE".equalsIgnoreCase(documentVO.getRequiereDigitalizar())) {
            correspondenciaDTO.setReqDigita("1");
        } else {
            correspondenciaDTO.setReqDigita("0");
        }

        if ("TRUE".equalsIgnoreCase(documentVO.getRequiereDistribucionFisica())) {
            correspondenciaDTO.setReqDistFisica("1");
        } else {
            correspondenciaDTO.setReqDistFisica("0");
        }

        PpdDocumentoDTO ppdDocumentoDTO = new PpdDocumentoDTO();
        ppdDocumentoDTO.setAsunto(documentVO.getAsunto());
        ppdDocumentoDTO.setNroAnexos(Long.valueOf(documentVO.getNoAnexos().longValue()));
        ppdDocumentoDTO.setNroFolios(Long.valueOf(documentVO.getNoFolios().longValue()));
        ppdDocumentoDTO.setCodTipoDoc(this.getValueStringCode(documentVO.getTipologiaDocumental(), BEFORE));
        ppdDocumentoDTO.setFecDocumento(documentVO.getFechaRadicacion());
        return ComunicacionOficialDTOBuilder.newBuilder().withAgenteList(listadoAgentes).withCorrespondencia(correspondenciaDTO).withPpdDocumentoList(Arrays.asList(new PpdDocumentoDTO[]{ppdDocumentoDTO})).build();
    }
}
