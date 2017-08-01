package co.com.foundation.soaint.documentmanager.infrastructure.builder.common;

import co.com.foundation.soaint.documentmanager.domain.DataTrdVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class DataTrdVOBuilder implements Builder<DataTrdVO>{

    private String codigo;
    private String instrumentos;
    private Long archivoGestion;
    private Long archivoCentral;
    private int disposicionFinal;
    private String procedimientos;

    private DataTrdVOBuilder(){
    }

    public DataTrdVOBuilder withCodigo(String codigo) {
        if(StringUtils.isEmpty(this.codigo)) {
            this.codigo = codigo;
        }else{
            this.codigo = this.codigo.concat( codigo );
        }
        return this;
    }

    public DataTrdVOBuilder withInstrumentos(String instrumentos) {
        if(StringUtils.isEmpty(this.instrumentos)) {
            this.instrumentos = instrumentos;
        }else{
            this.instrumentos = this.instrumentos.concat( instrumentos );
        }
        return this;
    }

    public DataTrdVOBuilder withArchivoGestion(Long archivoGestion) {
        this.archivoGestion = archivoGestion;
        return this;
    }

    public DataTrdVOBuilder withArchivoCentral(Long archivoCentral) {
        this.archivoCentral = archivoCentral;
        return this;
    }

    public DataTrdVOBuilder withDisposicionFinal(int disposicionFinal) {
        this.disposicionFinal = disposicionFinal;
        return this;
    }

    public DataTrdVOBuilder withProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;
        return this;
    }

    public static DataTrdVOBuilder newBuilder(){
        return new DataTrdVOBuilder();
    }

    @Override
    public DataTrdVO build() {
        return new DataTrdVO(codigo,instrumentos,archivoGestion,archivoCentral,disposicionFinal,procedimientos);
    }
}
