package com.clean.sample.cleanarqsample.main.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.clean.sample.cleanarqsample.domain.usescases.user.ListUsersResponse;
import com.clean.sample.cleanarqsample.presenter.user.ListUserPresenter;
import com.clean.sample.cleanarqsample.presenter.user.UserViewModel;

@Component
public class ListUserWebPresenter implements ListUserPresenter {
	private List<UserViewModel> viewModel;

	@Override
	public List<UserViewModel> getViewModel() {
		return this.viewModel;
	}

	@Override
	public void present(ListUsersResponse responseModel) {
		this.viewModel = new ArrayList<UserViewModel>();
		
	}

}
