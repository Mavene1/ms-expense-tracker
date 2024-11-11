package com.mavene.expensetracker.services.impl;

import com.mavene.expensetracker.dto.UserDto;
import com.mavene.expensetracker.entity.User;
import com.mavene.expensetracker.exception.EtAuthException;
import com.mavene.expensetracker.mapper.UserMapper;
import com.mavene.expensetracker.repository.UserRepository;
import com.mavene.expensetracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) throws EtAuthException {
        // Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EtAuthException("Email is already in use: " + userDto.getEmail());
        }

        User user = UserMapper.toUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto validateUser(String email, String password) throws EtAuthException {
        System.out.println("Validating user with email: " + email + " and password: " + password);
        return userRepository.findByEmailAndPassword(email, password)
                .map(UserMapper::toUserDto)
                .orElseThrow(() -> new EtAuthException("Invalid email or password"));
//        User user = userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new EtAuthException("Invalid credentials"));
//        return UserMapper.toUserDto(user);
    }
}


//    @Override
//    public UserDto registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
//        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
//        if (email!=null) email = email.toLowerCase().trim();
//        if (!pattern.matcher(email).matches()) {
//            throw new EtAuthException("Invalid email format");
//        }
//        Integer count = userRepository.getCountByEmail(email);
//        if (count > 0) {
//            throw new EtAuthException("User with email already exists");
//        }
//
//        Integer userId = userRepository.createUser(firstName, lastName, email, password);
//
//        return userRepository.findByUserId(userId);
//    }

