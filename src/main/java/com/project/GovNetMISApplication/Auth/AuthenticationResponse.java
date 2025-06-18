package com.project.GovNetMISApplication.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.GovNetMISApplication.Departments.DepwithUserDto;
import com.project.GovNetMISApplication.user.Enum.userTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String profilePath;
  private String downloadURL;
  private String email;
  @JsonProperty("access_token")
  private String accessToken;
//  @JsonProperty("refresh_token")
//  private String refreshToken;
  private List<String> roles;
  private userTypes userTypes;
  private List<DepwithUserDto> departments;
}