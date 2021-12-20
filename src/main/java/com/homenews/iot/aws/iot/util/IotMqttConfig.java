package com.homenews.iot.aws.iot.util;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homenews.iot.aws.iot.dto.HomeNewsPayload;
import com.homenews.iot.aws.iot.dto.IotAppConfig;
import com.homenews.iot.aws.iot.dto.PublishHomeNews;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IotMqttConfig {
    private final IotAppConfig iotAppConfig;
    private AWSIotMqttClient awsIotMqttClient;

    public IotMqttConfig(IotAppConfig iotAppConfig){
        this.iotAppConfig = iotAppConfig;
    }

    private void clientConnect(String thingName) throws AWSIotException {
        awsIotMqttClient = new AWSIotMqttClient(iotAppConfig.getClientEndpoint(), thingName,
                iotAppConfig.getAwsAccessKeyId(), iotAppConfig.getAwsSecretAccessKey(), null);

        // optional parameters can be set before connect()
        awsIotMqttClient.connect();
    }

    private void clientDisconnect() throws AWSIotException {
        awsIotMqttClient.disconnect();
    }

    public void publishHomeNews(HomeNewsPayload homeNewsPayload,
                                String thingName,
                                String topic) throws JsonProcessingException, AWSIotException
    {
        clientConnect(thingName);
        AWSIotQos qos = AWSIotQos.QOS0;
        long timeout = Long.parseLong(iotAppConfig.getTimeout());  // milliseconds
        ObjectMapper mapper = new ObjectMapper();

        PublishHomeNews news = new PublishHomeNews(topic, qos, mapper.writeValueAsString(homeNewsPayload));
        awsIotMqttClient.publish(news, timeout);
        clientDisconnect();
    }
}
