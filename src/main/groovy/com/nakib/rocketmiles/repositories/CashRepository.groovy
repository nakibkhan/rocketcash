package com.nakib.rocketmiles.repositories

import com.nakib.rocketmiles.constants.Bill

class CashRepository {
    private final HashMap<Bill, Integer> cash = [:]

    CashRepository()    {
        cash[Bill.SINGLES] = 0
        cash[Bill.DOUBLES] = 0
        cash[Bill.FIVERS] = 0
        cash[Bill.TENNERS] = 0
        cash[Bill.TWENTIES] = 0
    }

    void add(Bill bill, int tuple) {
        cash[bill] += bill.value *tuple
    }

    void subtract(Bill bill, int tuple) {
        cash[bill] -= bill.value *tuple
    }

    Integer getTotal()  {
        cash.collect {it.value}.sum() as Integer
    }

    Integer getBillCount(Bill bill)   {
        cash[bill] / bill.value
    }

    Integer getBillValue(Bill bill)   {
        cash[bill]
    }

    Boolean isEmpty(Bill bill)   {
        getBillValue(bill) == 0
    }

    Set<Bill> getAllBills()    {
        cash.keySet().sort  {
            it.getInputArgs()
        }
    }

}
