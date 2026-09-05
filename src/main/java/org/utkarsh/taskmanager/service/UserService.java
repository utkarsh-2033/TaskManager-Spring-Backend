package org.utkarsh.taskmanager.service;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;
import org.utkarsh.taskmanager.dto.RegisterUser;
import org.utkarsh.taskmanager.model.User;
import org.utkarsh.taskmanager.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo){
        this.repo=repo;
    }

    public void registerUser(User user){
        repo.save(user);
    }
}
