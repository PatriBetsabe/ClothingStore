package com.lamadrid.store.persistence;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.User;

public interface HelperUserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);

}
