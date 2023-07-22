package com.minuser.MinUser.repositories;

import com.minuser.MinUser.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>,
                                        JpaSpecificationExecutor<UserEntity> {}
