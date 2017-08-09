package co.com.foundation.soaint.documentmanager.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jorge on 14/07/2017.
 */
@Component
public class InitialContextContextLoader {
    public final static String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
    public final static String JMS_QUEUE_JNDI = "jms/queue/documentsQueue";

    @Value("${jms.username}")
    public String JMS_USERNAME = "";       //  Debe tener rol guest

    @Value("${jms.password}")
    public String JMS_PASSWORD = "";

    @Value("${wildfly.remoting.url}")
    public String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";

    public InitialContext getInitialContext() throws NamingException {
        InitialContext context = null;
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);   // NOTICE: "http-remoting" and port "8080"
            props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
            props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
            props.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
            //props.put("jboss.naming.client.ejb.context", true);
            context = new InitialContext(props);
            System.out.println("\n\tGot initial Context: " + context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }
}