package co.com.foundation.soaint.documentmanager.web.infrastructure.exceptionhandler;

import co.com.foundation.soaint.documentmanager.web.infrastructure.builder.generic.HTTPResponseBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.infrastructure.common.MessageUtil;
import co.com.foundation.soaint.documentmanager.web.infrastructure.common.HTTPResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by administrador_1 on 06/09/2016.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class.getName());

    // ---------------------------

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public HTTPResponse manageBusinessException(HttpServletRequest request, BusinessException ex) {

        LOGGER.error("MVC Controller Exception Handler - a business error has occurred", ex);

        return HTTPResponseBuilder.newBuilder()
                .withSuccess(false)
                .withMessage(ex.getMessage())
                .build();
    }


    // ---------------------------

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(SystemException.class)
    public HTTPResponse manageSystemException(HttpServletRequest request, SystemException ex) {

        LOGGER.error("MVC Controller Exception Handler - a system error has occurred", ex);

        return HTTPResponseBuilder.newBuilder()
                .withSuccess(false)
                .withMessage(ex.getMessage())
                .build();
    }

    // ---------------------------

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public HTTPResponse manageGenericException(HttpServletRequest request, Exception ex) {

        LOGGER.error("MVC Controller Exception Handler - a generic system error has occurred", ex);

        return HTTPResponseBuilder.newBuilder()
                .withSuccess(false)
                .withMessage(MessageUtil.getMessage("system.generic.error"))
                .build();
    }

}
