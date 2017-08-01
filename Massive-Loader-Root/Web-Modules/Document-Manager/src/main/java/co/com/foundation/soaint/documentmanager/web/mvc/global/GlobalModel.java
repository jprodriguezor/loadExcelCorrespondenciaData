package co.com.foundation.soaint.documentmanager.web.mvc.global;

import co.com.foundation.soaint.documentmanager.web.infrastructure.common.SelectItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodriguez on 20/09/2016.
 */
@Component
@Scope("session")
public class GlobalModel implements Serializable {

    private List<SelectItem> motivosCreacionList;
    private List<SelectItem> seriesList;
    private List<SelectItem> subSeriesList;
    private List<SelectItem> soportesList;
    private List<SelectItem> tradicionDocList;
    private List<SelectItem> tipologiasDocList;
    private List<SelectItem> unidadAdministrativoList;
    private List<SelectItem> oficinaProductoraList;
    private List<SelectItem> disposicionFinal;
    private List<SelectItem> versionCcdList;
    private List<SelectItem> versionesOrganigramaList;
    private List<SelectItem> trdUniAmtList;
    private List<SelectItem> trdOfcProduc;
    private List<SelectItem> unidadAdministrativoListExistByCcdOr;
    private List<SelectItem> oficinaProductoraListExistByCcdOr;
    private List<SelectItem> unidadAdministrativoListExistByCcdDe;
    private List<SelectItem> oficinaProductoraListExistByCcdDe;
    private List<SelectItem> seriesListExistInCcdOr;
    private List<SelectItem> subSeriesListtExistInCcdOr;
    private List<SelectItem> seriesListExistInCcdDe;
    private List<SelectItem> subSeriesListtExistInCcdDe;


    public GlobalModel() {
        motivosCreacionList = new ArrayList<>();
        seriesList = new ArrayList<>();
        soportesList = new ArrayList<>();
        tradicionDocList = new ArrayList<>();
        tipologiasDocList = new ArrayList<>();
        subSeriesList = new ArrayList<>();
        unidadAdministrativoList = new ArrayList<>();
        oficinaProductoraList = new ArrayList<>();
        disposicionFinal = new ArrayList<>();
        versionCcdList = new ArrayList<>();
        versionesOrganigramaList = new ArrayList<>();
        trdUniAmtList = new ArrayList<>();
        trdOfcProduc = new ArrayList<>();
        unidadAdministrativoListExistByCcdOr = new ArrayList<>();
        oficinaProductoraListExistByCcdOr =  new ArrayList<>();
        unidadAdministrativoListExistByCcdDe = new ArrayList<>();
        oficinaProductoraListExistByCcdDe =  new ArrayList<>();
        seriesListExistInCcdOr = new ArrayList<>();
        subSeriesListtExistInCcdOr =  new ArrayList<>();
        seriesListExistInCcdDe = new ArrayList<>();
        subSeriesListtExistInCcdDe =  new ArrayList<>();

    }

    public List<SelectItem> getMotivosCreacionList() {
        return motivosCreacionList;
    }

    public void setMotivosCreacionList(List<SelectItem> motivosCreacionList) {
        this.motivosCreacionList = motivosCreacionList;
    }

