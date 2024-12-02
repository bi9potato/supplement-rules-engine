package com.ryan.rulesengine.producer;

import com.ryan.rulesengine.eventbus.EventBus;
import org.eclipse.paho.client.mqttv3.MqttException;
import com.ryan.rulesengine.util.ConfigUtil;

public class OutputProducer {

    private EventBus eventBus;
    private String outTopic;

    public OutputProducer(EventBus eventBus, String topicId) {
        this.eventBus = eventBus;
        this.outTopic = ConfigUtil.getProp("mqtt.output.base") + topicId;

        System.out.println("output topic: " + outTopic);
    }

//    publish payload to the output MQTT topic with QoS 1
    public void produce(String outputPayLoad) throws MqttException {
        eventBus.publish(outTopic, outputPayLoad, 1);
    }
}
