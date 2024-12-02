package com.ryan.rulesengine.eventbus;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class EventBus {

    private MqttClient mqttClient;

//    for test
    public EventBus(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

//    create an EventBus with the MQTT broker URL and client ID
    public EventBus(String url, String clientId) throws MqttException {
        mqttClient = new MqttClient(url, clientId, new MemoryPersistence());
        connect();
    }

//    connect to MQTT broker
    public void connect() throws MqttException {

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        mqttClient.connect(options);
        System.out.println("mqttClient connected");

    }

    public void disconnect() throws MqttException {

        if (mqttClient != null && mqttClient.isConnected()) {
            mqttClient.disconnect();
            System.out.println("mqttClient disconnected");
        } else {
            System.out.println("mqttClient not connected");
        }

    }

    public void publish(String topic, String payload, int qos) throws MqttException {

        MqttMessage mqttMessage = new MqttMessage(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttClient.publish(topic, mqttMessage);

        System.out.println("Message published, topic: " + topic + ", msg: " + payload);

    }

    public void subscribe(String topic, IMqttMessageListener listener) throws MqttException {

        mqttClient.subscribe(topic, listener);

        System.out.println("Message subscribed, topic: " + topic);
    }


}
