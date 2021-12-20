package com.homenews.iot.aws.iot.controller;

import com.amazonaws.services.iot.client.AWSIotException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.homenews.iot.aws.iot.dto.HomeNewsPayload;
import com.homenews.iot.aws.iot.service.IoTCreateThingService;
import com.homenews.iot.aws.iot.service.IotMqttPublishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Home News IoT", description = "APIs to connect to AWS IoT Thing")
public class IotMqttController {

    private IotMqttPublishService iotMqttService;
    private IoTCreateThingService createThingService;
    public IotMqttController(IotMqttPublishService iotMqttService, IoTCreateThingService createThingService){
        this.iotMqttService = iotMqttService;
        this.createThingService = createThingService;
    }

    @Operation(summary = "Publishes given message to a AWS IoT topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Message is successfully published to AWS IoT Thing",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
            description = "AWS IoT Thing isn't found",
            content = @Content)
    })
    @PostMapping(value = "/publish/{thingName}/{topic}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String publishNews(@RequestBody HomeNewsPayload payload,
                              @PathVariable String thingName,
                              @PathVariable String topic) throws AWSIotException, JsonProcessingException
    {
        iotMqttService.publishNews(payload, thingName, topic);
        return "News published successfully";
    }

    @Operation(summary = "Registers AWS IoT Thing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "AWS IoT Thing is registered successfully",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping(value = "/register/{thingName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createThing(@PathVariable String thingName)
    {
        return createThingService.createThing(thingName);
    }
}
