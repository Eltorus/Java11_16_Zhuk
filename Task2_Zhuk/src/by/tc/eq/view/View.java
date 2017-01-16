package by.tc.eq.view;

import by.tc.eq.bean.Response;
import by.tc.eq.bean.entity.Equipment;
import by.tc.eq.bean.entity.Good;
import by.tc.eq.bean.entity.User;
import by.tc.eq.bean.requestimpl.AddEquipmentRequest;
import by.tc.eq.bean.requestimpl.DeleteAccountRequest;
import by.tc.eq.bean.requestimpl.DeleteEquipmentRequest;
import by.tc.eq.bean.requestimpl.RegistrationRequest;
import by.tc.eq.bean.requestimpl.RentEquipmentRequest;
import by.tc.eq.bean.requestimpl.SignInRequest;
import by.tc.eq.controller.Controller;
import by.tc.eq.controller.command.CommandName;

public class View {
	public static void main(String[] args) {
		Controller controller = new Controller();

		// Регистрация пользователя
		RegistrationRequest request = new RegistrationRequest();
		request.setCommand(CommandName.REGISTRATION);
		User user = new User();
		user.setName("Vasya");
		user.setSurname("Petrov");
		user.setEmail("1234");
		user.setPassword("111");
		request.setUser(user);
		Response response = controller.executeTask(request);
		if (!response.isErrorStatus()) {
			System.out.println(response.getMessage());
		} else {
			System.out.println(response.getErrorMessage());
		}

		// Вход пользователя в систему
		SignInRequest signrequest = new SignInRequest();
		signrequest.setCommand(CommandName.SIGN_IN);
		signrequest.setEmail(user.getEmail());
		signrequest.setPassword(user.getPassword());
		response = controller.executeTask(signrequest);
		if (!response.isErrorStatus()) {
			System.out.println(response.getMessage());
		} else {
			System.out.println(response.getErrorMessage());
		}

		// Взятие пользователем товара в прокат
		Good good = new Good();
		good.setTitle("lolo");
		RentEquipmentRequest rentrequest = new RentEquipmentRequest();
		rentrequest.setCommand(CommandName.RENT_EQPNT);
		rentrequest.setGood(good);
		rentrequest.setUser(user);
		response = controller.executeTask(rentrequest);
		if (!response.isErrorStatus()) {
			System.out.println(response.getMessage());
		} else {
			System.out.println(response.getErrorMessage());
		}

		// Добавление нового снаряжения
		Equipment eq = new Equipment();
		eq.setTitle("Ball");
		eq.setCategory("Cat1");
		eq.setAmount(5);
		eq.setPrice(5.25);
		AddEquipmentRequest addeqreq = new AddEquipmentRequest();
		addeqreq.setCommand(CommandName.ADD_EQPNT);
		addeqreq.setEquipment(eq);
		response = controller.executeTask(addeqreq);
		if (!response.isErrorStatus()) {
			System.out.println(response.getMessage());
		} else {
			System.out.println(response.getErrorMessage());
		}

		// Удаление снаряжения
		DeleteEquipmentRequest deleteeq = new DeleteEquipmentRequest();
		deleteeq.setCommand(CommandName.DELETE_EQPNT);
		deleteeq.setEquipment(eq);
		response = controller.executeTask(deleteeq);
		if (!response.isErrorStatus()) {
			System.out.println(response.getMessage());
		} else {
			System.out.println(response.getErrorMessage());
		}

		// Удаление аккаунта пользователя
		DeleteAccountRequest delrequest = new DeleteAccountRequest();
		delrequest.setCommand(CommandName.DELETE_ACCOUNT);
		delrequest.setUser(user);
		response = controller.executeTask(delrequest);
		if (!response.isErrorStatus()) {
			System.out.println(response.getMessage());
		} else {
			System.out.println(response.getErrorMessage());
		}

	}
}