    public List<SelectItem> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<SelectItem> seriesList) {
        this.seriesList = seriesList;
    }

    public List<SelectItem> getSoportesList() {
        return soportesList;
    }

    public void setSoportesList(List<SelectItem> soportesList) {
        this.soportesList = soportesList;
    }

    public List<SelectItem> getTradicionDocList() {
        return tradicionDocList;
    }

    public void setTradicionDocList(List<SelectItem> tradicionDocList) {
        this.tradicionDocList = tradicionDocList;
    }

    public List<SelectItem> getSubSeriesList() {
        return subSeriesList;
    }

    public void setSubSeriesList(List<SelectItem> subSeriesList) {
        this.subSeriesList = subSeriesList;
    }

    public List<SelectItem> getTipologiasDocList() {
        return tipologiasDocList;
    }

    public void setTipologiasDocList(List<SelectItem> tipologiasDocList) {
        this.tipologiasDocList = tipologiasDocList;
    }

    public List<SelectItem> getVersionCcdList() {
        return versionCcdList;
    }

    public void setVersionCcdList(List<SelectItem> versionCcdList) {
        this.versionCcdList = versionCcdList;
    }

    public List<SelectItem> getUnidadAdministrativoList() {
        return unidadAdministrativoList;
    }

    public void setUnidadAdministrativoList(List<SelectItem> unidadAdministrativoList) {
        this.unidadAdministrativoList = unidadAdministrativoList;
    }

    public List<SelectItem> getOficinaProductoraList() {
        return oficinaProductoraList;
    }

    public void setOficinaProductoraList(List<SelectItem> oficinaProductoraList) {
        this.oficinaProductoraList = oficinaProductoraList;
    }

    public List<SelectItem> getDisposicionFinal() {
        return disposicionFinal;
    }

    public void setDisposicionFinal(List<SelectItem> disposicionFinal) {
        this.disposicionFinal = disposicionFinal;
    }

    public List<SelectItem> getVersionesOrganigramaList() {
        return versionesOrganigramaList;
    }

    public void setVersionesOrganigramaList(List<SelectItem> versionesOrganigramaList) {
        this.versionesOrganigramaList = versionesOrganigramaList;
    }

    public List<SelectItem> getTrdUniAmtList() {
        return trdUniAmtList;
    }

    public void setTrdUniAmtList(List<SelectItem> trdUniAmtList) {
        this.trdUniAmtList = trdUniAmtList;
    }

    public List<SelectItem> getTrdOfcProduc() {
        return trdOfcProduc;
    }

    public void setTrdOfcProduc(List<SelectItem> trdOfcProduc) {
        this.trdOfcProduc = trdOfcProduc;
    }

    public List<SelectItem> getUnidadAdministrativoListExistByCcdOr() {
        return unidadAdministrativoListExistByCcdOr;
    }

    public void setUnidadAdministrativoListExistByCcdOr(List<SelectItem> unidadAdministrativoListExistByCcdOr) {
        this.unidadAdministrativoListExistByCcdOr = unidadAdministrativoListExistByCcdOr;
    }

    public List<SelectItem> getOficinaProductoraListExistByCcdOr() {
        return oficinaProductoraListExistByCcdOr;
    }

    public void setOficinaProductoraListExistByCcdOr(List<SelectItem> oficinaProductoraListExistByCcdOr) {
        this.oficinaProductoraListExistByCcdOr = oficinaProductoraListExistByCcdOr;
    }

    public List<SelectItem> getUnidadAdministrativoListExistByCcdDe() {
        return unidadAdministrativoListExistByCcdDe;
    }

    public void setUnidadAdministrativoListExistByCcdDe(List<SelectItem> unidadAdministrativoListExistByCcdDe) {
        this.unidadAdministrativoListExistByCcdDe = unidadAdministrativoListExistByCcdDe;
    }

    public List<SelectItem> getOficinaProductoraListExistByCcdDe() {
        return oficinaProductoraListExistByCcdDe;
    }

    public void setOficinaProductoraListExistByCcdDe(List<SelectItem> oficinaProductoraListExistByCcdDe) {
        this.oficinaProductoraListExistByCcdDe = oficinaProductoraListExistByCcdDe;
    }

    public List<SelectItem> getSeriesListExistInCcdOr() {
        return seriesListExistInCcdOr;
    }

    public void setSeriesListExistInCcdOr(List<SelectItem> seriesListExistInCcdOr) {
        this.seriesListExistInCcdOr = seriesListExistInCcdOr;
    }

    public List<SelectItem> getSubSeriesListtExistInCcdOr() {
        return subSeriesListtExistInCcdOr;
    }

    public void setSubSeriesListtExistInCcdOr(List<SelectItem> subSeriesListtExistInCcdOr) {
        this.subSeriesListtExistInCcdOr = subSeriesListtExistInCcdOr;
    }

    public List<SelectItem> getSeriesListExistInCcdDe() {
        return seriesListExistInCcdDe;
    }

    public void setSeriesListExistInCcdDe(List<SelectItem> seriesListExistInCcdDe) {
        this.seriesListExistInCcdDe = seriesListExistInCcdDe;
    }

    public List<SelectItem> getSubSeriesListtExistInCcdDe() {
        return subSeriesListtExistInCcdDe;
    }

    public void setSubSeriesListtExistInCcdDe(List<SelectItem> subSeriesListtExistInCcdDe) {
        this.subSeriesListtExistInCcdDe = subSeriesListtExistInCcdDe;
    }

    public void clear() {
        getMotivosCreacionList().clear();
        getSeriesList().clear();
        getSoportesList().clear();
        getTradicionDocList().clear();
        getSubSeriesList().clear();
        getTipologiasDocList().clear();
        getDisposicionFinal().clear();
        getVersionCcdList().clear();
        getVersionesOrganigramaList().clear();

        getUnidadAdministrativoListExistByCcdOr().clear();
        getOficinaProductoraListExistByCcdOr().clear();
        getUnidadAdministrativoListExistByCcdDe().clear();
        getOficinaProductoraListExistByCcdDe().clear();
        getSeriesListExistInCcdOr().clear();
        getSubSeriesListtExistInCcdOr().clear();
        getSeriesListExistInCcdDe().clear();
        getSubSeriesListtExistInCcdDe().clear();
    }
}
