package org.apache.cloudstack.api.command.user.fizzbuzz;

import javax.inject.Inject;

import org.apache.cloudstack.acl.RoleType;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.BaseCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.api.response.FizzBuzzResponse;
import org.apache.cloudstack.fizzbuzz.FizzBuzzService;

import com.cloud.exception.ConcurrentOperationException;
import com.cloud.user.Account;

/**
 * Created by KioiEddy on 2019-04-04.
 */
@APICommand(name = FizzBuzzCmd.APINAME,
        description = "Returns fizz or buzz based on Number parameter",
        responseObject = FizzBuzzResponse.class,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = false,
        authorized = {RoleType.Admin, RoleType.ResourceAdmin, RoleType.DomainAdmin})
public class FizzBuzzCmd extends BaseCmd {

    @Inject
    FizzBuzzService fizzBuzzService;

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
        return this.number;
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

    public void execute() throws ServerApiException, ConcurrentOperationException {
        FizzBuzzResponse response = new FizzBuzzResponse();
        response.setNumber(getNumber());
        response.setAnswer(fizzBuzzService.getAnswer(getNumber()));
        response.setObjectName("fizzBuzzer");
        response.setResponseName(getCommandName());
        setResponseObject(response);
    }



}
