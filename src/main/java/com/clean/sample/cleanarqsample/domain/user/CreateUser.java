package com.clean.sample.cleanarqsample.domain.user;

import javax.inject.Inject;
import javax.inject.Named;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.protocols.repositories.UserRepository;

@Named
public class CreateUser {
	
	@Inject
	private UserRepository repository;

	public CreateUserResponse create(CreateUserRequest request) {
		System.out.println("CreateUser usecase -> create");
		UserModel newUser = new UserModel();
		newUser.setName(request.name);
		newUser.setPassword(request.password);

		UserModel user = this.repository.save(newUser);

		CreateUserResponse response = new CreateUserResponse();
		response.id = user.getId();
		response.name = user.getName();

		return response;
	}
}
