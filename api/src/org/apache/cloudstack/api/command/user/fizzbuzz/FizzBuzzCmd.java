package org.apache.cloudstack.api.command.user.fizzbuzz;

import java.util.Random;

import org.apache.cloudstack.acl.RoleType;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.BaseCmd;
import org.apache.cloudstack.api.BaseResponse;
import org.apache.cloudstack.api.Parameter;

import com.cloud.serializer.Param;
import com.cloud.user.Account;
import com.google.gson.annotations.SerializedName;

/**
 * Created by KioiEddy on 2019-04-04.
 */
@APICommand(name = FizzBuzzCmd.APINAME,
        description = "Returns fizz or buzz based on Number parameter",
        responseObject = FizzBuzzCmd.FizzBuzzResponse.class,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = false,
        authorized = {RoleType.Admin, RoleType.ResourceAdmin, RoleType.DomainAdmin})
public class FizzBuzzCmd extends BaseCmd {
    public static final String APINAME = "fizzBuzz";

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////
    @Parameter(name = ApiConstants.NUMBER,
            type = CommandType.STRING,
            description = "Number to check whether fizz or buzz")

    private String number;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////
    public String getNumber() {
        return number;
    }

    public String getAnswer() {
        String answer = "";
        int number = Integer.parseInt(getNumber());
        if (number % 15 == 0) {
            answer += "fizzbuzz";
        } else if (number % 5 == 0) {
            answer += "buzz";
        } else if (number % 3 == 0) {
            answer += "fizz";
        }
        if (answer.length() == 0) {
            Random rand = new Random();
            answer = ((Integer) (rand.nextInt(100) + 1)).toString();
        }
        return answer;
    }

    @Override
    public String getCommandName() {
        return APINAME.toLowerCase() + BaseCmd.RESPONSE_SUFFIX;
    }

    @Override
    public long getEntityOwnerId() {
        return Account.ACCOUNT_ID_SYSTEM;
    }
    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    public void execute() {
        FizzBuzzResponse response = new FizzBuzzResponse();
        response.setNumber(getNumber());
        response.setAnswer(getAnswer());
        response.setObjectName("fizzBuzzer");
        response.setResponseName(getCommandName());
        setResponseObject(response);
    }


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
}
