package com.nashtech.rookie.yasa.security;

import com.nashtech.rookie.yasa.repository.UserRepository;
import com.nashtech.rookie.yasa.util.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if ( token != null && JWTService.validate(token)) {
            var username = JWTService.getUsername(token);
            var role = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return JWTService.getRole(token);
                }
            };
            var authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>() {{ add(role);}});
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken (HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if ( authHeader == null ) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}