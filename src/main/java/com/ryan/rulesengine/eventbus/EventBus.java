package com.ryan.rulesengine.eventbus;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class EventBus {
//    https://www.emqx.com/en/blog/how-to-use-mqtt-in-java
    private MqttClient mqttClient;

    public EventBus() {
        this.mqttClient = null;
    }

    public EventBus(String url, String clientId) throws MqttException {
        mqttClient = new MqttClient(url, clientId);
        connect();
    }

    public void connect() throws MqttException {

        mqttClient.connect();
        System.out.println("mqttClient connected");

    }

    public void disconnect() throws MqttException {

        if (mqttClient.isConnected()) {
            mqttClient.disconnect();
            System.out.println("mqttClient disconnected");
        }

    }


}
