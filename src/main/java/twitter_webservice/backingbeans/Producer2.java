package twitter_webservice.backingbeans;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.jms.*;

/**
 * Created by Anna-May on 26/05/2017.
 */
@RequestScoped
public class Producer2 {
    private static final String JNDI_CONNECTION_FACTORY = "jms/kwetterQueuePoolFactory";

    private static final String JNDI_QUEUE = "jms/kwetterQueue";

    @Resource(lookup = JNDI_CONNECTION_FACTORY)
    private ConnectionFactory connectionFactory;

    @Resource(lookup = JNDI_QUEUE)
    private Queue queue;

    public boolean sendMessage(String k) throws JMSException {

        boolean succes = false;
        Connection con = connectionFactory.createConnection();

        try{
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();

            message.setText(k);
            producer.send(message);
            System.out.println("message sent");

            succes = true;
        } catch (Exception e){
            succes = false;
        } finally {
            con.close();
        }

        return succes;
    }

}
