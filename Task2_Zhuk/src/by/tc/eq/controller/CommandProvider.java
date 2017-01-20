package by.tc.eq.controller;

import java.util.HashMap;
import java.util.Map;

import by.tc.eq.controller.command.Command;
import by.tc.eq.controller.command.CommandName;
import by.tc.eq.controller.command.impl.AddEquipment;
import by.tc.eq.controller.command.impl.DeleteAccount;
import by.tc.eq.controller.command.impl.DeleteEquipment;
import by.tc.eq.controller.command.impl.Registration;
import by.tc.eq.controller.command.impl.RentEquipment;
import by.tc.eq.controller.command.impl.SignIn;
import by.tc.eq.controller.exception.CommandNotFoundException;

public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {// класс public , а почему единственный конструктор без параметра?
		repository.put(CommandName.ADD_EQPNT, new AddEquipment());
		repository.put(CommandName.DELETE_ACCOUNT, new DeleteAccount());
		repository.put(CommandName.DELETE_EQPNT, new DeleteEquipment());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.RENT_EQPNT, new RentEquipment());
		repository.put(CommandName.SIGN_IN, new SignIn());
	}

	Command getCommand(CommandName name) throws CommandNotFoundException {
		Command command = null;
		try {
			command = repository.get(name);
		} catch (IllegalArgumentException | NullPointerException e) {// почему ты перехватываешь и гасишь NullPointerException?
			// ты же так маскируешь серьеьзные ошибки в коде
			throw new CommandNotFoundException();
		}
		return command;
	}

}
