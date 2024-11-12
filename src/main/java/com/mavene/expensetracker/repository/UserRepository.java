package com.mavene.expensetracker.repository;

import com.mavene.expensetracker.dto.UserDto;
import com.mavene.expensetracker.entity.User;
import com.mavene.expensetracker.exception.EtAuthException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);// <2>

//    Optional<User> findByEmailAndPassword(String email, String password);
//
//    Integer createUser(String firstName, String lastName, String email, String password) throws EtAuthException;
//
//    UserDto findByEmailAndPassword(String email, String password) throws EtAuthException;
//
//    UserDto findByUserId(Integer userId);
//
//    Integer getCountByEmail(String email);
}
