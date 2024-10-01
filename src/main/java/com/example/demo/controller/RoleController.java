package com.example.demo.controller;

import com.example.demo.dto.request.ApiResponse;
import com.example.demo.dto.request.RoleRequest;
import com.example.demo.dto.response.RoleResponse;
import com.example.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {

        ApiResponse<RoleResponse> apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setMessage("Role create successfully");
        apiResponse.setResult(roleService.createRole(roleRequest));

        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRoles() {

        ApiResponse<List<RoleResponse>> listApiResponse = new ApiResponse<>();

        listApiResponse.setCode(200);
        listApiResponse.setResult(roleService.getAllRole());

        return listApiResponse;
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> getDetailRole(@PathVariable String role){

        ApiResponse<Void> apiResponse = new ApiResponse<>();
        roleService.deleteRole(role);
        apiResponse.setCode(200);
        apiResponse.setMessage("Delete role successfully");

        return apiResponse;
    }
}
