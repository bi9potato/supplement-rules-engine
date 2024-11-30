package com.ryan.rulesengine;

import com.ryan.rulesengine.eventbus.EventBus;

public class App {
    public static void main(String[] args) {

        try{

            String url = "tcp://test.mosquitto.org:1883";
            String clientId = "test";

            EventBus eventBus = new EventBus(url, clientId);

            eventBus.disconnect();

        } catch (Exception e) {
            System.err.println( "exception: " + e.getMessage() );
            e.printStackTrace();
        }
//        start
    }
}
