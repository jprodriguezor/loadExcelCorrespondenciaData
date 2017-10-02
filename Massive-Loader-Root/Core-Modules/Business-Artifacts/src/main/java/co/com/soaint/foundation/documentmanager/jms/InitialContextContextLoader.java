package co.com.soaint.foundation.documentmanager.jms;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by Jorge on 14/07/2017.
 */
@Component
@Log4j2
public class InitialContextContextLoader {
    //public final static String jmsConnectionFactoryJndi = "jms/RemoteConnectionFactory";
    //public final static String jmsQueueJndi = "jms/queue/documentsQueue";

    //@Value("${jms.username}")
    public String jmsUsername = "";       //  Debe tener rol guest

    //@Value("${jms.password}")
    public String jmsPassword = "";

    //@Value("${wildfly.remoting.url}")
    public String wildflyRemotingUrl = "http-remoting://localhost:8080";

    public InitialContext getInitialContext() throws NamingException {
        InitialContext context = null;
        jmsUsername = System.getProperty("jms.username");
        jmsPassword = System.getProperty("jms.password");
        wildflyRemotingUrl = System.getProperty("wildfly.remoting.url");
        log.info("Usuario: " + jmsUsername + " Password: " + jmsPassword + " wildfly remoting URL: " + wildflyRemotingUrl);
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(Context.PROVIDER_URL, wildflyRemotingUrl);   // NOTICE: "http-remoting" and port "8080"
            props.put(Context.SECURITY_PRINCIPAL, jmsUsername);
            props.put(Context.SECURITY_CREDENTIALS, jmsPassword);
            props.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
            //props.put("jboss.naming.client.ejb.context", true);
            props.put("jboss.naming.client.connect.timeout", "15000");
            context = new InitialContext(props);
            System.out.println("\n\tGot initial Context: " + context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }
}