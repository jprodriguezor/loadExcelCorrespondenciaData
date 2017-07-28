package com.soaint.massive.loader.web.mvc.trd;

import co.com.foundation.soaint.documentmanager.business.ecm.EcmTrdManager;
import com.soaint.massive.loader.web.infrastructure.builder.generic.HTTPResponseBuilder;
import com.soaint.massive.loader.web.infrastructure.common.HTTPResponse;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.security.wui.mvc.PortalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jrodriguez on 08/01/2017.
 */
@Controller
@Scope("request")
@RequestMapping(value = "/trdEcm")
public class TrdPublicarController {

    @Autowired
    private EcmTrdManager ecmBoundary;

    @Autowired
    private PortalModel model;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String init() throws BusinessException, SystemException {
        return "trd/trd-publicar-ui";
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public HTTPResponse publicarVersionTrd() throws BusinessException, SystemException {

        ecmBoundary.generarEstructuraECM(model.getContext().getUsername(), model.getContext().getPassword());

        return HTTPResponseBuilder.newBuilder()
                .withSuccess(true)
                .withMessage(MessageUtil.getMessage("trd.public.ecm.successfully"))
                .build();
    }


}
