package com.example.demo.controller;

import com.example.demo.dto.request.ApiResponse;
import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setCode(200);
        userApiResponse.setMessage("User create successfully");
        userApiResponse.setResult(userService.createUser(userCreationRequest));
        return userApiResponse;
    }

    @GetMapping
    public List<UserResponse> getUsers(){
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        log.info("Username: {}",authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getDetailUser(@PathVariable String userId){
        return userService.getFirstUser(userId);
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo(){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        UserResponse userResponse = userService.getMyInfo();

        apiResponse.setCode(200);
        apiResponse.setMessage("Detail User Login successfully");
        apiResponse.setResult(userResponse);
        return apiResponse;
    }


    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
