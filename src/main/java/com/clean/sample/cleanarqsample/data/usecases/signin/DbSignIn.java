package com.clean.sample.cleanarqsample.data.usecases.signin;

import javax.inject.Inject;

import com.clean.sample.cleanarqsample.data.protocols.TokenManager;
import com.clean.sample.cleanarqsample.domain.usescases.auth.SignIn;
import com.clean.sample.cleanarqsample.domain.usescases.auth.SignInRequest;
import com.clean.sample.cleanarqsample.domain.usescases.auth.SignInResponse;

public class DbSignIn implements SignIn {
	@Inject
	private TokenManager tokenManager;

	@Override
	public SignInResponse sigin(SignInRequest credentials) {
		String token = this.tokenManager.signin(credentials.userName);
		
		SignInResponse response = new SignInResponse();
		response.token = token;
		
		return response;
	}

}
