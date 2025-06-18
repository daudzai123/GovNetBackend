package com.project.DocumentMIS.APIsForAdmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private  AdminService adminService;
    public AdminController() {
    }

    @GetMapping("/data")
    public ResponseDTO getAllDataForAdmin(){
        ResponseDTO responseDTO = adminService.DataForAdminDashboard();
        return responseDTO;
    }
}
