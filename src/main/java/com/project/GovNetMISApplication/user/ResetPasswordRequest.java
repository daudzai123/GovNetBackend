package com.project.GovNetMISApplication.user;

public record ResetPasswordRequest(String newPassword,String confirmNewPassword,Long Id) {
}
