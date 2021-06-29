package com.nakib.rocketmiles.service

import com.nakib.rocketmiles.constants.Bill
import com.nakib.rocketmiles.constants.Commands
import com.nakib.rocketmiles.repositories.CashRepository

class CommandsProcessor {
    CashRepository cashRepository

    String process(String[] args) {

        if(args[0] == Commands.SHOW)   {
            return printStatus()
        } else if(args[0] == Commands.PUT)  {
            return put(args)
        } else if(args[0] == Commands.TAKE){
            return take(args)
        } else if(args[0] == Commands.CHANGE)   {
            return change(args[1])
        }
    }

    String put(String[] args)  {
        for(int i = 1; i< args.length; i++) {
            cashRepository.add(Bill.getByArgIndex(i), args[i].toInteger() )
        }

        return printStatus()
    }

    String take(String [] args) {
        for(int i = 1; i< args.length; i++) {
            cashRepository.subtract(Bill.getByArgIndex(i), args[i].toInteger() )
        }

        return printStatus()
    }

    String change(String value)   {
        int changeValue = value.toInteger()
        def changeNotes = [:]

        if(!cashRepository.isEmpty(Bill.TWENTIES))    {
            changeNotes[Bill.TWENTIES] = determineChange(Bill.TWENTIES, changeValue)
            changeValue -= changeNotes[Bill.TWENTIES] * Bill.TWENTIES.value
        }

        if(!cashRepository.isEmpty(Bill.TENNERS))    {
            changeNotes[Bill.TENNERS] = determineChange(Bill.TENNERS, changeValue)
            changeValue -= changeNotes[Bill.TENNERS] * Bill.TENNERS.value
        }

        if(!cashRepository.isEmpty(Bill.FIVERS))    {
            changeNotes[Bill.FIVERS] = determineChange(Bill.FIVERS, changeValue)
            changeValue -= changeNotes[Bill.FIVERS] * Bill.FIVERS.value
        }

        if(!cashRepository.isEmpty(Bill.DOUBLES))    {
            changeNotes[Bill.DOUBLES] = determineChange(Bill.DOUBLES, changeValue)
            changeValue -= changeNotes[Bill.DOUBLES] * Bill.DOUBLES.value
        }

        if(!cashRepository.isEmpty(Bill.SINGLES))    {
            changeNotes[Bill.SINGLES] = determineChange(Bill.SINGLES, changeValue)
            changeValue -= changeNotes[Bill.SINGLES] * Bill.SINGLES.value
        }

        StringBuilder sb = new StringBuilder()
        if(changeValue == 0)    {
            cashRepository.allBills.each {
                changeNotes[it]? sb.append(" ").append(changeNotes[it]) : sb.append(" ").append("0")
                if(changeNotes[it] != null) cashRepository.subtract(it as Bill, changeNotes[it] as int)
            }
        } else {
            sb.append("sorry")
        }

        sb.toString()

    }

    String printStatus()  {
        StringBuilder sb = new StringBuilder()
        sb.append("\$$cashRepository.total")

        cashRepository.allBills.each {
            sb.append(" ").append(cashRepository.getBillCount(it))
        }

        sb.toString()
    }

    private int determineChange(Bill bill, int changeValue) {
        changeValue.intdiv(bill.value) >=  cashRepository.getBillCount(bill) ? cashRepository.getBillCount(bill): changeValue.intdiv(bill.value)
    }
}
