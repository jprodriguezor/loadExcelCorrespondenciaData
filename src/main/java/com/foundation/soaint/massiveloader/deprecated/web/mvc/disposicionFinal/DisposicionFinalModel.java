/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foundation.soaint.massiveloader.deprecated.web.mvc.disposicionFinal;

import com.foundation.soaint.massiveloader.deprecated.web.domain.DisposicionFinalVO;
import java.io.Serializable;
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
public class DisposicionFinalModel implements Serializable{
    
     private List<DisposicionFinalVO> disposicionFinalList;

    public DisposicionFinalModel() {
        disposicionFinalList = new ArrayList<>();
    }

    public List<DisposicionFinalVO> getDisposicionFinalList() {
        return disposicionFinalList;
    }

    public void setDisposicionFinalList(List<DisposicionFinalVO> disposicionFinalList) {
        this.disposicionFinalList = disposicionFinalList;
    }
     
    public void clear() {
       disposicionFinalList.clear();
    }
    
     
    
}
