package com.nakib.rocketmiles.controllers

import com.nakib.rocketmiles.service.CommandsProcessor
import com.nakib.rocketmiles.validators.CommandsValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cashier")
class CashierController {
    @Autowired CommandsProcessor processor

    @GetMapping
    String welcome()    {
        "Hello to cashier application"
    }


    @PostMapping("/processCommand")
    String addFunds(@RequestBody String[] input)    {
        String inputAll = input.join(" ")
        if(!CommandsValidator.isValid(inputAll)) return "Invalid input"

        processor.process(inputAll.split())
    }

}
