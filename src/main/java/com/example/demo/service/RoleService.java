package com.example.demo.service;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest request){
        Role role = RoleMapper.RoleReqMapRole(request);

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);
        return RoleMapper.RoleMapRoleRes(role);
    }

    public List<RoleResponse> getAllRole(){
        var roles = roleRepository.findAll();
        return roles.stream().map(RoleMapper::RoleMapRoleRes).toList();
    }

    public void deleteRole(String role){
        roleRepository.deleteById(role);
    }
}
