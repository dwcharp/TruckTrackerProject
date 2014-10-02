package com.beeno.trucktracker.util;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;

/**
 * Created by dcharp on 10/2/14.
 */
public class AmazonUtil {

    private  static CognitoCachingCredentialsProvider cognitoProvider;

    public static CognitoCachingCredentialsProvider getCognitoProvider(Context context) {
        if(cognitoProvider == null) {
            cognitoProvider = new CognitoCachingCredentialsProvider(
                    context,
                    "598124958357",
                    "us-east-1:d9bc7196-3900-4393-b715-b580e6872075",
                    "arn:aws:iam::598124958357:user/dwayne",
                    "arn:aws:iam::598124958357:user/dwayne",
                    Regions.US_EAST_1
            );
        }
        return  cognitoProvider;
    }


}
