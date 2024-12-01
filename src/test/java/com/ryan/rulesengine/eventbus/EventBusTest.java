package com.ryan.rulesengine.eventbus;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
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
    public void connectTest() throws MqttException {
        eventBus.connect();
        verify(client,
                times(1)
        ).connect(
                any(MqttConnectOptions.class)
        );
    }

}
