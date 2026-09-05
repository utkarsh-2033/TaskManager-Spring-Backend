package org.utkarsh.taskmanager.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.utkarsh.taskmanager.dto.LoginUser;
import org.utkarsh.taskmanager.model.User;
import org.utkarsh.taskmanager.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo repo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserRepo repo , AuthenticationManager authenticationManager, JwtService jwtService){
        this.repo=repo;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }

    public void registerUser(User user){
        repo.save(user);
    }

    public String loginUser(LoginUser user) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );
        String token = jwtService.generateToken(authentication);
        return token;

    }
}
