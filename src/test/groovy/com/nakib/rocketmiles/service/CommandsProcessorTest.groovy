package com.nakib.rocketmiles.service

import com.nakib.rocketmiles.repositories.CashRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommandsProcessorTest {

    CommandsProcessor commandsProcessor
    CashRepository cashRepository

    @BeforeEach
    void setUp() {
        cashRepository = new CashRepository()
        commandsProcessor = new CommandsProcessor(
                cashRepository: cashRepository
        )
    }

    @Test
    void testPut() {

        Assertions.assertEquals("\$0 0 0 0 0 0", commandsProcessor.process("show"))

        Assertions.assertEquals("\$68 1 2 3 4 5", commandsProcessor.process("put 1 2 3 4 5".split()))

        Assertions.assertEquals("\$68 1 2 3 4 5", commandsProcessor.process("show".split()))

        Assertions.assertEquals("\$128 2 4 6 4 10", commandsProcessor.process("put 1 2 3 0 5".split()))
    }

    @Test
    void testTake() {
        commandsProcessor.process("put 1 2 3 4 5".split())
        commandsProcessor.process("put 1 2 3 0 5".split())

        Assertions.assertEquals("\$43 1 0 3 4 0", commandsProcessor.process("take 1 4 3 0 10".split()))
    }

    @Test
    void testChange_good() {
        commandsProcessor.process("put 0 0 1 4 0".split())

        Assertions.assertEquals(" 0 0 1 3 0", commandsProcessor.process("change 11".split()))
    }

    @Test
    void testChange_13cash_8change_good() {
        commandsProcessor.process("put 0 0 2 1 1".split())

        Assertions.assertEquals("\$13 0 0 2 1 1", commandsProcessor.process("show".split()))
        Assertions.assertEquals(" 0 0 1 1 1", commandsProcessor.process("change 8".split()))
    }

    @Test
    void testChange_13cash_8change_bad() {
        commandsProcessor.process("put 0 1 0 1 1".split())

        Assertions.assertEquals("\$13 0 1 0 1 1", commandsProcessor.process("show".split()))
        Assertions.assertEquals("sorry", commandsProcessor.process("change 8".split()))
    }


}
