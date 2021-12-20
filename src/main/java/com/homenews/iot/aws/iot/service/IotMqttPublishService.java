package com.homenews.iot.aws.iot.service;

import com.amazonaws.services.iot.client.AWSIotException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.homenews.iot.aws.iot.dto.HomeNewsPayload;
import com.homenews.iot.aws.iot.util.IotMqttConfig;
import org.springframework.stereotype.Service;

@Service
public class IotMqttPublishService {

    private IotMqttConfig iotMqttConfig;

    public IotMqttPublishService(IotMqttConfig iotMqttConfig){
        this.iotMqttConfig = iotMqttConfig;
    }

    public void publishNews(HomeNewsPayload homeNewsPayload,
                            String thingName,
                            String topic) throws AWSIotException, JsonProcessingException
    {
        iotMqttConfig.publishHomeNews(homeNewsPayload, thingName, topic);
    }
}
