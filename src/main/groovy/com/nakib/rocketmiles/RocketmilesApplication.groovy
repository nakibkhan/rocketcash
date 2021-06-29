package com.nakib.rocketmiles

import com.nakib.rocketmiles.repositories.CashRepository
import com.nakib.rocketmiles.service.CommandsProcessor
import com.nakib.rocketmiles.validators.CommandsValidator
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RocketmilesApplication implements CommandLineRunner {

	static void main(String[] args) {
		SpringApplication.run(RocketmilesApplication, args)
	}

	@Override
	void run(String... args) throws Exception {
		CashRepository cashRepository = new CashRepository()
		CommandsProcessor processor = new CommandsProcessor(
				cashRepository: cashRepository
		)

		Scanner scanner = new Scanner(System.in)
		while (scanner.hasNext())	{
			String input = scanner.next()

			if(CommandsValidator.isValid(input))	{
				processor.process(input.split())
			} else {
				System.out.println("Invalid Command")
			}
		}
	}
}
