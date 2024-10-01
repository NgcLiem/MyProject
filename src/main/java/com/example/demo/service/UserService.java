package com.example.demo.service;

import com.example.demo.dto.request.UserCreationRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request){

        if(userRepository.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = UserMapper.toCreateUser(request);
        PasswordEncoder hashPass = new BCryptPasswordEncoder(10);
        user.setPassword(hashPass.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        //user.setRoles(roles);

        return UserMapper.toUserMapUserResponse(userRepository.save(user));
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('UPDATE_DATA')")
    public List<UserResponse> getUsers(){
        List<User> listUser = userRepository.findAll();

        log.info("In method get Users");
        return listUser.stream().map(UserMapper::toUserMapUserResponse).toList();
    }

    @PreAuthorize("hasRole('USER')")
    public UserResponse getFirstUser(String id){
        log.info("In method get user by Id");
        return UserMapper.toUserMapUserResponse(userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();
//        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByUserName(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return UserMapper.toUserMapUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest){
        User user = UserMapper.toUpdateUser(
                userRepository.findById(userId).orElseThrow(
                        () -> new AppException(ErrorCode.USER_NOT_EXISTED)), userUpdateRequest);

        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));

        var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);

        return UserMapper.toUserMapUserResponse(user);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
