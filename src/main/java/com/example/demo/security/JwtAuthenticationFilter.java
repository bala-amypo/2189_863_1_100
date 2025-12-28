// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Arrays;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain)
//             throws ServletException, IOException {

//         // Provide authentication with ADMIN role for all requests
//         UsernamePasswordAuthenticationToken authentication =
//                 new UsernamePasswordAuthenticationToken(
//                         "admin", null, 
//                         Arrays.asList(
//                             new SimpleGrantedAuthority("ROLE_ADMIN"),
//                             new SimpleGrantedAuthority("ROLE_USER")
//                         ));

//         authentication.setDetails(
//                 new WebAuthenticationDetailsSource().buildDetails(request));

//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         filterChain.doFilter(request, response);
//     }
// }


package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        
        if (token != null && jwtUtil.validateToken(token)) {
            String role = jwtUtil.getRoleFromToken(token);
            Long userId = jwtUtil.getUserIdFromToken(token);
            
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userId.toString(), null, 
                            Arrays.asList(
                                new SimpleGrantedAuthority("ROLE_" + role),
                                new SimpleGrantedAuthority("ROLE_USER")
                            ));

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
