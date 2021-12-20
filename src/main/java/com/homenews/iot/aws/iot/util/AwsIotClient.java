package com.homenews.iot.aws.iot.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.iot.AWSIot;
import com.amazonaws.services.iot.AWSIotClientBuilder;
import com.homenews.iot.aws.iot.dto.IotAppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsIotClient {

    private final IotAppConfig iotAppConfig;
    public AwsIotClient(IotAppConfig iotAppConfig){
        this.iotAppConfig = iotAppConfig;
    }

    @Bean
    public AWSIot getIotClient(){
        return AWSIotClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(new BasicAWSCredentials(iotAppConfig.getAwsAccessKeyId(),
                        iotAppConfig.getAwsSecretAccessKey()))).withRegion(Regions.DEFAULT_REGION)
                .build();
    }
}
