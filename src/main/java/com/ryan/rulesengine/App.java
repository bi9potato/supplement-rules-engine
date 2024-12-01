package com.ryan.rulesengine;

import com.ryan.rulesengine.consumer.InputConsumer;
import com.ryan.rulesengine.eventbus.EventBus;
import com.ryan.rulesengine.util.ConfigUtil;

import java.util.UUID;


public class App {
    public static void main(String[] args) {

        EventBus eventBus = null;

        try{

            String topicId = ConfigUtil.getProp("topicId");
            String url = ConfigUtil.getProp("mqtt.url");
            String clientId = ConfigUtil.getProp("mqtt.client.id.base") + "-" + UUID.randomUUID().toString();
            System.out.println("topic id: " + topicId);
            System.out.println("url: " + url);
            System.out.println("current clientId: " + clientId);

            eventBus = new EventBus(url, clientId);
            InputConsumer inputConsumer = new InputConsumer(eventBus, topicId);

            inputConsumer.start();



            while (true) {
                Thread.sleep(1000);
            }




        } catch (Exception e) {
            System.err.println( "exception: " + e.getMessage() );
            e.printStackTrace();
        } finally {
            if (eventBus != null) {
                try {
                    eventBus.disconnect();
                } catch (Exception e) {
                    System.err.println( "exception: " + e.getMessage() );
                    e.printStackTrace();
                }
            }
        }

    }
}
