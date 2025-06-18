package com.project.DocumentMIS.user;

public record ResetPasswordRequest(String newPassword,String confirmNewPassword,Long Id) {
}
