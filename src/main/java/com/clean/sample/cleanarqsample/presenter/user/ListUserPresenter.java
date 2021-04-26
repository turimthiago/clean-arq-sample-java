package com.clean.sample.cleanarqsample.presenter.user;

import java.util.List;

import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersResponse;

public interface ListUserPresenter {
	List<UserViewModel> getViewModel();

	void present(ListUsersResponse responseModel);

}
