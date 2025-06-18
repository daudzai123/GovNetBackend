package com.project.DocumentMIS.config;

import com.project.DocumentMIS.Auth.token.TokenRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
        if(authHeader == null){
            throw new AccessDeniedException("Access token not provided");
        }
        if (!authHeader.startsWith("Bearer ")){
            throw new AccessDeniedException("Access token not valid");
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }

        // Clear session data
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Remove cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // Set the cookie's expiry time to zero
                cookie.setPath("/"); // Set the cookie's path
                response.addCookie(cookie); // Add the modified cookie to the response
            }
        }
        SecurityContextHolder.clearContext();
    }
}
