package com.ryan.rulesengine.eventbus;

import org.eclipse.paho.client.mqttv3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class EventBusTest {

    private MqttClient client;
    private EventBus eventBus;
    private String url = "tcp://test.mosquitto.org:1883";
    private String clientId = "testClient";

    @BeforeEach
    public void init() throws MqttException {
        client = mock(MqttClient.class); // mock client

        eventBus = new EventBus(client);
    }

    @Test
    public void testConnect() throws MqttException {
        eventBus.connect();
        verify(client,
                times(1)
        ).connect(
                any(MqttConnectOptions.class)
        );
    }

    @ Test
    public void testDisconnect() throws MqttException {
        when(client.isConnected()).thenReturn(true);

        eventBus.disconnect();
        verify(client, times(1)).disconnect();
    }

    @Test
    public void testPublish() throws MqttException {
        String topic = "testTopic/qwe123";
        String payload = "fake payload";
        int qos = 1;

        eventBus.publish(topic, payload, qos);
        verify(client, times(1)).publish(eq(topic), any(MqttMessage.class));
    }

    @Test
    public void testSubscribe() throws MqttException {
        String topic = "testTopic/qwe123";
        IMqttMessageListener listener = (tpc, msg) -> {};

        eventBus.subscribe(topic, listener);
        verify(client, times(1)).subscribe(eq(topic), eq(listener));
    }

}
