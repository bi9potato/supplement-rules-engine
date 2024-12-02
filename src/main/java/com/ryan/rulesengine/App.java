package com.ryan.rulesengine;

import com.ryan.rulesengine.consumer.InputConsumer;
import com.ryan.rulesengine.eventbus.EventBus;
import com.ryan.rulesengine.handler.SupplementHandler;
import com.ryan.rulesengine.producer.OutputProducer;
import com.ryan.rulesengine.util.ConfigUtil;

import java.util.UUID;


public class App {

    private static EventBus eventBus = null;

//    entry point
    public static void main(String[] args) {

//        EventBus eventBus = null;

        try{
//            get configuration properties
            String topicId = ConfigUtil.getProp("topicId");
            String url = ConfigUtil.getProp("mqtt.url");
            String clientId = ConfigUtil.getProp("mqtt.client.id.base") + "-" + UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
                    System.out.println("topic id: " + topicId);
            System.out.println("url: " + url);
            System.out.println("current clientId: " + clientId);

//            create EventBus
            eventBus = new EventBus(url, clientId);

//            create InputConsumer
            InputConsumer inputConsumer = new InputConsumer(eventBus, topicId,
                    new SupplementHandler(),
                    new OutputProducer(eventBus, topicId));

//            start listening
            inputConsumer.start();



//            while (true) {
//                Thread.sleep(1000);
//            }

//            shutdown hook to disconnect EventBus when application terminated
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Disconnecting...");
                if (eventBus != null) {
                    try {
                        eventBus.disconnect();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }));

            // keep app running
            synchronized (App.class) {
                App.class.wait();
            }




        } catch (Exception e) {
            System.out.println(e.getMessage() );
            e.printStackTrace();
        } finally {
            if (eventBus != null) {
                try {
                    eventBus.disconnect();
                } catch (Exception e) {
                    System.out.println(e.getMessage() );
                    e.printStackTrace();
                }
            }
        }

    }
}
