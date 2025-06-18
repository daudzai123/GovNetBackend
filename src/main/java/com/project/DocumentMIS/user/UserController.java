package com.project.DocumentMIS.user;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {
        Logger log=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userSer;
    @Autowired
    private UsersRepository usersRepository;
//    @Autowired
//    SimpMessagingTemplate messagingTemplate;
    @GetMapping("/GETALLUSERS")
    public List<Users> getUsers(){
        return usersRepository.findAll();
    }
    
    @GetMapping("/all")
    public Page<UserResponseDT> getAllUsers(Pageable pageable){
        log.debug("all user accessed!!!");
        return userSer.getAllUsers(pageable);
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addUser(@Valid @RequestPart("user1")  UserDTO user1, @RequestPart(value = "file",required = false) MultipartFile file){
        Users s = userSer.registerUser(user1, file);

//        Notification notification=new Notification();
//        String content="New user "+s.getFirstName() +" "+s.getLastName()+" added";
//        List<Long> collect = s.getDepartment().stream().map(department -> {
//            Long depId = department.getParentDepartmentId().getDepId();
//            return depId;
//        }).collect(Collectors.toList());

//        String content="New user "+s.getFirstName() +" "+s.getLastName()+" added";
//        List<Long> collect = s.getDepartment().stream().map(department -> {
//            Long depId = department.getParentDepartmentId().getDepId();
//            return depId;
//        }).collect(Collectors.toList());

//        if (s != null && s.getDepartment() !=null){
//            messagingTemplate.convertAndSend("/topic/department"+collect,content);
//        }

        return "User added successfully";
    }
    @GetMapping("/get/{id}")
    public UserResponseDT getUserById(@PathVariable Long id){
        return userSer.getSingleUser(id);
    }
    @GetMapping("/getUser/{id}")
    public Optional<Users> getUser(@PathVariable Long id){
      return  userSer.getUser(id);
    }
    @PutMapping(value = "/update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                             @RequestPart(value = "user",required = false) UserDTO updatedUser,
                             @RequestPart(value = "file",required = false) MultipartFile file) throws IOException {
        userSer.updateUser(id, updatedUser, file);
        return ResponseEntity.ok().body("user updated successfully");
    }

//    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserDTO user1) {
//        userSer.updateUser(id,user1);
//        return ResponseEntity.ok().body("user updated successfully");
//    }
    @GetMapping("getAllUserSOfSingleDepartment/{id}")
    public List<UserResponseDT> usersOfDepartment(@PathVariable Long id){
        return userSer.getUsersOfDepartment(id);
    }

    @GetMapping("/getCurrentUser")
    public UserResponseDT getUser(){
        return userSer.getCurrentLoggedInUser();
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDT>> searchUsers(@RequestParam String searchItem){
        List<UserResponseDT> UserResponseDTS = userSer.searchUser(searchItem);
        return ResponseEntity.ok(UserResponseDTS);
    }


    @PutMapping("/{userId}/roles")
    public ResponseEntity<?> assignRolesToUser(@PathVariable Long userId, @RequestBody List<Long> roleIds) {
        userSer.assignRolesToUser(userId, roleIds);
        return ResponseEntity.ok("Roles assigned to the user successfully.");
    }

    @PutMapping("/disableuser/{id}")
    public ResponseEntity<?> disableUser(@PathVariable Long id){
        userSer.disableUser(id);
        return ResponseEntity.ok("User Disabled Successfully");
    }

    @PutMapping("/enableuser/{id}")
    public ResponseEntity<?> enableUser(@PathVariable Long id){
        userSer.enableUser(id);
        return ResponseEntity.ok("User Enabled Successfully");
    }

    @PostMapping("otp")
    public ResponseEntity<String> requestPasswordResetOTP(@Valid @RequestBody ResetPasswordOTPRequest request) {
        return userSer.requestPasswordResetOTP(request);
    }
    @PostMapping("checkOtp")
    public ResponseEntity<?> requestPasswordResetOTP(@Valid @RequestBody OtpResponse otpcode) {
        System.out.println("khanllllllllll:"+otpcode.getOtp());
        return userSer.ValidateOtp(otpcode.getOtp());
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> requestPasswordReset(@Valid @RequestBody ResetPasswordRequest request) {
        return userSer.requestPasswordReset(request);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> ChangePassword(@Valid @RequestBody ChangePassword request) {
        System.out.println("this is the reques"+request.getId());
        return userSer.ChangePassword(request);
    }

    @GetMapping("/departments")
    public List<UserDepartmentDTO> getDepartmentsAndUserCount() {
        return userSer.getDepartmentsAndUserCount();
    }
}
    
