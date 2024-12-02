package com.ryan.rulesengine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ryan.rulesengine.consumer.InputConsumer;
import com.ryan.rulesengine.eventbus.EventBus;
import com.ryan.rulesengine.handler.SupplementHandler;
import com.ryan.rulesengine.model.InputData;
import com.ryan.rulesengine.model.OutputData;
import com.ryan.rulesengine.producer.OutputProducer;
import com.ryan.rulesengine.util.JsonUtil;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {

    @Test
    public void integrationTest() throws MqttException, JsonProcessingException, InterruptedException {

        String url = "tcp://test.mosquitto.org:1883";
        String topicId = "integrationTest1";
        String clientId = "integrationTest1-" + System.currentTimeMillis();

        EventBus eventBus = new EventBus(url, clientId);
        InputConsumer inputConsumer = new InputConsumer(eventBus, topicId,
                new SupplementHandler(),
                new OutputProducer(eventBus, topicId));

        inputConsumer.start();

        InputData inputData = new InputData();
        inputData.setId("integrationTest1");
        inputData.setFamilyComposition("single");
        inputData.setNumberOfChildren(3);
        inputData.setFamilyUnitInPayForDecember(true);


        String inputTopic = "BRE/calculateWinterSupplementInput/" + topicId;
        String outputTopic = "BRE/calculateWinterSupplementOutput/" + topicId;

        CountDownLatch latch = new CountDownLatch(1);

        eventBus.subscribe(outputTopic , (topic, msg) -> {
            String payload = new String(msg.getPayload());
            OutputData outputData = JsonUtil.fromJson(payload, OutputData.class);

            assertEquals("integrationTest1", outputData.getId());
            assertTrue(outputData.isEligible());
            assertEquals(120.0, outputData.getBaseAmount());
            assertEquals(60.0, outputData.getChildrenAmount());
            assertEquals(180.0, outputData.getSupplementAmount());

            latch.countDown();
        });

        String inputPayLoad = JsonUtil.toJson(inputData);
        eventBus.publish(inputTopic, inputPayLoad, 1);

        int waitingTime = 30;
        boolean msgReceived = latch.await(waitingTime, TimeUnit.SECONDS);

        assertTrue(msgReceived, "Message not been received within seconds:" + waitingTime);

        eventBus.disconnect();



    }

}
