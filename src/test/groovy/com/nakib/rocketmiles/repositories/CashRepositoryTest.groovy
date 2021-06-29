package com.nakib.rocketmiles.repositories

import com.nakib.rocketmiles.constants.Bill
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CashRepositoryTest {
    CashRepository cashRepository

    @BeforeEach
    void setUp() {
        cashRepository = new CashRepository()
    }

    @Test
    void testInitial()  {
        Assertions.assertTrue(cashRepository.isEmpty(Bill.SINGLES))
        Assertions.assertTrue(cashRepository.isEmpty(Bill.DOUBLES))
        Assertions.assertTrue(cashRepository.isEmpty(Bill.TENNERS))
        Assertions.assertTrue(cashRepository.isEmpty(Bill.FIVERS))
        Assertions.assertTrue(cashRepository.isEmpty(Bill.TWENTIES))
    }

    @Test
    void testAdd() {
        cashRepository.add(Bill.TWENTIES, 3)

        Assertions.assertEquals(60, cashRepository.getBillValue(Bill.TWENTIES))
        Assertions.assertEquals(3, cashRepository.getBillCount(Bill.TWENTIES))
    }

    @Test
    void testSubtract() {
        cashRepository.add(Bill.FIVERS, 3)
        cashRepository.subtract(Bill.FIVERS, 2)

        Assertions.assertEquals(5, cashRepository.getBillValue(Bill.FIVERS))
        Assertions.assertEquals(1, cashRepository.getBillCount(Bill.FIVERS))
    }

    @Test
    void testTotal()    {
        cashRepository.add(Bill.SINGLES, 5)
        cashRepository.add(Bill.DOUBLES, 4)
        cashRepository.add(Bill.FIVERS, 3)
        cashRepository.add(Bill.TENNERS, 2)
        cashRepository.add(Bill.TWENTIES, 1)

        Assertions.assertEquals(68, cashRepository.getTotal())
    }
}
