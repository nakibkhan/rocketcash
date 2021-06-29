package com.nakib.rocketmiles.validators

import com.nakib.rocketmiles.constants.Commands

class CommandsValidator {

    static boolean isValid(String input)   {
        String [] inputs = input.split()
        if(inputs[0] == Commands.EXIT)  {
            return true
        }

        if(inputs[0] == Commands.SHOW)  {
            return true
        }

        if(inputs[0] == Commands.TAKE || inputs[0] == Commands.PUT) {
            if(inputs.size() == 6)  {
                for(int i = 1; i< inputs.size(); i++)   {
                    if(! inputs[i].isNumber())  {
                        return false
                    }
                }

                return true
            }
        }

        if(inputs[0] == Commands.CHANGE)  {
            if(inputs.size() == 2 && inputs[1].isNumber())  {
                return true
            }
        }

        false
    }
}
