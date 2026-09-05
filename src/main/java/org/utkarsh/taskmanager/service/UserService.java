package org.utkarsh.taskmanager.service;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.utkarsh.taskmanager.dto.LoginUser;
import org.utkarsh.taskmanager.dto.RegisterUser;
import org.utkarsh.taskmanager.model.User;
import org.utkarsh.taskmanager.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo repo;
    private AuthenticationManager authenticationManager;

    public UserService(UserRepo repo , AuthenticationManager authenticationManager){
        this.repo=repo;
        this.authenticationManager=authenticationManager;
    }

    public void registerUser(User user){
        repo.save(user);
    }

    public void loginUser(LoginUser user) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );
    }
}
