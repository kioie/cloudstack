package org.apache.cloudstack.fizzbuzz;

import org.apache.cloudstack.context.CallContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.cloud.vm.dao.VMInstanceDao;

/**
 * Created by KioiEddy on 2019-04-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class FizzBuzzServiceImplTest {

    @Spy
    @InjectMocks
    private FizzBuzzServiceImpl fizzBuzzImpl;

    @Mock
    private VMInstanceDao _vmDao;

    private void notFizzBuzz(String actual) {
        Assert.assertNotNull(actual);
        Assert.assertNotEquals("fizz", actual);
        Assert.assertNotEquals("buzz", actual);
        Assert.assertNotEquals("fizzbuzz", actual);
    }

    @Test
    public void FizzBuzzTestNegativeOne() {
        String answer = fizzBuzzImpl.getAnswer("-1");
        notFizzBuzz(answer);
    }

    @Test
    public void FizzBuzzTestZero() {
        String answer = fizzBuzzImpl.getAnswer("0");
        Assert.assertEquals("fizzbuzz", answer);
    }

    @Test
    public void FizzBuzzTestTwo() {
        String answer = fizzBuzzImpl.getAnswer("2");
        notFizzBuzz(answer);
    }

    @Test
    public void FizzBuzzTestThree() {
        String answer = fizzBuzzImpl.getAnswer("3");
        Assert.assertEquals("fizz", answer);
    }

    @Test
    public void FizzBuzzTestFive() {
        String answer = fizzBuzzImpl.getAnswer("5");
        Assert.assertEquals("buzz", answer);
    }

    @Test
    public void FizzBuzzTestFifteen() {
        String answer = fizzBuzzImpl.getAnswer("15");
        Assert.assertEquals("fizzbuzz", answer);
    }

    @Test
    public void FizzBuzzTestFifty() {
        String answer = fizzBuzzImpl.getAnswer("50");
        Assert.assertEquals("buzz", answer);
    }

    @Test
    public void FizzBuzzTestNoInput() {
        String answer = fizzBuzzImpl.getAnswer("");
        long accountId = CallContext.current().getCallingAccountId();
        String numberOfRunningVMs = String.valueOf(_vmDao.countRunningByAccount(accountId));
        String answer2 = fizzBuzzImpl.getAnswer(numberOfRunningVMs);
        Assert.assertEquals(answer, answer2);
    }
}