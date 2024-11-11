package com.mavene.expensetracker.mapper;

import com.mavene.expensetracker.dto.UserDto;
import com.mavene.expensetracker.entity.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User toUser (UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword()
        );
    }
}
