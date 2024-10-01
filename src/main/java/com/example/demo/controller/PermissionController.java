package com.example.demo.controller;

import com.example.demo.dto.request.ApiResponse;
import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.dto.response.PermissionResponse;
import com.example.demo.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest) {

        ApiResponse<PermissionResponse> apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setMessage("Create permission successfully");
        apiResponse.setResult(permissionService.createPermission(permissionRequest));

        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPer() {
        ApiResponse<List<PermissionResponse>> apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setMessage("All list of Permission");
        apiResponse.setResult(permissionService.getAllPermissions());

        return apiResponse;
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> deletePermission(@PathVariable String permission){
        ApiResponse<Void> apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setMessage("Deleted permission");
        apiResponse.setResult(null);
        permissionService.deletePermission(permission);

        return apiResponse;
    }
}
