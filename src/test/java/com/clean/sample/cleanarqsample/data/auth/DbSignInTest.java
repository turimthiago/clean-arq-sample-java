package com.clean.sample.cleanarqsample.data.auth;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import com.clean.sample.cleanarqsample.data.protocols.TokenManager;
import com.clean.sample.cleanarqsample.data.usecases.signin.DbSignIn;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.auth.SignInRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;

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

	
	@Test
	public void shouldThrowIfTokenManagerThrows() {
		SignInRequest request = new SignInRequest();
		
		doThrow(RuntimeException.class).when(tokenManager).signin(request.userName);

		assertThrows(RuntimeException.class, () -> {
			sut.sigin(request);
		});
	}

}
