package com.clean.sample.cleanarqsample.main.controllers.user;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsers;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersRequest;
import com.clean.sample.cleanarqsample.presenter.user.CreatedUserPresenter;
import com.clean.sample.cleanarqsample.presenter.user.CreatedUserViewModel;
import com.clean.sample.cleanarqsample.presenter.user.ListUserPresenter;
import com.clean.sample.cleanarqsample.presenter.user.UserViewModel;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Inject
	private CreateUser createUser;
	@Inject
	private CreatedUserPresenter createdUserPresenter;
	@Inject
	private ListUsers listUsers;
	@Inject
	private ListUserPresenter listUserPresenter;

	@PostMapping
	public ResponseEntity<CreatedUserViewModel> post(@Valid @RequestBody NewUserRequest request) {
		CreateUserRequest createRequest = new CreateUserRequest();
		createRequest.email = request.email;
		createRequest.password = request.password;

		this.createUser.create(createRequest);

		return ResponseEntity.ok(createdUserPresenter.getViewModel());
	}

	@GetMapping
	public ResponseEntity<List<UserViewModel>> get() {
		this.listUsers.list(new ListUsersRequest());

		return ResponseEntity.ok(listUserPresenter.getViewModel());
	}

}
