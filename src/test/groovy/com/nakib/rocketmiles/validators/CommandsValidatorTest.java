package com.nakib.rocketmiles.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandsValidatorTest {
    @Test
    void isValid_exit() {
        Assertions.assertTrue(CommandsValidator.isValid("quit"));
        Assertions.assertTrue(CommandsValidator.isValid("quit 65fdsafadsfa"));
    }

    @Test
    void isValid_show() {
        Assertions.assertTrue(CommandsValidator.isValid("show"));
    }

    @Test
    void isValid_put()  {
        Assertions.assertTrue(CommandsValidator.isValid("put 12 5 6 3 2"));
    }

    @Test
    void isValid_take()  {
        Assertions.assertTrue(CommandsValidator.isValid("take 12 5 6 3 2"));
    }

    @Test
    void isValid_change()  {
        Assertions.assertTrue(CommandsValidator.isValid("change 12"));
    }

    @Test
    void isInvalid_put_size()  {
        Assertions.assertFalse(CommandsValidator.isValid("put 12 5 6 3"));
    }

    @Test
    void isInvalid_put_format()  {
        Assertions.assertFalse(CommandsValidator.isValid("put 12 5 a 3 2"));
    }

    @Test
    void isInvalid_take_size()  {
        Assertions.assertFalse(CommandsValidator.isValid("take"));
    }

    @Test
    void isInvalid_take_format()  {
        Assertions.assertFalse(CommandsValidator.isValid("take 12 5 a 3 2"));
    }

    @Test
    void isInvalid_change_size()  {
        Assertions.assertFalse(CommandsValidator.isValid("change 12 5"));
    }

    @Test
    void isInvalid_change_format()  {
        Assertions.assertFalse(CommandsValidator.isValid("change a"));
    }
}
