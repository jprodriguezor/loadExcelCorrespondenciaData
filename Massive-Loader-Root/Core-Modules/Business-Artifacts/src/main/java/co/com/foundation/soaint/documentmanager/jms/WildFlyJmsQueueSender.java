package co.com.foundation.soaint.documentmanager.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.IOException;

/**
 * Created by Jorge on 14/07/2017.
 */
@Component
public class WildFlyJmsQueueSender {
    public final static String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
    public final static String JMS_QUEUE_JNDI = "jms/queue/documentsQueue";

    @Autowired
    InitialContextContextLoader initialContextContextLoader;

    @Value("${jms.username}")
    public String JMS_USERNAME = ""; //  Debe tener rol guest

    @Value("${jms.password}")
    public String JMS_PASSWORD = "";

    @Value("${wildfly.remoting.url}")
    public String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";

    private QueueConnectionFactory qconFactory;
    private QueueConnection qcon;
    private QueueSession qsession;
    private QueueSender qsender;
    private Queue queue;
    private TextMessage msg;


    /*public static void main(String[] args) throws Exception {
        InitialContext ic = null;//getInitialContext();
        WildFlyJmsQueueSender queueSender = new WildFlyJmsQueueSender();
        queueSender.init(JMS_QUEUE_JNDI);
        readAndSend(queueSender);
        queueSender.close();
    }*/

    public void init() throws NamingException, JMSException {
        init(JMS_QUEUE_JNDI);
    }
    public void init(String queueName) throws NamingException, JMSException {
        Context ctx = initialContextContextLoader.getInitialContext();
        qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_CONNECTION_FACTORY_JNDI);

        //  If you won't pass jms credential here then you will get
        // [javax.jms.JMSSecurityException: HQ119031: Unable to validate user: null]
        qcon = qconFactory.createQueueConnection(this.JMS_USERNAME, this.JMS_PASSWORD);

        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = (Queue) ctx.lookup(queueName);
        qsender = qsession.createSender(queue);
        msg = qsession.createTextMessage();
        qcon.start();
    }

    public void send(String message/*, int counter*/) throws JMSException {
        msg.setText(message);
        //msg.setIntProperty("counter", counter);
        qsender.send(msg);
    }

    public void close() throws JMSException {
        qsender.close();
        qsession.close();
        qcon.close();
    }

    private static void readAndSend(WildFlyJmsQueueSender wildFlyJmsQueueSender) throws IOException, JMSException {
        String line = "Test Message Body with counter = ";
        for (int i = 0; i < 10; i++) {
           // wildFlyJmsQueueSender.send(line + i, i);
            System.out.println("JMS Message Sent: " + line + i + "\n");
        }
    }
}