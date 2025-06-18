package com.project.DocumentMIS.Auth;

import com.project.DocumentMIS.config.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.io.IOException;

//@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173"})
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationService service;
  @Autowired
  private LogoutService logoutService;




    // @GetMapping("/send-email")
    // public ResponseEntity<?> sendEmail(@RequestBody String to) {
    //   users user = (users) userrepo.findByEmail(to);
    //   if (user!=null) {

    //     String subject = "Password Reset";
    //     String text = "Your password reset instructions..."; // Customize this

    //     emailService.sendSimpleMessage(to, subject, text);
        
    //     return ResponseEntity.ok().body("Reset Code is send to user Email");
    //   } else {
    //       // User does not exist, send a message to the front-end
    //       return ResponseEntity.ok().body("User not found!");
    //   }  

    // }

//  @PostMapping("/register")
//  public ResponseEntity<AuthenticationResponse> register(
//      @RequestBody RegisterRequest request
//  ) {
//    System.out.println(request);
//    return ResponseEntity.ok(service.register(request));
//  }
  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody @Valid AuthenticationRequest request,HttpServletResponse response
  ) {
    AuthenticationResponse authenticate = service.authenticate(request, response);
    return ResponseEntity.ok(authenticate);
  }
  @GetMapping("/refresh-token")
  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }
  @PostMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    logoutService.logout(request,response,authentication);
    return ResponseEntity.ok("Logout successful");
  }

//  @PostMapping("/refresh-token")
//  public void refreshToken(
//      HttpServletRequest request,
//      HttpServletResponse response
//  ) throws IOException {
//    service.refreshToken(request, response);
//  }




}
