package com.ryan.rulesengine.consumer;

import com.ryan.rulesengine.eventbus.EventBus;
import com.ryan.rulesengine.handler.SupplementHandler;
import com.ryan.rulesengine.model.InputData;
import com.ryan.rulesengine.producer.OutputProducer;
import com.ryan.rulesengine.util.JsonUtil;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class InputConsumerTest {

    private EventBus eventBus;
    private OutputProducer outputProducer;
    private SupplementHandler supplementHandler;
    private InputConsumer inputConsumer;

    @BeforeEach
    public void init() {
        eventBus = mock(EventBus.class);
        outputProducer = mock(OutputProducer.class);
        supplementHandler = mock(SupplementHandler.class);

        String topicId = "testTopicId";
        inputConsumer = new InputConsumer(eventBus, topicId,
                supplementHandler, outputProducer);
    }

    @Test
    public void testStart() throws MqttException {
        inputConsumer.start();
        verify(eventBus, times(1)).subscribe(anyString(), any(IMqttMessageListener.class));
    }

    @Test
    public void testMessageArrived() throws Exception {

        IMqttMessageListener listener = ListenerCaptor();

        InputData inputData = new InputData();
        inputData.setId("testMessageArrived1");
        inputData.setFamilyComposition("single");
        inputData.setNumberOfChildren(12);
        inputData.setFamilyUnitInPayForDecember(true);

        String payload = JsonUtil.toJson(inputData);
        MqttMessage mqttMessage = new MqttMessage(payload.getBytes());

        // Mock supplementHandler and outputProducer
        when(supplementHandler.start(any(InputData.class))).thenReturn(null);

        listener.messageArrived("testArrivedTopic", mqttMessage);

        // Verify supplementHandler & outputProducer are invoked
        verify(supplementHandler, times(1)).start(any(InputData.class));
        verify(outputProducer, times(1)).produce(anyString());
    }

    private IMqttMessageListener ListenerCaptor() throws MqttException {

        inputConsumer.start();

        ArgumentCaptor<IMqttMessageListener> listenerCaptor =
                ArgumentCaptor.forClass(IMqttMessageListener.class);
        verify(eventBus).subscribe(anyString(), listenerCaptor.capture());
        return listenerCaptor.getValue();
    }



}
