package com.kumar.gamesstore.mapper;

import com.kumar.gamesstore.dto.UserDto;
import com.kumar.gamesstore.modals.User;

public class UserMapper {

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
