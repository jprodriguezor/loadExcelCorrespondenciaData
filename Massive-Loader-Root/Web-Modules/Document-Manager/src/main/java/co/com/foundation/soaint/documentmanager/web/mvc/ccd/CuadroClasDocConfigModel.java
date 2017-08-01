/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.com.foundation.soaint.documentmanager.web.mvc.ccd;

import co.com.foundation.soaint.documentmanager.web.domain.CcdVO;
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
public class CuadroClasDocConfigModel implements Serializable{
    private List<CcdVO> ccdList;
    private List<CcdVO> ccdListByVersion;

    public CuadroClasDocConfigModel() {
        ccdList = new ArrayList<>();
        ccdListByVersion = new ArrayList<>();
    }

    public List<CcdVO> getCcdList() {
        return ccdList;
    }

    public void setCcdList(List<CcdVO> ccdList) {
        this.ccdList = ccdList;
    }

    public List<CcdVO> getCcdListByVersion() {
        return ccdListByVersion;
    }

    public void setCcdListByVersion(List<CcdVO> ccdListByVersion) {
        this.ccdListByVersion = ccdListByVersion;
    }

    public void clear() {
        ccdList.clear();
    }
    public void clearListByVersion() {
        ccdListByVersion.clear();
    }

}
