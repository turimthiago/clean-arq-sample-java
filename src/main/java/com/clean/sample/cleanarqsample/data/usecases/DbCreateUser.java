package com.clean.sample.cleanarqsample.data.usecases;

import javax.inject.Inject;
import javax.inject.Named;

import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;

@Named
public class DbCreateUser implements CreateUser{
	
	@Inject
	private UserRepository repository;

	public CreateUserResponse create(CreateUserRequest request) {
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
