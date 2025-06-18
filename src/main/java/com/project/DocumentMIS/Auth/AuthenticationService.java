package com.project.DocumentMIS.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.DocumentMIS.ActivitiesLog.ActivityLog;
import com.project.DocumentMIS.ActivitiesLog.ActivityLogService;
import com.project.DocumentMIS.Auth.token.Token;
import com.project.DocumentMIS.Auth.token.TokenRepository;
import com.project.DocumentMIS.Auth.token.TokenType;
import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Departments.DepwithUserDto;
import com.project.DocumentMIS.ExceptionHandlingFiles.UserStatusExcetion;
import com.project.DocumentMIS.config.JwtService;
import com.project.DocumentMIS.config.LogoutService;
import com.project.DocumentMIS.user.Users;
import com.project.DocumentMIS.user.UsersRepository;
import com.project.DocumentMIS.user.Enum.userTypes;

//import com.codeMe.JWTDEMO.config.JwtService;
//import com.codeMe.JWTDEMO.roles.Role;
//import com.codeMe.JWTDEMO.roles.roleRepository;
//import com.codeMe.JWTDEMO.users.Users;
//import com.codeMe.JWTDEMO.users.usersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationService {

        @Autowired
        private UsersRepository userRepo;

        @Autowired
        private JwtService jwtService;
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private TokenRepository tokenRepository;
        @Autowired
        private ActivityLogService activityLogService;
        @Autowired
        private LogoutService logoutService;

        // Method for user registration
//        public AuthenticationResponse register(RegisterRequest request) {
//                System.out.println(request);
//
//                List<Roles> roles = new ArrayList<>();
//                Users user1 = new Users();
//                // user1.setFullName(request.getFullName());
//                user1.setEmail(request.getEmail());
//                user1.setPassword(passwordEncoder.encode(request.getPassword()));
//                for (String roleName : request.getRoleNames()) {
//
//                        Roles byRoleName = roleRepo.findByroleName(roleName)
//                                        .orElseThrow(() -> new MyNotFoundException("Role not fount in the database!"));
//                        System.out.println(byRoleName);
//
//                        if (byRoleName != null) {
//                                roles.add(byRoleName); // Use get() to retrieve the actual Role from Optional
//                        }
//                        user1.setRole(roles);
//
//                }
//                userRepo.save(user1);
//
//                var jwtToken = jwtService.generateToken(user1);
//                String refreshToken = jwtService.generateRefreshToken(user1);
//                return AuthenticationResponse.builder()
//                                .accessToken(jwtToken)
//                                .refreshToken(refreshToken)
//
//                                .build();
//        }

        // Method for request for authentitcation(to generate token for authenticated
        // user)
        public AuthenticationResponse authenticate(AuthenticationRequest request,HttpServletResponse response) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                Users user = (Users) userRepo.findByEmail(request.getEmail());
                if (!user.isActivate()) {
                     throw new UserStatusExcetion("this user is bloked!");
                }
                var jwtToken = jwtService.generateToken(user);
                var refreshToken = jwtService.generateRefreshToken(user);
                // revokeAllUserTokens((Users) user);
                saveUserToken((Users) user, jwtToken);
                List<String> authorityNames = new ArrayList<>();
                for (GrantedAuthority authority : user.getAuthorities()) {
                        authorityNames.add(authority.getAuthority().substring(5));
                }
                // List<String> userRoles = ((Users) user).getRole().stream()
                // .map(Roles::getRoleName)
                // .collect(Collectors.toList());
                String firstName = ((Users) user).getFirstName();
                String lastName = ((Users) user).getLastName();
                String email = ((Users) user).getEmail();
                userTypes type = ((Users) user).getUserType();
                String profilepath = ((Users) user).getProfilePath();
                String downloadURL = ((Users) user).getDownloadURL();
                Long userId = ((Users) user).getId();
                List<DepwithUserDto> departments = ((Users) user).getDepartment().stream().map(dep -> {
                DepwithUserDto depwithUserDto = new DepwithUserDto(dep.getDepId(), dep.getDepName());
                return depwithUserDto;
                }).collect(Collectors.toList());
                List<Department> departmentList = user.getDepartment();
                AuthenticationResponse build= AuthenticationResponse.builder()
                                .id(userId)
                        .firstName(firstName)
                        .profilePath(profilepath)
                        .downloadURL(downloadURL)
                        .lastName(lastName)
                        .email(email)
                        .userTypes(type)
                        .accessToken(jwtToken)
