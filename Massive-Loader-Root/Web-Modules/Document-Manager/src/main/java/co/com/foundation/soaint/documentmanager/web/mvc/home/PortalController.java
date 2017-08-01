package co.com.foundation.soaint.documentmanager.web.mvc.home;

import co.com.foundation.soaint.documentmanager.adapter.SecurityClientAdapter;

import co.com.foundation.soaint.documentmanager.web.domain.LoginInfoVO;
import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.HTTPResponseBuilder;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.HTTPResponse;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.security.wui.domain.LoginContext;
import co.com.foundation.soaint.security.wui.mvc.PortalModel;
import co.com.foundation.soaint.security.wui.util.WebSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("request")
@RequestMapping(value = "/")
public class PortalController {

    @Autowired
    private PortalModel model;
    @Autowired
    private SecurityClientAdapter securityClientAdapter;

    public PortalController() {
    }

    // ----------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String initHome() {
        return "home";
    }

    // ----------------------------

    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String initLogin() {
        return "login";
    }


    // ----------------------------

    @ResponseBody
    @RequestMapping(value = "security/processLogin", method = RequestMethod.POST)
    public HTTPResponse processLogin(@RequestBody LoginInfoVO info) throws BusinessException, SystemException {

        LoginContext context = securityClientAdapter.login(info.getUsuario(), info.getClave());

        if (context.isLogged()) {
            model.setContext(context);
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(true)
                    .build();
        } else {
            model.setContext(LoginContext.newInstance());
            return HTTPResponseBuilder.newBuilder()
                    .withSuccess(false)
                    .withMessage(MessageUtil.getMessage("system.security.login_error"))
                    .build();
        }
    }


    // ----------------------------

    @RequestMapping(value = "security/processLogout", method = {RequestMethod.GET})
    public String logout() {
        WebSecurityUtil.terminateSession();
        return "login";
    }

}
