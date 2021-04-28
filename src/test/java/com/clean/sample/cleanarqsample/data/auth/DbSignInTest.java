package com.clean.sample.cleanarqsample.data.auth;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;

import com.clean.sample.cleanarqsample.data.protocols.TokenManager;
import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.data.usecases.signin.DbSignIn;
import com.clean.sample.cleanarqsample.data.usecases.user.DbCreateUser;
import com.clean.sample.cleanarqsample.domain.databuilder.UserModelDataBuilder;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.auth.SignInRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;
import com.clean.sample.cleanarqsample.presenter.user.CreatedUserPresenter;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DbSignInTest {
	@InjectMocks
	private DbSignIn sut = new DbSignIn();
	@Mock
	private TokenManager tokenManager;

	@Test
	public void shouldTokenManagerWithCorrectValues() {
		SignInRequest request = new SignInRequest();

		when(tokenManager.signin(request.userName)).thenReturn("valid_token");

		sut.sigin(request);
		
		verify(tokenManager).signin(request.userName);
	}


}
