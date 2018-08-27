package com.project.UserBackendService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserModel, Integer>{
	public UserModel findByEmail(String email);
}
