package com.clean.sample.cleanarqsample.domain.databuilder;

import com.clean.sample.cleanarqsample.domain.models.UserModel;

public class UserModelDataBuilder {
	private UserModel userModel;

	public UserModelDataBuilder() {
		
	}
	
	public UserModelDataBuilder aUser() {
		this.userModel = new UserModel();
		this.userModel.setId(1l);
		this.userModel.setName("Any Name");
		this.userModel.setPassword("any_password");
		
		return this;
	}
	
	public UserModel build() {
		return this.userModel;
	}

}
