package com.foundation.soaint.massiveloader.web.infrastructure.executor;


import co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader.CmRegistroCargaMasivaBuilder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.MassiveRecordContext;
import co.com.foundation.soaint.documentmanager.persistence.entity.CmCargaMasiva;
import co.com.foundation.soaint.infrastructure.annotations.InfrastructureService;
import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.ExceptionBuilder;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import co.com.foundation.soaint.infrastructure.proxy.ProxyBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

@InfrastructureService
public class GenericLoaderExecutor<T> extends LoaderExecutor<MassiveRecordContext<T>> {

    @Autowired
    private ApplicationContext appContext;

    // -----------------------

    @Override
    public boolean processRecord(MassiveRecordContext<T> input, CallerContext callerContext) {
        LOGGER.info("start generic loader executor -  processing record: " + input.getRaw());
        boolean success = true;
        try {
            callService(input.getDomainItem(), callerContext);
        } catch (SystemException | BusinessException e) {
            success = false;
        } catch (Exception e) {
            success = false;
        }
        LOGGER.info("end processing record: " + input.getRaw());
        return success;
    }

    // -----------------------

    private void callService(T domainItem, CallerContext callerContext) throws BusinessException, SystemException, InvocationTargetException, IllegalAccessException {

        Object serviceBean = appContext.getBean(callerContext.getBeanName());
        Method[] methods = callerContext.getServiceInterface().getMethods();
        Method targetMethod = null;

        for (Method option : methods) {
            if (StringUtils.equalsIgnoreCase(option.getName(), callerContext.getMethodName())) {
                targetMethod = option;
                break;
            }
        }

        if (!Objects.isNull(targetMethod)) {

            Object proxy = ProxyBuilder.getInstance().newProxy(serviceBean,callerContext.getServiceInterface());
            targetMethod.invoke(proxy,domainItem);

        } else {
            throw ExceptionBuilder.newBuilder()
                    .withMessage("massiveloader.caller.method.error")
                    .buildSystemException();
        }

    }

}
