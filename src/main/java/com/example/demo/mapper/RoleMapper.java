package com.example.demo.mapper;

import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.entity.Role;
import org.mapstruct.Mapper;

@Mapper
public class RoleMapper {
    public static Role RoleReqMapRole(RoleRequest roleRequest){
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());

        return role;
    }

    public static RoleResponse RoleMapRoleRes(Role role){
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setName(role.getName());
        roleResponse.setDescription(role.getDescription());
        roleResponse.setPermissions(roleResponse.getPermissions());

        return roleResponse;
    }
}
