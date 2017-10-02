package co.com.soaint.foundation.documentmanager.jms;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 * Created by Jorge on 14/07/2017.
 */
@Component
@Log4j2
public class WildFlyJmsQueueSender {
    //@Value("${jms.connection.factory.jndi}")
    public String jmsConnectionFactoryJndi = "";

    //@Value("${jms_queue_jndi}")
    public String jmsQueueJndi = "";

    @Autowired
    InitialContextContextLoader initialContextContextLoader;

    //@Value("${jms.username}")
    public String jmsUsername = ""; //  Debe tener rol guest

    //@Value("${jms.password}")
    public String jmsPassword = "";

    //@Value("${wildfly.remoting.url}")
    //public String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";

    private QueueConnectionFactory qconFactory;
    private QueueConnection qcon;
    private QueueSession qsession;
    private QueueSender qsender;
    private Queue queue;
    private TextMessage msg;


    /*public static void main(String[] args) throws Exception {
        InitialContext ic = null;//getInitialContext();
        WildFlyJmsQueueSender queueSender = new WildFlyJmsQueueSender();
        queueSender.init(jmsQueueJndi);
        readAndSend(queueSender);
        queueSender.close();
    }*/

    public void init() throws NamingException, JMSException {
        this.jmsQueueJndi = System.getProperty("jms_queue_jndi");
        log.info("Cola en la que depositar los mensajes: " + this.jmsQueueJndi);
        init(this.jmsQueueJndi);
    }
    public void init(String queueName) throws NamingException, JMSException {
        this.jmsConnectionFactoryJndi = System.getProperty("jms.connection.factory.jndi");
        log.info("Connection Factory a emplear: " + this.jmsConnectionFactoryJndi);
        Context ctx = initialContextContextLoader.getInitialContext();
        qconFactory = (QueueConnectionFactory) ctx.lookup(this.jmsConnectionFactoryJndi);

        //  If you won't pass jms credential here then you will get
        // [javax.jms.JMSSecurityException: HQ119031: Unable to validate user: null]
        this.jmsUsername = System.getProperty("jms.username");
        this.jmsPassword = System.getProperty("jms.password");
        log.info("Usuario: " + jmsUsername + " Password: " + jmsPassword);
        qcon = qconFactory.createQueueConnection(this.jmsUsername, this.jmsPassword);

        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = (Queue) ctx.lookup(queueName);
        qsender = qsession.createSender(queue);
        msg = qsession.createTextMessage();
        qcon.start();
    }

    public void send(String message) throws JMSException {
        msg.setText(message);
        qsender.send(msg);
        log.info("Mensaje enviado");
    }

    public void close() throws JMSException {
        qsender.close();
        qsession.close();
        qcon.close();
    }
}