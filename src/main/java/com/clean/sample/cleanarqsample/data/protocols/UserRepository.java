package com.clean.sample.cleanarqsample.data.protocols;

import com.clean.sample.cleanarqsample.domain.models.UserModel;

public interface UserRepository {
	public UserModel save(UserModel user);
}
