package com.example.demo.service;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.entity.Permission;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {

    UserRepository userRepository;

    PermissionRepository permissionRepository;
    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = PermissionMapper.ToPermissionReqMapPermission(permissionRequest);

        permission = permissionRepository.save(permission);

        return PermissionMapper.toPermissionMapPermissionRes(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissionsList = permissionRepository.findAll();
        return permissionsList.stream()
                .map(PermissionMapper::toPermissionMapPermissionRes)
                .toList();
    }
    public void deletePermission(String permission){
        permissionRepository.deleteById(permission);
    }
}
