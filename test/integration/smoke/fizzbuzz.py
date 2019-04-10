from marvin.cloudstackTestCase import cloudstackTestCase
from marvin.lib.base import (fizzBuzz)
from nose.plugins.attrib import attr


class TestFizzBuzz(cloudstackTestCase):

    def setUp(self):
        self.apiclient = self.testClient.getApiClient()

    def notFizzBuzz(self, answer):
        self.assertIsNotNone(answer)
        self.assertNotEqual("fizz", answer)
        self.assertNotEqual("buzz", answer)
        self.assertNotEqual("fizzbuzz", answer)

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_negativeOne(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        cmd.number = -1
        resp = self.apiclient.fizzBuzz(cmd)
        self.notFizzBuzz(resp.answer)

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_zero(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        cmd.number = 0
        resp = self.apiclient.fizzBuzz(cmd)
        self.assertEqual(resp.answer, u'fizzbuzz')

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_two(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        cmd.number = 2
        resp = self.apiclient.fizzBuzz(cmd)
        self.notFizzBuzz(resp.answer)

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_three(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        cmd.number = 3
        resp = self.apiclient.fizzBuzz(cmd)
        self.assertEqual(resp.answer, u'fizz')

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_five(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        cmd.number = 5
        resp = self.apiclient.fizzBuzz(cmd)
        self.assertEqual(resp.answer, u'buzz')

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_fifteen(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        cmd.number = 15
        resp = self.apiclient.fizzBuzz(cmd)
        self.assertEqual(resp.answer, u'fizzbuzz')

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_fifty(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        cmd.number = 50
        resp = self.apiclient.fizzBuzz(cmd)
        self.assertEqual(resp.answer, u'buzz')

    @attr(tags=['advanced', 'simulator', 'basic', 'sg'], required_hardware=False)
    def test_fizz_buzz_noInput(self):
        cmd = fizzBuzz.fizzBuzzCmd()
        resp = self.apiclient.fizzBuzz(cmd)
        self.assertIsNotNone(resp.answer)
