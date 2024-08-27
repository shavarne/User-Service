package com.project.user.service.UserService.repositories;

import com.project.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByNameAndEmail(String name, String email);
}
