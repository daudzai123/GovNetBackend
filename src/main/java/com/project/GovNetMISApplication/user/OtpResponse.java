package com.project.GovNetMISApplication.user;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpResponse {
    @Size(min = 6, max = 6, message = "the OTP code must be 6 digit!")
    private String otp;
}
