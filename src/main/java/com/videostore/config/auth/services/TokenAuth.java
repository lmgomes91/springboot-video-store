package com.videostore.config.auth.services;

import com.videostore.config.crypto.TokenService;
import com.videostore.modules.users.entities.User;
import com.videostore.modules.users.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class TokenAuth extends OncePerRequestFilter {
    private TokenService tokenService;
    private UserRepository userRepository;

    public TokenAuth(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        String token = retrieveToken(request);
        boolean valid = tokenService.isValid(token);

        if(valid){
            userAuth(token);
        }

        filterChain.doFilter(request, response);
    }

    private void userAuth(String token) {
        String userId = tokenService.getUserId(token);

        Optional<User> user = userRepository.findById(UUID.fromString(userId));

        if(user.isPresent()){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get(),
                    null, user.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
