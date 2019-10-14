package com.accenture.flowershop.fe.ws.jms;

import com.accenture.flowershop.be.business.UserMarshallingServiceImpl;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.jms.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


@EnableScheduling
@Component
public class Consumer {

    @Value("${filepath}")
    private String filePath;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private UserMarshallingServiceImpl userMarshallingService;
    @Autowired
    private UserBusinessService userBusinessService;

    private Connection connection; //сам Connection.
    private Session session; //контекст для посылки и приема сообщений.

    @Autowired
    @Qualifier("outQueue")
    private  Destination outQueue; // буфер получения сообщений


    public Consumer() {
    }

    @Scheduled(fixedRate = 15000)
    public void receive() throws JMSException {
        connection = connectionFactory.createConnection();
        //получаем экзмпляр класса подключения
        connection.start(); //стартуем
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //создаем объект сессию без транзакций
        //параметром AUTO_ACKNOWLEDGE мы указали что отчет о доставке будет
        //отправляться автоматически при получении сообщения.
        MessageConsumer consumer = session.createConsumer(outQueue);
        //создаем объект получателя сообщения
        consumer.setMessageListener(
                new MessageListener() {
                    public void onMessage(Message message) {
                        String textFromMessage = null;
                        try {
                            TextMessage textmessage = (TextMessage) message;
                            textFromMessage = textmessage.getText();
//                            System.out.println("TextMessage: " + textFromMessage);
                            DiscountRequestObject requestObject = (DiscountRequestObject) userMarshallingService
                                            .convertStringXmlToObject(textFromMessage);
                            userBusinessService.updateDiscountOfUser(requestObject.getId(),requestObject.getDiscount());
                        } catch (JMSException  e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }  finally {
                            try {
                                connection.close();
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
        );
    }

}


