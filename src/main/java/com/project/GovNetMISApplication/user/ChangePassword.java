package com.project.GovNetMISApplication.user;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePassword {
    @Size(min = 6, max = 20)
    String newPassword;
    String oldPassword;
    Long Id;
}
