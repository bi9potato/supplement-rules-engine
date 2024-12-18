package com.ryan.rulesengine.consumer;

import com.ryan.rulesengine.eventbus.EventBus;
import com.ryan.rulesengine.handler.SupplementHandler;
import com.ryan.rulesengine.model.InputData;
import com.ryan.rulesengine.model.OutputData;
import com.ryan.rulesengine.producer.OutputProducer;
import com.ryan.rulesengine.util.JsonUtil;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.ryan.rulesengine.util.ConfigUtil;


public class InputConsumer {

    private String inTopic;
    private EventBus eventBus;
    private SupplementHandler supplementHandler;
    private OutputProducer outputProducer;


//    public InputConsumer(EventBus eventBus, String topicId) {
//
//        this.inTopic = ConfigUtil.getProp("mqtt.input.base") + topicId;
//        this.eventBus = eventBus;
//        this.supplementHandler = new SupplementHandler();
//        this.outputProducer = new OutputProducer(eventBus, topicId);
//
//        System.out.println("input topic: " + inTopic);
//
//    }

    public InputConsumer(EventBus eventBus, String topicId,
                         SupplementHandler supplementHandler, OutputProducer outputProducer) {

        this.inTopic = ConfigUtil.getProp("mqtt.input.base") + topicId;
        this.eventBus = eventBus;
        this.supplementHandler = supplementHandler;
        this.outputProducer = outputProducer;

        System.out.println("input topic: " + inTopic);

    }

//    start listening to the input MQTT topic, and process incoming data then pass to handler and producer.
    public void start() throws MqttException {
        eventBus.subscribe(inTopic, new IMqttMessageListener() {
            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) {

                try {
                    String inputPayload = new String(mqttMessage.getPayload());
                    InputData inputData = JsonUtil.fromJson(inputPayload, InputData.class);

                    OutputData outputData = supplementHandler.start(inputData);
                    String outputPayload = JsonUtil.toJson(outputData);

                    outputProducer.produce(outputPayload);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }



            }
        });
    }

}
