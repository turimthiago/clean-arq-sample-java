package com.clean.sample.cleanarqsample.infra.db;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import com.clean.sample.cleanarqsample.data.protocols.UserRepository;
import com.clean.sample.cleanarqsample.domain.models.UserModel;
import com.clean.sample.cleanarqsample.infra.entities.UserEntity;

@Repository
public class MemoryUserRepository implements UserRepository{
	private List<UserEntity> users = new ArrayList<UserEntity>();

	@Override
	public UserModel save(UserModel user) {
		UserEntity entity = new UserEntity();
		entity.setId(1l);
		entity.setName(user.getName());
		entity.setPassword(user.getPassword());
		
		user.setId(Long.valueOf(this.users.size()+1));
		
		this.users.add(entity);
		
		return user;
	}

	@Override
	public List<UserModel> findAll() {
		System.out.println("teste"+this.users.size());
		return this.convertList(this.users);
	}
	
	private UserModel convert(UserEntity entity) {
		UserModel model = new UserModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setPassword(entity.getPassword());
		
		return model;
	}

	private List<UserModel> convertList(List<UserEntity> entities) {
		return entities.stream().map(this::convert).collect(Collectors.toList());
	}
}
