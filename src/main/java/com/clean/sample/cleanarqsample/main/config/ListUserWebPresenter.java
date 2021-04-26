package com.clean.sample.cleanarqsample.main.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.clean.sample.cleanarqsample.domain.models.UserModel;
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
		this.viewModel = new ArrayList<UserViewModel>(
				responseModel.users.stream().map(this::convert).collect(Collectors.toList()));
	}

	private UserViewModel convert(UserModel model) {
		UserViewModel view = new UserViewModel();
		view.id = model.getId();
		view.name = model.getName();

		return view;
	}

}
