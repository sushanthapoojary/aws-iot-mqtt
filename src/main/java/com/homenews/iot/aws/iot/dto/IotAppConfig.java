package com.homenews.iot.aws.iot.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="aws")
@Component
@Getter
@Setter
public class IotAppConfig {
    private String clientEndpoint;
    private String timeout;
    private String awsAccessKeyId;
    private String awsSecretAccessKey;
    private String sessionToken;
}
