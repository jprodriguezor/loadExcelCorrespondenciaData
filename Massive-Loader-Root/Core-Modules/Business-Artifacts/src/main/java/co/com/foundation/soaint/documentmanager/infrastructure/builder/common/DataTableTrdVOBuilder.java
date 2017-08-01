package co.com.foundation.soaint.documentmanager.infrastructure.builder.common;

import co.com.foundation.soaint.documentmanager.domain.DataTableTrdVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jrodriguez on 3/12/2016.
 */
public class DataTableTrdVOBuilder implements Builder<DataTableTrdVO> {

    private String codigo;
    private String instrumentos;
    private Long archivoGestion;
    private Long archivoCentral;
    private String ct;
    private String e;
    private String m;
    private String s;
    private String d;
    private String procedimientos;

    private DataTableTrdVOBuilder() {
    }

    public DataTableTrdVOBuilder withCodigo(String codigo) {
        if(StringUtils.isEmpty(this.codigo)) {
            this.codigo = codigo;
        }else{
            this.codigo = this.codigo.concat( codigo );
        }
        return this;
    }

    public DataTableTrdVOBuilder withInstrumentos(String instrumentos) {
        if(StringUtils.isEmpty(this.instrumentos)) {
            this.instrumentos = instrumentos;
        }else{
            this.instrumentos = this.instrumentos.concat( instrumentos );
        }
        return this;
    }

    public DataTableTrdVOBuilder withArchivoGestion(Long archivoGestion) {
        this.archivoGestion = archivoGestion;
        return this;
    }

    public DataTableTrdVOBuilder withArchivoCentral(Long archivoCentral) {
        this.archivoCentral = archivoCentral;
        return this;
    }

    public DataTableTrdVOBuilder withCt(String ct) {
        this.ct = ct;
        return this;
    }

    public DataTableTrdVOBuilder withM(String m) {
        this.m = m;
        return this;
    }

    public DataTableTrdVOBuilder withE(String e) {
        this.e = e;
        return this;
    }

    public DataTableTrdVOBuilder withS(String s) {
        this.s = s;
        return this;
    }

    public DataTableTrdVOBuilder withD(String d) {
        this.d = d;
        return this;
    }

    public DataTableTrdVOBuilder withProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;
        return this;
    }

    public static DataTableTrdVOBuilder newBuilder(){
        return new DataTableTrdVOBuilder();
    }

    @Override
    public DataTableTrdVO  build() {
        return new DataTableTrdVO(codigo,instrumentos,archivoGestion,archivoCentral,ct,e,m,s,d,procedimientos);
    }
}
