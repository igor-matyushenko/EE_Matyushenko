package com.accenture.flowershop.fe.ws.ClientWS;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientWS {
    public static void main(String[] arg) {

        for (int i = 0; i < 100; i++) {
            try {
                int count = 10 + (int) (Math.random() * 30);
                increase(count);
                // For example 10 sec :
                Thread.sleep(10 * 1000L);
//                Thread.sleep(10000L * 60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void increase(int count) {
        FlowersStockWebServiceImplService service = new FlowersStockWebServiceImplService();
        FlowersStockWebService sei = service.getFlowersStockWebServiceImplPort();
        sei.increaseFlowersStockSize(count);
    }
}

