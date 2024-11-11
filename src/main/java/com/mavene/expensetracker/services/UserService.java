package com.mavene.expensetracker.services;

import com.mavene.expensetracker.dto.UserDto;
import com.mavene.expensetracker.exception.EtAuthException;

public interface UserService {

    UserDto validateUser(String email, String password) throws EtAuthException;

//    UserDto registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
    UserDto registerUser(UserDto userDto) throws EtAuthException;

}
