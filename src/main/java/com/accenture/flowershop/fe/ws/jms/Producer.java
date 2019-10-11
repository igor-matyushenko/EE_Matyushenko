package com.accenture.flowershop.fe.ws.jms;

import com.accenture.flowershop.be.business.UserMarshallingServiceImpl;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Producer  {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private UserMarshallingServiceImpl userMarshallingService;
    @Autowired
    private UserBusinessService userBusinessService;

    private Connection connection; //сам Connection.
    private Session session; //контекст для посылки и приема сообщений.
    @Autowired
    @Qualifier("inQueue")
    private  Destination inQueue; //буфер отправки  сообщений.



    public void sendMessage(String stringXML) throws JMSException {
        try {
            connection = connectionFactory.createConnection();
            //получаем экзмпляр класса подключения
            connection.start(); //стартуем
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(inQueue);
            //создаем отправителя сообщения
            Message message = session.createTextMessage(stringXML);
            producer.send(message);
        } catch (JMSException e) {

        }finally {
            connection.close();
        }
    }
}
