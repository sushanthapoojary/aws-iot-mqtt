package com.homenews.iot.aws.iot.service;

import com.amazonaws.services.iot.model.*;
import com.homenews.iot.aws.iot.util.AwsIotClient;
import org.springframework.stereotype.Service;

@Service
public class IoTCreateThingService {

    private AwsIotClient awsIotClient;

    public IoTCreateThingService(AwsIotClient awsIotClient){
        this.awsIotClient = awsIotClient;
    }

    public String createThing(String thingName){
        // Check if thing exists
        if(!describeThing(thingName)){
            awsIotClient.getIotClient()
                    .createThing(new CreateThingRequest().withThingName(thingName));
            return "Thing "+thingName+" is registered successfully";
        }
        return "Thing "+thingName+" already exists";
    }

    private boolean describeThing(String thingName) {
        if(thingName == null)
            return false;

        try {
            DescribeThingResponse(thingName);
            return true;
        } catch (ResourceNotFoundException ex) {
            return false;
        }

    }

    private DescribeThingResult DescribeThingResponse(String thingName) {
        DescribeThingRequest describeThingRequest = new DescribeThingRequest();
        describeThingRequest.setThingName(thingName);
        return awsIotClient.getIotClient().describeThing(describeThingRequest);
    }
}
