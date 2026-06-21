package com.dbexcel.controller;

import com.dbexcel.entity.User;
import com.dbexcel.service.UserExcelService;
import com.dbexcel.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserExcelService userExcelService;

    UserController(UserService userService, UserExcelService userExcelService) {
        this.userService = userService;
        this.userExcelService = userExcelService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportExcel() throws IOException {

        ByteArrayInputStream excelFile = userExcelService.getUsersInExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).body(new InputStreamResource(excelFile));
    }

}
