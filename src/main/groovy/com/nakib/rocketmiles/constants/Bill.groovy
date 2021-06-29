package com.nakib.rocketmiles.constants

enum Bill {
     TWENTIES(1, 20), TENNERS(2, 10), FIVERS(3, 5), DOUBLES(4, 2), SINGLES(5, 1)


    private int dollarVal
    private int argIndex

    Bill(int argIndex, int dollarVal)    {
        this.dollarVal = dollarVal
        this.argIndex = argIndex
    }

    int getValue()    {
        dollarVal
    }

    int getInputArgs()    {
        argIndex
    }

    static Bill getByArgIndex(int index)   {
        values().find{
            it.argIndex == index
        }
    }
}