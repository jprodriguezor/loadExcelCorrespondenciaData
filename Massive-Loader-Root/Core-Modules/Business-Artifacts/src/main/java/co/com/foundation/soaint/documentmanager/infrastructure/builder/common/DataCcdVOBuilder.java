package co.com.foundation.soaint.documentmanager.infrastructure.builder.common;

import co.com.foundation.soaint.documentmanager.domain.DataTableCcdVO;
import co.com.foundation.soaint.infrastructure.builder.Builder;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jrodriguez on 13/12/2016.
 */
public class DataCcdVOBuilder implements Builder<DataTableCcdVO> {

    private String codigoNombreSeccion;
    private String codigoNombreSubseccion;
    private String codigoNombreSerie;
    private String codigoNombreSubserie;
    private String tiposDocumentales;

    public static DataCcdVOBuilder newBuilder(){
        return new DataCcdVOBuilder();
    }

    public DataCcdVOBuilder withCodigoNombreSeccion(String codigoNombreSeccion) {
        this.codigoNombreSeccion = codigoNombreSeccion;
        return  this;
    }

    public DataCcdVOBuilder withCodigoNombreSubseccion(String codigoNombreSubseccion) {
        this.codigoNombreSubseccion = codigoNombreSubseccion;
        return  this;
    }

    public DataCcdVOBuilder withCodigoNombreSerie(String codigoNombreSerie) {

        if(StringUtils.isEmpty(this.codigoNombreSerie)) {
            this.codigoNombreSerie = codigoNombreSerie;
        }else{
            this.codigoNombreSerie = this.codigoNombreSerie.concat( codigoNombreSerie );
        }
        return  this;
    }

    public DataCcdVOBuilder withCodigoNombreSubserie(String codigoNombreSubserie) {
        if(StringUtils.isEmpty(this.codigoNombreSubserie)) {
            this.codigoNombreSubserie = codigoNombreSubserie;
        }else{
            this.codigoNombreSubserie = this.codigoNombreSubserie.concat( codigoNombreSubserie );
        };
        return  this;
    }

    public DataCcdVOBuilder withTiposDocumentales(String tiposDocumentales) {
        if(StringUtils.isEmpty(this.tiposDocumentales)) {
            this.tiposDocumentales = tiposDocumentales;
        }else{
            this.tiposDocumentales = this.tiposDocumentales.concat( tiposDocumentales );
        }
        return  this;
    }


    @Override
    public DataTableCcdVO build() {
        return new DataTableCcdVO(codigoNombreSeccion,codigoNombreSubseccion,codigoNombreSerie,codigoNombreSubserie,tiposDocumentales);
    }
}
