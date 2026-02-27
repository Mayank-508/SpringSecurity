package com.SecurityApplication.demo.filters;

import com.SecurityApplication.demo.entity.User;
import com.SecurityApplication.demo.service.JwtService;
import com.SecurityApplication.demo.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader=request.getHeader("Authorization");
        if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        // Token actually starts with bearer so its like "Bearer knfdkdnf.ksdbjfjke.ksnfjwe"
        // so doing split("Bearer ") actually gives us the array of string {[], [token]}
        // so we're doing -> arr[1]

        String token = requestTokenHeader.split("Bearer ")[1];

        Long userId=jwtService.getUserIdFromToken(token);

        if(userId !=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
          User user=  userService.getUserByUserId(userId);
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user, null, null);
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }

        filterChain.doFilter(request,response);

    }
}
