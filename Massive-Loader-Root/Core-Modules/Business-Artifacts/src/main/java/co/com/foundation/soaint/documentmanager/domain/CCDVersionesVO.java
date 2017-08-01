/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CCDVersionesVO {
    
    private List<CCDForVersionVO> listVersion;

    public CCDVersionesVO() {
       this.listVersion = new ArrayList<>();
    }

    public List<CCDForVersionVO> getDataCcd() {
        return listVersion;
    }

    public void setDataCcd(List<CCDForVersionVO> dataCcd) {
        this.listVersion.addAll(dataCcd);
    }

    @Override
    public String toString() {
        return "CCDVersionesVO{" + "listVersion=" + listVersion + '}';
    }

    

    
    
    
     
}
