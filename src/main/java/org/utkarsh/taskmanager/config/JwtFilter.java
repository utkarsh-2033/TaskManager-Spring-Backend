package org.utkarsh.taskmanager.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.utkarsh.taskmanager.service.CustomUserDetailsService;
import org.utkarsh.taskmanager.service.JwtService;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    JwtFilter(JwtService jwtService , CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");
        if(header==null || !header.startsWith("Bearer ")) {
            response.sendError(401, "Unauthorized");
            return;
        }
        String token=header.substring(7);

        String username=jwtService.extractUsername(token);

        UserDetails user= customUserDetailsService.loadUserByUsername(username);

        if(!jwtService.isValid(token, user.getUsername())) {
            response.sendError(401, "Unauthorized");
            return;
        }

        Authentication authentication=
                new UsernamePasswordAuthenticationToken(user , null , user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
