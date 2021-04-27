package com.clean.sample.cleanarqsample.data.usecases.user;

import javax.inject.Inject;
import javax.inject.Named;

import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;
import com.clean.sample.cleanarqsample.presenter.user.CreatedUserPresenter;

@Named
public class DbCreateUser implements CreateUser{
	
	@Inject
	private UserRepository repository;
	@Inject
	private CreatedUserPresenter presenter;

	public CreateUserResponse create(CreateUserRequest request) {
		UserModel newUser = new UserModel();
		newUser.setEmail(request.email);
		newUser.setPassword(request.password);

		UserModel user = this.repository.save(newUser);

		CreateUserResponse response = new CreateUserResponse();
		response.id = user.getId();
		response.name = user.getEmail();
		
		this.presenter.present(response);

		return response;
	}
}
