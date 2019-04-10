package org.apache.cloudstack.api.response;

import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.BaseResponse;

import com.cloud.serializer.Param;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KioiEddy on 2019-04-08.
 */
public class FizzBuzzResponse extends BaseResponse {

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////
    @SerializedName(ApiConstants.NUMBER)
    @Param(description = "Number to check whether fizz or buzz")
    private String number;

    @Param(description = "Fizz or buzz result")
    private String answer;


    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////
    public void setNumber(String number) {
        this.number = number;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}