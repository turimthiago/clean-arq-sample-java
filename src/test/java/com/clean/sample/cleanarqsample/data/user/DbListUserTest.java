package com.clean.sample.cleanarqsample.data.user;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.data.usecases.DbListUser;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsers;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersRequest;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DbListUserTest {
	@InjectMocks
	private ListUsers sut = new DbListUser();
	@Mock
	private UserRepository repository;

	@Test
	public void shouldThrowIfRespositoryThrows() {
		ListUsersRequest request = new ListUsersRequest();

		doThrow(RuntimeException.class).when(repository).findAll();

		assertThrows(RuntimeException.class, () -> {
			sut.list(request);
		});
	}

}
