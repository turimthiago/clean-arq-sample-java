package com.clean.sample.cleanarqsample.main.presenters;

import org.springframework.stereotype.Component;
import com.clean.sample.cleanarqsample.domain.usescases.user.CreateUserResponse;
import com.clean.sample.cleanarqsample.presenter.user.CreatedUserPresenter;
import com.clean.sample.cleanarqsample.presenter.user.CreatedUserViewModel;

@Component
public class CreatedUserWebPresenter implements CreatedUserPresenter {
	private CreatedUserViewModel viewModel;

	@Override
	public CreatedUserViewModel getViewModel() {
		return this.viewModel;
	}

	@Override
	public void present(CreateUserResponse responseModel) {
		this.viewModel = new CreatedUserViewModel();
		this.viewModel.id = responseModel.id;
	}

}
