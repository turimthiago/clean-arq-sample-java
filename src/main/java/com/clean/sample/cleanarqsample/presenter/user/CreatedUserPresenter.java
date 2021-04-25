package com.clean.sample.cleanarqsample.presenter.user;

import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;

public interface CreatedUserPresenter {
	CreatedUserViewModel getViewModel();

	void present(CreateUserResponse responseModel);
}
