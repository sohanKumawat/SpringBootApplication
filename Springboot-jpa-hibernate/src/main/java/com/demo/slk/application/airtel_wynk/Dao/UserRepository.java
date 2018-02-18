package com.demo.slk.application.airtel_wynk.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.slk.application.airtel_wynk.pojo.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, String>{
	Optional<User> findByUserId(String userId);
}
