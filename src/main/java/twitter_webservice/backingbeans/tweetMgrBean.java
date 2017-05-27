package twitter_webservice.backingbeans;

import twitter_webservice.domain.Userr;
import twitter_webservice.service.UserMgr;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import java.io.Serializable;

/**
 * Created by Anna-May on 25/05/2017.
 */
@Named("tweetMgrEJB")
@SessionScoped
public class tweetMgrBean implements Serializable {
    private Userr selectedUser;
    private String content;

    @Inject
    private UserMgr userMgr;

    @EJB
    private producerSessionBean producer;
//    @Inject
//    private Producer2 producer;


    @PostConstruct
    public void init(){
        selectedUser = userMgr.getUserByUserName("Rutger1944");
    }

    //region getterSetter
    public Userr getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Userr selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserMgr getUserMgr() {
        return userMgr;
    }

    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    public producerSessionBean getProducer() {
        return producer;
    }

    public void setProducer(producerSessionBean producer) {
        this.producer = producer;
    }
    //endregion

    public void newTweet(){
        String message = selectedUser.getUserName()+ "%" + content;

        try {
            producer.sendMessageToQueue(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
