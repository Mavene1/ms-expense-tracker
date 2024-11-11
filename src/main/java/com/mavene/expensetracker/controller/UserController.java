package com.mavene.expensetracker.controller;

import com.mavene.expensetracker.dto.UserDto;
import com.mavene.expensetracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.registerUser(userDto);
        System.out.println("Creating and saving user.....");
//        Map<String, String> map = new HashMap<>();
//        map.put("message", "User created: " + savedUser.getEmail() );
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<UserDto> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        UserDto loggedInUser = userService.validateUser(email, password);
        System.out.println("Logging in user.....");
        return new ResponseEntity<>(loggedInUser, HttpStatus.CREATED);
    }


//    @PostMapping("/createUser")
//    public String createUser(@RequestBody Map<String, Object> userMap) {
//        String firstName = (String) userMap.get("firstName");
//        String lastName = (String) userMap.get("lastName");
//        String email = (String) userMap.get("email");
//        String password = (String) userMap.get("password");
//        return "User created: " + firstName + " " + lastName + " " + email + " " + password;
//    }
}

