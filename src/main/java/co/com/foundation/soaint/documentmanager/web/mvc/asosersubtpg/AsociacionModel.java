/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.mvc.asosersubtpg;

import co.com.foundation.soaint.documentmanager.web.domain.AsociacionGroupVO;
import co.com.foundation.soaint.documentmanager.web.domain.AsociacionVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author malzate on 27/09/2016
 */
@Component
@Scope("session")
public class AsociacionModel {
    private List<AsociacionVO> asociacionList;
    private List<AsociacionGroupVO> asociacionGroupList;

    public AsociacionModel() {
        this.asociacionList = new ArrayList<>();
        asociacionGroupList = new ArrayList<>();
    }

    public List<AsociacionVO> getAsociacionList() {
        return asociacionList;
    }

    public void setAsociacionList(List<AsociacionVO> asociacionList) {
        this.asociacionList = asociacionList;
    }

    public List<AsociacionGroupVO> getAsociacionGroupList() {
        return asociacionGroupList;
    }

    public void setAsociacionGroupList(List<AsociacionGroupVO> asociacionGroupList) {
        this.asociacionGroupList = asociacionGroupList;
    }
    
    public void clear() {
        asociacionList.clear();
        asociacionGroupList.clear();
    }
}