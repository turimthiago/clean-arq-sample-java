package com.clean.sample.cleanarqsample.infra.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.infra.entities.UserEntity;

@Repository
public class UserMemoryRepository implements UserRepository{
	private List<UserEntity> users = new ArrayList<UserEntity>();

	@Override
	public UserModel save(UserModel user) {
		UserEntity entity = new UserEntity();
		entity.setId(1l);
		entity.setName(user.getName());
		entity.setPassword(user.getPassword());
		
		user.setId(entity.getId());
		
		return user;
	}

}
