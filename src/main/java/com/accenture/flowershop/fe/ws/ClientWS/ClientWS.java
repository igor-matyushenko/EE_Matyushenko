package com.accenture.flowershop.fe.ws.ClientWS;


public class ClientWS {
    public static void main(String[] arg) {

        for (int i = 0; i < 100; i++) {
            try {
                int count = 10 + (int) (Math.random() * 30);
                increase(count);
                // For example 10 sec :
                Thread.sleep( 1000L * 10);
//                Thread.sleep(1000L * 10 * 60);
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

