package com.clean.sample.cleanarqsample.data.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.data.usecases.DbListUser;
import com.clean.sample.cleanarqsample.domain.databuilder.UserModelDataBuilder;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsers;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersResponse;
import com.clean.sample.cleanarqsample.presenter.user.ListUserPresenter;

@ExtendWith(MockitoExtension.class)
public class DbListUserTest {
	@InjectMocks
	private ListUsers sut = new DbListUser();
	@Mock
	private UserRepository repository;
	@Mock
	private ListUserPresenter presenter;

	@Test
	public void shouldThrowIfRespositoryThrows() {
		ListUsersRequest request = new ListUsersRequest();

		doThrow(RuntimeException.class).when(repository).findAll();

		assertThrows(RuntimeException.class, () -> {
			sut.list(request);
		});
	}

	@Test
	public void shouldRepositoryWithCorrectValues() {
		ListUsersRequest request = new ListUsersRequest();

		when(repository.findAll()).thenReturn(Arrays.asList(new UserModelDataBuilder().aUser().build()));

		sut.list(request);

		verify(repository, times(1)).findAll();
	}

	@Test
	public void shouldReturnListUsers() {
		ListUsersRequest request = new ListUsersRequest();

		when(repository.findAll()).thenReturn(Arrays.asList(new UserModelDataBuilder().aUser().build()));

		ListUsersResponse response = sut.list(request);

		assertNotNull(response);
		assertNotNull(response.users);
		assertEquals(1, response.users.size());
		assertTrue(response.users.stream().allMatch(u -> u instanceof UserModel));

	}

}
