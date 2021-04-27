package com.clean.sample.cleanarqsample.data.usecases.user;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsers;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersRequest;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersResponse;
import com.clean.sample.cleanarqsample.presenter.user.ListUserPresenter;

@Component
public class DbListUser implements ListUsers {
	@Inject
	private UserRepository userRepository;
	@Inject
	private ListUserPresenter presenter;

	@Override
	public ListUsersResponse list(ListUsersRequest request) {
		List<UserModel> users = this.userRepository.findAll();
		
		ListUsersResponse response = new ListUsersResponse();
		response.users = users;
		
		this.presenter.present(response);
		
		return response;
	}

}
