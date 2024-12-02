package com.ryan.rulesengine.producer;

import com.ryan.rulesengine.eventbus.EventBus;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class OutputProducerTest {

    private EventBus eventBus;
    private OutputProducer outputProducer;

    @BeforeEach
    public void setUp() {
        eventBus = mock(EventBus.class);
        String topicId = "ti";
        outputProducer = new OutputProducer(eventBus, topicId);
    }

    @Test
    public void testProduce() throws MqttException {

        String payload = "{\"ryantest\": \"hope pass oa\"}";
        outputProducer.produce(payload);

        verify(eventBus, times(1)).publish(anyString(), eq(payload), anyInt());
    }


}
