package com.clean.sample.cleanarqsample.main.controllers.user;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Inject
	private CreateUser createUser;

	@PostMapping
	public ResponseEntity<CreateUserResponse> post(@Valid @RequestBody NewUserRequest request) {
		CreateUserRequest createRequest = new CreateUserRequest();
		createRequest.name = request.name;
		createRequest.password = request.name;

		CreateUserResponse response = this.createUser.create(createRequest);

		return ResponseEntity.ok(response);
	}

}
