package com.mavene.expensetracker.services.impl;

import com.mavene.expensetracker.dto.UserDto;
import com.mavene.expensetracker.entity.User;
import com.mavene.expensetracker.exception.EtAuthException;
import com.mavene.expensetracker.mapper.UserMapper;
import com.mavene.expensetracker.repository.UserRepository;
import com.mavene.expensetracker.services.UserService;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) throws EtAuthException {
        String email = userDto.getEmail();

        if (email == null || email.isEmpty()) {
            throw new EtAuthException("Email is required");
        }
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        email = email.toLowerCase().trim();
        if (!pattern.matcher(email).matches()) {
            throw new EtAuthException("Invalid email format");
        }
        // Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EtAuthException("Email is already in use: " + userDto.getEmail());
        }

        User user = UserMapper.toUser(userDto);
        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
//        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto validateUser(UserDto userDto) throws EtAuthException {
        System.out.println("Validating user with email: " + userDto.getEmail() + " and password: " + userDto.getPassword());
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isEmpty() || !BCrypt.checkpw(userDto.getPassword(), user.get().getPassword())) {
            throw new EtAuthException("Invalid email or password");
        }
        return UserMapper.toUserDto(user.get());

//        return userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword())
//                .map(UserMapper::toUserDto)
//                .orElseThrow(() -> new EtAuthException("Invalid email or password"));
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

