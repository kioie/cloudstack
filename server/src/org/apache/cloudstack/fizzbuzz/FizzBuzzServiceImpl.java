package org.apache.cloudstack.fizzbuzz;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.cloudstack.api.command.user.fizzbuzz.FizzBuzzCmd;
import org.apache.cloudstack.context.CallContext;

import com.cloud.utils.StringUtils;
import com.cloud.utils.component.ManagerBase;
import com.cloud.utils.component.PluggableService;
import com.cloud.vm.dao.VMInstanceDao;

/**
 * Created by KioiEddy on 2019-04-08.
 */
public class FizzBuzzServiceImpl extends ManagerBase implements FizzBuzzService, PluggableService {

    @Inject
    private VMInstanceDao vmDao;

    public String getAnswer(String numberToFizzBuzz) {
        String answer = "";
        int number;
        if (StringUtils.isNotBlank(numberToFizzBuzz)) {
            number = Integer.parseInt(numberToFizzBuzz);
        } else {
            Long accountId = CallContext.current().getCallingAccountId();
            number = Math.toIntExact(vmDao.countRunningByAccount(accountId));
        }

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
    public List<Class<?>> getCommands() {
        final List<Class<?>> cmdList = new ArrayList<>();
        cmdList.add(FizzBuzzCmd.class);
        return cmdList;
    }
}
