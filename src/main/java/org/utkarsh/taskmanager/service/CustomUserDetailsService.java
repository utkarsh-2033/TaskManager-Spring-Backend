package org.utkarsh.taskmanager.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.utkarsh.taskmanager.model.User;
import org.utkarsh.taskmanager.repository.UserRepo;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo repo;

    CustomUserDetailsService(UserRepo repo){
        this.repo=repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repo.findByUsername(username);
        if(user==null) return null;
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), List.of());
    }
}
