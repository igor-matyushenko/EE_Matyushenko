package com.accenture.flowershop.fe.ws.jsm;

import com.accenture.flowershop.be.business.UserMarshallingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import javax.jms.*;



@Component
public class FlowersGlobalCRM {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private UserMarshallingServiceImpl userMarshallingService;
    @Autowired
    private Queue inQueue;
    @Autowired
    private Queue outQueue;

    private Connection connection;
    private Session session;

    public FlowersGlobalCRM() {
    }

    public void init() throws JMSException {
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageConsumer consumer = session.createConsumer(inQueue);
        connection.start();

        consumer.setMessageListener(
                new MessageListener() {
                    public void onMessage(Message message) {

                    }
                }
        );

    }
}

