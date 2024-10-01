package com.example.demo.mapper;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;

public class UserMapper {
    public static User toCreateUser(UserCreationRequest userCreationRequest){
        User user = new User();
        user.setUserName(userCreationRequest.getUserName());
        user.setPassword(userCreationRequest.getPassword());
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setDob(userCreationRequest.getDob());

        return user;
    }

    public static UserResponse toUserMapUserResponse(User user){
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setRoles(user.getRoles());
        userResponse.setDob(user.getDob());

        return userResponse;
    }

    public static User toUpdateUser(User user, UserUpdateRequest userUpdateRequest){
        user.setPassword(userUpdateRequest.getPassword());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setDob(userUpdateRequest.getDob());

        return user;
    }

}