//                        .refreshToken(refreshToken)
//                        .refreshToken(refreshToken)
                        .roles(authorityNames)
                        .departments(departments)
                        .build();
//                activityLogService.logsActivity("user",userId,"Login",departmentList,content);

                // Set refresh token as HTTP-only cookie
                Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
                refreshTokenCookie.setHttpOnly(true);
                refreshTokenCookie.setSecure(true); // Ensure it's only sent over HTTPS
                refreshTokenCookie.setPath("/"); // Set the path for which the cookie is valid
                response.addCookie(refreshTokenCookie);
                Map<String,Object> details=new HashMap<>();
                String message = String.format("user %s logged in to the system", firstName);


                activityLogService.logsActivity("user",userId,"Login",user.getDepartment(),message);
                return build;
        }

        private void saveUserToken(Users user, String jwtToken) {
                var token = Token.builder()
                                .user(user)
                                .token(jwtToken)
                                .tokenType(TokenType.BEARER)
                                .expired(false)
                                .revoked(false)
                                .build();
                tokenRepository.save(token);
        }

        public void revokeAllUserTokens(Users user) {
                var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
                if (validUserTokens.isEmpty())
                        return;
                validUserTokens.forEach(token -> {
                        token.setExpired(true);
                        token.setRevoked(true);
                });
                tokenRepository.saveAll(validUserTokens);
        }
        public void refreshToken(
                HttpServletRequest request,
                HttpServletResponse response
        ) throws IOException {
                final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//                final String refreshToken;
                final String userEmail;
//                if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//                        return;
//                }
//                refreshToken = authHeader.substring(7);
//
                // Retrieve refresh token from cookie
                Cookie[] cookies = request.getCookies();
                 String refreshToken = null;
                if (cookies != null) {
                        for (Cookie cookie : cookies) {
                                if ("refreshToken".equals(cookie.getName())) {
                                        refreshToken = cookie.getValue();
                                        break;
                                }
                        }
                }

                if (refreshToken != null){
                userEmail = jwtService.extractUsername(refreshToken);
                if (userEmail != null) {
                        var user = userRepo.findByEmails(userEmail).orElseThrow();
                        if (jwtService.isTokenValid(refreshToken, user)) {
                                var accessToken = jwtService.generateToken(user);
                                revokeAllUserTokens(user);
                                saveUserToken(user, accessToken);
                                List<String> authorityNames = new ArrayList<>();
                                for (GrantedAuthority authority : user.getAuthorities()) {
                                        authorityNames.add(authority.getAuthority().substring(5));
                                }
//                                var authResponse = AuthenticationResponse.builder()
//                                        .accessToken(accessToken)
//                                        .refreshToken(refreshToken)
//                                        .build();
//                                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//                        }
//                                // Set refresh token as HTTP-only cookie
//                                Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
//                                refreshTokenCookie.setHttpOnly(true);
//                                refreshTokenCookie.setSecure(true); // Ensure it's only sent over HTTPS
//                                refreshTokenCookie.setPath("/"); // Set the path for which the cookie is valid
//                                response.addCookie(refreshTokenCookie);
                                var authResponse = AuthenticationResponse.builder()
                                        .accessToken(accessToken)
                                        .roles(authorityNames)
                                        .build();
                                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                        }
                        else {
                                // If refresh token is expired, send custom error message
                                String errorMessage = "Refresh token expired. Please login again.";
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
                                logoutService.logout(request,response,null);
                        }
                        }
                }

                else {
                        // If refresh token is expired, send custom error message
                        String errorMessage = "Refresh token expired. Please login again.";
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
                        logoutService.logout(request,response,null);
                }
        }
}
