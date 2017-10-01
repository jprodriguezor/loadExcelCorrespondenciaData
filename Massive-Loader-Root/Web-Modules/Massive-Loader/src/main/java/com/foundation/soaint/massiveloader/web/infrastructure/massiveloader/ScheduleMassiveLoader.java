package com.foundation.soaint.massiveloader.web.infrastructure.massiveloader;

import co.com.foundation.soaint.infrastructure.exceptions.BusinessException;
import co.com.foundation.soaint.infrastructure.exceptions.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.text.ParseException;

/**
 * Created by Jorge on 29/09/2017.
 */
@Component
public class ScheduleMassiveLoader {

    @Autowired
    public MassiveLoaderRetry massiveLoaderRetry;

    @Scheduled(fixedRate = 30000, initialDelay = 10000)
    public void retryCall() throws ParseException, NamingException, JMSException, BusinessException, SystemException {
        massiveLoaderRetry.retryCall();
    }
}
