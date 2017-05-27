package twitter_webservice.backingbeans;

/**
 * Created by Anna-May on 26/05/2017.
 */
public class Producer {
    private static final String JNDI_FACTORY = "jms/kwetterQueuePoolFactory";
    private static final String JNDI_QUEUE = "jms/kwetterQueue";

    public static void main(String[] args) {
//        try {
//            Context jndiContext = new InitialContext();
//            ConnectionFactory cf = (ConnectionFactory) jndiContext.lookup(JNDI_FACTORY);
//            Queue queue = (Queue) jndiContext.lookup(JNDI_QUEUE);
//
//            Connection connection = cf.createConnection();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer producer = session.createProducer(queue);
//
//            TextMessage message = session.createTextMessage();
//            message.setText("JMS 1.1: hallo dit is een bericht");
//            producer.send(message);
//            connection.close();
//        } catch (Exception ex) {
//            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
