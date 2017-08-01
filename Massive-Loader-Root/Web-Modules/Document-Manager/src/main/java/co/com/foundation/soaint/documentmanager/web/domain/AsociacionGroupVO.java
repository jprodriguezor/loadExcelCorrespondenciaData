/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author malzate on 27/09/2016
 */
public class AsociacionGroupVO implements Serializable{
        
    private SerieVO ideSerie;
    private SubserieVO ideSubserie;
    private List<TipologiaDocVO> ideTpgDoc;

    public AsociacionGroupVO() {
        ideTpgDoc = new ArrayList<>();
    }

    public AsociacionGroupVO(SerieVO ideSerie, SubserieVO ideSubserie, List<TipologiaDocVO> ideTpgDoc) {
        this.ideSerie = ideSerie;
        this.ideSubserie = ideSubserie;
        this.ideTpgDoc = ideTpgDoc;
    }
    
    public AsociacionGroupVO(SerieVO ideSerie, List<TipologiaDocVO> ideTpgDoc) {
        this.ideSerie = ideSerie;
        this.ideTpgDoc = ideTpgDoc;
    }

    public SerieVO getIdeSerie() {
        return ideSerie;
    }

    public void setIdeSerie(SerieVO ideSerie) {
        this.ideSerie = ideSerie;
    }

    public SubserieVO getIdeSubserie() {
        return ideSubserie;
    }

    public void setIdeSubserie(SubserieVO ideSubserie) {
        this.ideSubserie = ideSubserie;
    }

    public List<TipologiaDocVO> getIdeTpgDoc() {
        return ideTpgDoc;
    }

    public void setIdeTpgDoc(List<TipologiaDocVO> ideTpgDoc) {
        this.ideTpgDoc = ideTpgDoc;
    }

    @Override
    public String toString() {
        return "AsociacionGroupVO{" + "ideSerie=" + ideSerie + ", ideSubserie=" + ideSubserie + ", ideTpgDoc=" + ideTpgDoc + '}';
    }
}