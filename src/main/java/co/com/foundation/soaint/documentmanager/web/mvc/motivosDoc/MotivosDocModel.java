/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.foundation.soaint.documentmanager.web.mvc.motivosDoc;

import co.com.foundation.soaint.documentmanager.web.domain.MotivosDocVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author Diego Sanchez
 */

@Component
@Scope("session")
public class MotivosDocModel implements Serializable{
    
    private List<MotivosDocVO> motivosCreacionList;

    
    public MotivosDocModel() {
        motivosCreacionList = new ArrayList<>();
    }

    
    public List<MotivosDocVO> getMotivosCreacionList() {
        return motivosCreacionList;
    }

    public void setMotivosCreacionList(List<MotivosDocVO> motivosCreacionList) {
        this.motivosCreacionList = motivosCreacionList;
    }
    
    public void clear() {
       motivosCreacionList.clear();
    }
    

    
}
