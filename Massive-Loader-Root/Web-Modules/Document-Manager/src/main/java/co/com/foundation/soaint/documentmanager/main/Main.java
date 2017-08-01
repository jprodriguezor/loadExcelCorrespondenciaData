package co.com.foundation.soaint.documentmanager.main;

import co.com.foundation.soaint.documentmanager.business.asosersubtpg.interfaces.AsoSerSubTpglManagerProxy;
import co.com.foundation.soaint.documentmanager.business.cuadroclasificaciondoc.interfaces.VersionCuadroClasificacionDocManagerProxy;
import co.com.foundation.soaint.documentmanager.business.ecm.EcmTrdManager;
import co.com.foundation.soaint.documentmanager.business.organigrama.OrganigramaManager;
import co.com.foundation.soaint.documentmanager.business.organigrama.interfaces.OrganigramaManagerProxy;
import co.com.foundation.soaint.documentmanager.business.tablaretenciondisposicion.interfaces.TabRetencionDiposicionManagerProxy;
import co.com.foundation.soaint.documentmanager.business.tablaretenciondisposicion.interfaces.VersionesTabRetDocManagerProxy;
import co.com.foundation.soaint.documentmanager.domain.*;
import co.com.foundation.soaint.documentmanager.integration.client.ecm.ContenidoDependenciaTrdVO;
import co.com.foundation.soaint.documentmanager.integration.client.ecm.EstructuraTrdVO;
import co.com.foundation.soaint.documentmanager.integration.client.ecm.OrganigramaVO;
import co.com.foundation.soaint.documentmanager.persistence.entity.AdmVersionTrd;
import co.com.foundation.soaint.documentmanager.persistence.entity.TvsOrganigramaAdministrativo;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.enterprise.inject.New;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ParameterNameProvider;

public class Main {


    public static void main(String[] args) throws Exception {

        System.setProperty("spring.profiles.active", "dev");

        ApplicationContext ac = new ClassPathXmlApplicationContext("/spring/crosscutting-config.xml",
                "/spring/persistence-config.xml","/spring/client-integration-config.xml");

        VersionCuadroClasificacionDocManagerProxy boundary = (VersionCuadroClasificacionDocManagerProxy) ac.getBean("versionCCDManager");

        VersionesTabRetDocManagerProxy trd = (VersionesTabRetDocManagerProxy) ac.getBean("versionesTabRetDocManager");

        OrganigramaManagerProxy organigrama = (OrganigramaManagerProxy) ac.getBean("organigramaManager");

        EcmTrdManager ecmTrdManager = (EcmTrdManager) ac.getBean("ecmTrdManager");

        ecmTrdManager.generarEstructuraECM("P8Admin","filenet");


        System.exit(0);

    }


}