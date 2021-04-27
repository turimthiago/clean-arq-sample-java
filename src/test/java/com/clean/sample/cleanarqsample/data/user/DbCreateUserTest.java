package com.clean.sample.cleanarqsample.data.user;

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
import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.data.usecases.DbCreateUser;
import com.clean.sample.cleanarqsample.domain.databuilder.UserModelDataBuilder;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;
import com.clean.sample.cleanarqsample.presenter.user.CreatedUserPresenter;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DbCreateUserTest {
	@InjectMocks
	private CreateUser sut = new DbCreateUser();
	@Mock
	private UserRepository repository;
	@Mock
	private CreatedUserPresenter presenter;
	@Captor
	private ArgumentCaptor<UserModel> captor;

	@Test
	public void shouldThrowIfRespositoryThrows() {
		CreateUserRequest request = new CreateUserRequest();
		request.email = "any@mail.com.br";
		request.password = "any_password";

		doThrow(RuntimeException.class).when(repository).save(any(UserModel.class));

		assertThrows(RuntimeException.class, () -> {
			sut.create(request);
		});
	}

	@Test
	public void shouldRepositoryWithCorrectValues() {
		UserModel userModel = new UserModelDataBuilder().aUser().build();

		CreateUserRequest request = new CreateUserRequest();
		request.email = userModel.getEmail();
		request.password = userModel.getPassword();

		when(repository.save(any(UserModel.class))).thenReturn(userModel);

		sut.create(request);
		
		verify(repository, times(1)).save(captor.capture());
		
		assertNull(captor.getValue().getId());
		assertEquals(captor.getValue().getEmail(), userModel.getEmail());
		assertEquals(captor.getValue().getPassword(), userModel.getPassword());
	}

	@Test
	public void shouldSaveUser() {
		CreateUserRequest request = new CreateUserRequest();
		request.email = "any@mail.com.br";
		request.password = "any_password";

		when(repository.save(any(UserModel.class))).thenReturn(new UserModelDataBuilder().aUser().build());

		CreateUserResponse response = sut.create(request);

		assertNotNull(response.id);

	}
}
