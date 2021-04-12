package com.clean.sample.cleanarqsample.domain.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
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

import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.data.usecases.DbCreateUser;
import com.clean.sample.cleanarqsample.domain.databuilder.UserModelDataBuilder;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreateUserTest {
	@InjectMocks
	private CreateUser sut = new DbCreateUser();
	@Mock
	private UserRepository repository;

	@Test
	public void shouldThrowIfRespositoryThrows() {
		CreateUserRequest request = new CreateUserRequest();
		request.name = "Any name";
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
		request.name = userModel.getName();
		request.password = userModel.getPassword();

		when(repository.save(any(UserModel.class))).thenReturn(new UserModelDataBuilder().aUser().build());

		sut.create(request);

		verify(repository).save(argThat((userArgument) -> {
			return userArgument.getName().equals(userModel.getName());
		}));

	}

	@Test
	public void shouldSaveUser() {
		CreateUserRequest request = new CreateUserRequest();
		request.name = "Any name";
		request.password = "any_password";

		when(repository.save(any(UserModel.class))).thenReturn(new UserModelDataBuilder().aUser().build());

		CreateUserResponse response = sut.create(request);

		assertNotNull(response.id);

	}
}
