package com.example.demo.mapper;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.entity.Permission;

public class PermissionMapper {
    public static Permission ToPermissionReqMapPermission(PermissionRequest permissionRequest){
        Permission permission = new Permission();
        permission.setName(permissionRequest.getName());
        permission.setDescription(permissionRequest.getDescription());

        return permission;
    }

    public static PermissionResponse toPermissionMapPermissionRes(Permission permission){
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setName(permission.getName());
        permissionResponse.setDescription(permission.getDescription());

        return permissionResponse;
    }
}
