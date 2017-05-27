package twitter_webservice.backingbeans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Anna-May on 25/05/2017.
 */
@Stateless(name = "producerSessionBeanEJB")
public class producerSessionBean {
    public producerSessionBean() {
    }

    @Resource(mappedName = "jms/kwetterQueue")
    private Queue kwetterMayQueque;
    @Resource(mappedName = "jms/kwetterQueuePoolFactory")
    private ConnectionFactory kwetterMayQuequeFactory;

    private Message createJMSMessageForjmsKwetterQueque(Session session, Object messageData) throws JMSException {
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToKwetterQueque(Object messageData) throws JMSException{
        Connection connection = null;
        Session session = null;
        try{
            connection = kwetterMayQuequeFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(kwetterMayQueque);
            messageProducer.send(createJMSMessageForjmsKwetterQueque(session , messageData));
        } finally {
            if(session != null){
                try{
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
        }
    }

    public void sendMessageToQueue(Object message) throws JMSException {
        sendJMSMessageToKwetterQueque(message);
    }
}
