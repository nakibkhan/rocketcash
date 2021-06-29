package com.nakib.rocketmiles

import com.nakib.rocketmiles.constants.Commands
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

		System.out.println("ready")
		Scanner scanner = new Scanner(System.in)
		while (scanner.hasNextLine())	{
			String input = scanner.nextLine()

			if(CommandsValidator.isValid(input))	{
				String[] inputs = input.split()

				if(inputs[0] == Commands.EXIT)	break

				System.out.println(processor.process(inputs))
			} else {
				System.out.println("Invalid Command")
			}
		}

		System.out.println("Bye")
		System.exit(0)
	}
}
