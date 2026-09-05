package org.utkarsh.taskmanager.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.utkarsh.taskmanager.dto.LoginUser;
import org.utkarsh.taskmanager.dto.RegisterUser;
import org.utkarsh.taskmanager.model.User;
import org.utkarsh.taskmanager.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService service;

    public UserController(PasswordEncoder passwordEncoder , UserService service){
        this.passwordEncoder=passwordEncoder;
        this.service=service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUser user){
        String hashedPassword=passwordEncoder.encode(user.getPassword());
        User newuser=new User();
        newuser.setPassword(hashedPassword);
        newuser.setUsername(user.getUsername());
        service.registerUser(newuser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUser user){
        service.loginUser(user);
        return ResponseEntity.ok().build();
    }
}
