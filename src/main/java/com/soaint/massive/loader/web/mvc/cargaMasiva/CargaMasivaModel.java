/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soaint.massive.loader.web.mvc.cargaMasiva;

import com.soaint.massive.loader.web.domain.CargaMasivaVO;
import com.soaint.massive.loader.web.domain.RegistroCargaMasivaVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
@Scope("session")
public class CargaMasivaModel {

    private List<CargaMasivaVO> listCargaMasiva;
    private List<RegistroCargaMasivaVO> listCargaMasivaDetail;

    public CargaMasivaModel() {
        listCargaMasiva = new ArrayList<>();
        listCargaMasivaDetail =new ArrayList<>();
    }

    public List<CargaMasivaVO> getListCargaMasiva() {
        return listCargaMasiva;
    }

    public void setListCargaMasiva(List<CargaMasivaVO> listCargaMasiva) {
        this.listCargaMasiva = listCargaMasiva;
    }

    public List<RegistroCargaMasivaVO> getListCargaMasivaDetail() {
        return listCargaMasivaDetail;
    }

    public void setListCargaMasivaDetail(List<RegistroCargaMasivaVO> listCargaMasivaDetail) {
        this.listCargaMasivaDetail = listCargaMasivaDetail;
    }

}
