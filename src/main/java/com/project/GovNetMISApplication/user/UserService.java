package com.project.GovNetMISApplication.user;

import com.project.GovNetMISApplication.ActivitiesLog.ActivityLog;
import com.project.GovNetMISApplication.ActivitiesLog.ActivityLogService;
import com.project.GovNetMISApplication.Auth.AuthenticationService;
import com.project.GovNetMISApplication.ExceptionHandlingFiles.MyNotFoundException;
import com.project.GovNetMISApplication.Notifications.Notification;
import com.project.GovNetMISApplication.Notifications.NotificationRepository;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Departments.DepartmentRepository;
import com.project.GovNetMISApplication.Departments.DepwithUserDto;
import com.project.GovNetMISApplication.Documents.PathToDownloadUrlService;
import com.project.GovNetMISApplication.Role.Roles;
import com.project.GovNetMISApplication.Role.RoleRepository;
import com.project.GovNetMISApplication.UploadAndDownload.documentUpload;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Value("${file.upload-dir}")
    private  String UPLOAD_DIRECTORY;

    private final PathToDownloadUrlService pathToDownloadUrlService;

    private final UsersRepository usersRepo;

    private final RoleRepository roleRepo;


    private final DepartmentRepository departmentRepo;

    private final ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;


    private final OTPService otpService;

   private final documentUpload uploadService;


    private final CurrentUserInfoService currentUserInfoSer;

    private final ActivityLogService activityLogService;
    private final NotificationRepository notificationRepository;



    private final AuthenticationService authenticateService;


    Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserService(PathToDownloadUrlService pathToDownloadUrlService, UsersRepository usersRepo, RoleRepository roleRepo, DepartmentRepository departmentRepo, ModelMapper mapper, PasswordEncoder passwordEncoder, OTPService otpService, documentUpload uploadService, CurrentUserInfoService currentUserInfoSer, ActivityLogService activityLogService, NotificationRepository notificationRepository, AuthenticationService authenticateService) {
        this.pathToDownloadUrlService = pathToDownloadUrlService;
        this.usersRepo = usersRepo;
        this.roleRepo = roleRepo;
        this.departmentRepo = departmentRepo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.uploadService = uploadService;
        this.currentUserInfoSer = currentUserInfoSer;
        this.activityLogService = activityLogService;
        this.notificationRepository = notificationRepository;
        this.authenticateService = authenticateService;

    }

    public Users registerUser(UserDTO register,MultipartFile file) {

        List<Users> notifialbes = new ArrayList<>();
        Users user1 = mapper.map(register, Users.class);
        user1.setPassword(passwordEncoder.encode(register.getPassword()));

        if (!register.getDepartmentIds().isEmpty()) {
            List<Department> departments = departmentRepo.findAllById(register.getDepartmentIds());
            departments.get(0).getDepName();
            departments.get(0).getParentDepartmentId();
            user1.setDepartment(departments);
            for (Department dep : departments) {
                for (Users use : dep.getActiveUsers()) {
                    notifialbes.add(use);
                }
            }
           
        }

        else {
            user1.setDepartment(null);
        }
        List<Roles> rolesList = new ArrayList<>();
        for (String roleName : register.getRoleName()
        ) {
            Roles role = roleRepo.findByroleName(roleName).orElseThrow(()-> new MyNotFoundException("Role not found with name : "+ roleName) );
            if (role != null) {
                rolesList.add(role);
            }
        }

        if (rolesList.isEmpty()) {
            Roles defaultRole = roleRepo.findByroleName("ROLE_VIEVER").orElseThrow(()-> new MyNotFoundException("the role is not allowed") );; // Assuming "Viewer" is a default role
            rolesList.add(defaultRole);
        }
        user1.setRole(rolesList);
        String filePath = null;
         if (file!=null && !file.isEmpty() && file.getSize()>0) {
            String uniqueFileName=uploadService.generateUniqueFileName(file.getOriginalFilename(),UPLOAD_DIRECTORY);
            filePath=UPLOAD_DIRECTORY+uniqueFileName;
            Path path=Paths.get(filePath);
            // copy the file to this path
            try {
                Files.write(path,file.getBytes());
                user1.setProfilePath(pathToDownloadUrlService.getAccessUrl(uniqueFileName));
                user1.setDownloadURL(pathToDownloadUrlService.getDownloadUrl(uniqueFileName));
            } catch (IOException e) {
            }
         }
        Users registered = usersRepo.save(user1);
        List<Department> department = registered.getDepartment();
        Map<String,Object> details=new HashMap<>();
        details.put("user id ",registered.getId());
        details.put("userName ",registered.getFirstName());
        List<String> depNames = registered.getDepartment().stream().map(depName -> {
            String depName1 = depName.getDepName();
            return depName1;
        }).collect(Collectors.toList());
        details.put("Department ",depNames);
        List<String> roleNames = registered.getRole().stream().map(roleName -> {
            String roleName1 = roleName.getRoleName();
            return roleName1;
        }).collect(Collectors.toList());
        details.put("Role ",roleNames);
        ActivityLog activityLog=new ActivityLog();
        String content = activityLog.MapToString(details);
        activityLogService.logsActivity("user",registered.getId(),"created",department,content);
        if (!notifialbes.isEmpty()) {
            for (Users noti : notifialbes) {
                Notification notification = new Notification(registered, noti);
                notificationRepository.save(notification); 
            }
        }
        return user1;
    }

    // private void saveUserToken(Users user, String jwtToken) {
    //     var token = Token.builder()
    //             .user(user)
    //             .token(jwtToken)
    //             .tokenType(TokenType.BEARER)
    //             .expired(false)
    //             .revoked(false)
    //             .build();
    //     tokenRepository.save(token);
    // }


    public Page<UserResponseDT> getAllUsers(Pageable pageable) {
        Page<Users> usersList = usersRepo.findAll(pageable);
        List<UserResponseDT> collect = usersList.stream()
                .map(us -> {
                    UserResponseDT userDTO = mapper.map(us, UserResponseDT.class);
                    List<String> roleNames = us.getRole().stream()
                            .map(role -> role.getAuthority())
                            .collect(Collectors.toList());
                    userDTO.setRoleName(roleNames);

                    List<DepwithUserDto> departments = us.getDepartment().stream()
                        .map(depart -> new DepwithUserDto(depart.getDepId(), depart.getDepName()))
                        .collect(Collectors.toList());
                    userDTO.setDepartments(departments);
                    return userDTO;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, usersList.getTotalElements());
    }

    public UserResponseDT getSingleUser(Long id) {
        Users byId = usersRepo.findById(id).orElseThrow(()->new MyNotFoundException("user with id "+id+" not found"));
        UserResponseDT UserResponseDT = mapEntityToDTO(byId);
        return UserResponseDT;
        }

    public Optional<Users> getUser(Long id) {
        return usersRepo.findById(id);
    }

    public void updateUser(Long id, UserDTO user1,MultipartFile file) throws IOException {
        Users existingUser = usersRepo.findById(id).orElseThrow(() -> new MyNotFoundException("The user is not found!"));
       Users previousUser=new Users(existingUser);
        Map<String,Object> updatedData=new HashMap<>();
        if (user1.getFirstName() != null) {
            existingUser.setFirstName(user1.getFirstName());
            if (!Objects.equals(previousUser.getFirstName(),existingUser.getFirstName())) {
                updatedData.put("previous userName", previousUser.getFirstName());
                updatedData.put("updated userName", existingUser.getFirstName());
            }
        }

        if (user1.getLastName() != null) {
            existingUser.setLastName(user1.getLastName());
            if (!Objects.equals(previousUser.getLastName(),existingUser.getLastName())) {
                updatedData.put("previous userLastName", previousUser.getLastName());
                updatedData.put("updated userLastName", existingUser.getLastName());
            }
        }
        if (user1.getPosition() != null) {
            existingUser.setPosition(user1.getPosition());
            if (!Objects.equals(previousUser.getPosition(),existingUser.getPosition())) {
                updatedData.put("previous userPosition", previousUser.getPosition());
                updatedData.put("updated userPosition", existingUser.getPosition());
            }
        }
        if (user1.getUserType() != null) {
            existingUser.setUserType(user1.getUserType());
            if (!Objects.equals(previousUser.getUserType(),existingUser.getUserType())) {
                updatedData.put("previous userType", previousUser.getUserType());
                updatedData.put("updated userType", existingUser.getUserType());
            }
        }
        if (user1.isActivate() != existingUser.isActivate()) {
            existingUser.setActivate(user1.isActivate());
            updatedData.put("previous userActivation",previousUser.isActivate());
            updatedData.put("updated userActivation",existingUser.isActivate());
        }
        if (user1.getPhoneNo() != null){
            existingUser.setPhoneNo(user1.getPhoneNo());
//            if (!previousUser.getPhoneNo().equals(existingUser.getPhoneNo())) {
            if (!Objects.equals(previousUser.getPhoneNo(),existingUser.getPhoneNo())){
                updatedData.put("previous userPhoneNo", previousUser.getPhoneNo());
                updatedData.put("updated userPhoneNo", existingUser.getPhoneNo());
            }
        }
        if (user1.getPassword()!=null && !user1.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user1.getPassword()));
            if (!Objects.equals(previousUser.getPassword(),existingUser.getPassword())) {
                updatedData.put("previous userPassword", previousUser.getPassword());
                updatedData.put("updated userPassword", existingUser.getPassword());
                updatedData.put("user who`s password changed",previousUser.getFirstName());
            }
        }
        if (user1.getDepartmentIds() != null && user1.getDepartmentIds().size() > 0) {
            List<Department> departments = departmentRepo.findAllById(user1.getDepartmentIds());
            existingUser.setDepartment(departments);
            List<String> previousDepNames = previousUser.getDepartment().stream().map(department -> {
                String depName = department.getDepName();
                return depName;
            }).collect(Collectors.toList());

            List<String> updatedDepNames = existingUser.getDepartment().stream().map(department -> {
                String depName = department.getDepName();
                return depName;
            }).collect(Collectors.toList());
            if (!Objects.equals(previousDepNames,updatedDepNames)) {
                updatedData.put("previous userDepartments", previousDepNames);
                updatedData.put("updated userDepartments", updatedDepNames);
            }
        }
        List<Roles> rolesList = new ArrayList<>();
        if (user1.getRoleName() != null) {
            for (String roleName : user1.getRoleName()) {
                Roles role = roleRepo.findByroleName(roleName)
                        .orElseThrow(() -> new MyNotFoundException("Role not found with name : " + roleName));
                if (role != null) {
                    rolesList.add(role);
                }
            }
            if (!rolesList.isEmpty()) {
                existingUser.setRole(rolesList);
            }
            List<String> previousRoles = previousUser.getRole().stream().map(roles -> {
                String roleName = roles.getRoleName();
                return roleName;
            }).collect(Collectors.toList());

            List<String> updatedUserRoles = existingUser.getRole().stream().map(roles -> {
                String roleName = roles.getRoleName();
                return roleName;
            }).collect(Collectors.toList());
            if (!Objects.equals(previousRoles,updatedUserRoles)) {
                updatedData.put("previous userRoles", previousRoles);
                updatedData.put("updated userRoles", updatedUserRoles);
            }
        }


        if (user1.getEmail() != null) {
            existingUser.setEmail(user1.getEmail());
            if (!Objects.equals(previousUser.getEmail(),existingUser.getEmail())) {
                updatedData.put("previous userEmail", previousUser.getEmail());
                updatedData.put("updated userEmail", existingUser.getEmail());
            }
        }
        if (user1.getPhoneNo() != null) {
            existingUser.setPhoneNo(user1.getPhoneNo());

        }

        String filePath = null;
        if (file != null && !file.isEmpty() && file.getSize() > 0) {
            // uploading new file
            String uniqueFileName = uploadService.generateUniqueFileName(file.getOriginalFilename(), UPLOAD_DIRECTORY);
            filePath = UPLOAD_DIRECTORY + uniqueFileName;
            Path newPath = Paths.get(filePath);
            Files.write(newPath, file.getBytes());

            // if existing user having profile pic so delete it
            if (existingUser.getProfilePath() != null) {
                String existingFilePath = UPLOAD_DIRECTORY + existingUser.getProfilePath();
                String newProfilePath = existingFilePath.replace("//download", "");
                Path oldFile = Paths.get(newProfilePath);
                if (Files.exists(oldFile))

                    Files.delete(oldFile);
            }
            existingUser.setProfilePath(pathToDownloadUrlService.getAccessUrl(uniqueFileName));
            existingUser.setDownloadURL(pathToDownloadUrlService.getDownloadUrl(uniqueFileName));
        }
        Users save = usersRepo.save(existingUser);
        List<Department> departmentList=new ArrayList<>();
        departmentList= save.getDepartment();
        ActivityLog activityLog=new ActivityLog();
        String currentUserName = currentUserInfoSer.getCurrentUser().getFirstName();
        updatedData.put("updated by",currentUserName);
        String content = activityLog.MapToString(updatedData);
        activityLogService.logsActivity("user",save.getId(),"updated",departmentList,content);
    }

    public List<UserResponseDT> getUsersOfDepartment(Long id) {
        List<Users> usersByDepartment = usersRepo.findByDepartmentDepId(id);
        try {
            return usersByDepartment.stream()
                    .map(us -> {
                        UserResponseDT userDTO = mapper.map(us, UserResponseDT.class);
                        List<String> roleNames = us.getRole().stream()
                                .map(role -> role.getAuthority())
                                .collect(Collectors.toList());
                        userDTO.setRoleName(roleNames);
                        return userDTO;
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Department> getDepartmentsByUser(Long userId) {
        List<Department> departmentsByUserId = usersRepo.findDepartmentsByUserId(userId);
        return departmentsByUserId;
    }

    public UserResponseDT getCurrentLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            // Handle the case where no user is authenticated (e.g., anonymous user)
            return null;
        }

        // Assuming your UserDetails implementation is of type Users
        Users user = (Users) authentication.getPrincipal();
        Hibernate.initialize(user.getDepartment());
        Users user2 = usersRepo.findById(user.getId()).get();
        UserResponseDT userDTO = mapper.map(user2, UserResponseDT.class);
        List<String> roleNames = user2.getRole().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        userDTO.setRoleName(roleNames);
        List<DepwithUserDto> departments = user2.getDepartment().stream()
                        .map(depart -> new DepwithUserDto(depart.getDepId(), depart.getDepName()))
                        .collect(Collectors.toList());
                    userDTO.setDepartments(departments);
        return userDTO;

    }

    public void assignRolesToUser(Long userId, List<Long> roleIds) {
        Users user = usersRepo.findById(userId)
                .orElseThrow(() -> new MyNotFoundException("User not found with id: " + userId));

        List<Roles> roles = roleRepo.findAllById(roleIds);
        user.setRole(roles);
        usersRepo.save(user);
    }

    public boolean disableUser(Long id) {
        Users user = usersRepo.findById(id).orElseThrow(() -> new MyNotFoundException("User not found!"));

        if (user != null) {
            user.setActivate(false);
            usersRepo.save(user);
            SecurityContextHolder.clearContext();
            authenticateService.revokeAllUserTokens(user);
            List<Department> department = user.getDepartment();
//            activityLogService.logActivity("user",user.getId(),"user disabled",department);
            Map<String,Object> updatedData=new HashMap<>();
            updatedData.put("disabled userId",user.getId());
            updatedData.put("disabled userName",user.getFirstName());
            List<String> depNames = user.getDepartment().stream().map(department1 -> {
                String depName = department1.getDepName();
                return depName;
            }).collect(Collectors.toList());
            updatedData.put("disabled userDepartments",depNames);
            List<String> roleNames = user.getRole().stream().map(roles -> {
                String roleName = roles.getRoleName();
                return roleName;
            }).collect(Collectors.toList());
            updatedData.put("disabled userRoles",roleNames);
            ActivityLog activityLog=new ActivityLog();
            String content = activityLog.MapToString(updatedData);
            activityLogService.logsActivity("user",user.getId(),"Disabled user",user.getDepartment(),content);
            return true;
        }
        else {
            throw new MyNotFoundException("User with id  " + id + "  Not Found");
        }
    }

    public boolean enableUser(Long id) {
        Users user = usersRepo.findById(id).orElseThrow(() -> new MyNotFoundException("User not found!"));

        if (user !=null) {
            user.setActivate(true);
            usersRepo.save(user);
            List<Department> department = user.getDepartment();
//            activityLogService.logActivity("user",user.get().getId(),"user enabled",department);
            Map<String,Object> updatedData=new HashMap<>();
            updatedData.put("enabled userId",user.getId());
            updatedData.put("enabled userName",user.getFirstName());
            List<String> depNames = user.getDepartment().stream().map(department1 -> {
                String depName = department1.getDepName();
                return depName;
            }).collect(Collectors.toList());
            updatedData.put("enabled userDepartments",depNames);
            List<String> roleNames = user.getRole().stream().map(roles -> {
                String roleName = roles.getRoleName();
                return roleName;
            }).collect(Collectors.toList());
            updatedData.put("enabled userRoles",roleNames);
            ActivityLog activityLog=new ActivityLog();
            String content = activityLog.MapToString(updatedData);
            activityLogService.logsActivity("user",user.getId(),"enabled user",department,content);
            return true;
        } else {
            throw new MyNotFoundException("User with id  " + id + "  Not Found");
        }
    }

    public ResponseEntity<String> requestPasswordResetOTP(ResetPasswordOTPRequest request) {
        String email = request.email();
        Users user = (Users) usersRepo.findByEmail(email);
        if (user == null) {
            throw new MyNotFoundException("The email do not exsit in syste!");
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Email
            // Address");
        }
        String otpCode = otpService.generateOTP();
        String subject = "OTP Code";
        String body = "Hi dear User, Please Use this (" + otpCode + ")  6 digit code to reset your password.";
        otpService.sendOTP(email, subject, body);
        otpService.storeOTPCode(user, otpCode);
        return ResponseEntity.ok("OTP generated and Send to User Successfully");
    }

    public ResponseEntity<String> requestPasswordReset(ResetPasswordRequest request) {
        Users user = usersRepo.findById(request.Id()).orElseThrow(() -> new MyNotFoundException("User not found!"));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        String encryptedNewPassword = new BCryptPasswordEncoder().encode(request.newPassword());
        user.setPassword(encryptedNewPassword);
        user.setOtpCode(null);
        user.setOtpExpiration(null);
        usersRepo.save(user);
        List<Department> department = user.getDepartment();
        activityLogService.logActivity("user",user.getId(),"request password changed",department);
        return ResponseEntity.ok("Password Changed Successfully");
    }


    public ResponseEntity<String> ChangePassword(ChangePassword request) {
        Users user = usersRepo.findById(request.getId()).orElseThrow(() -> new MyNotFoundException("User not found!"));
        if (new BCryptPasswordEncoder().matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(request.getNewPassword()));
            usersRepo.save(user);
            SecurityContextHolder.clearContext();
            authenticateService.revokeAllUserTokens(user);
            List<Department> department = user.getDepartment();
            ActivityLog activityLog=new ActivityLog();
            Map<String,Object> mapData= new HashMap<>();
            mapData.put("user who`s password changed",user.getFirstName());
            String content = activityLog.MapToString(mapData);
            activityLogService.logsActivity("user",user.getId(),"password changed",department,content);
            return ResponseEntity.ok("Password Changed Successfully");
        }
        return ResponseEntity.badRequest().body("Old Password is incorrect!");
    }

    public ResponseEntity<?> ValidateOtp(String otp) {
        Users user = usersRepo.findByOtpCode(otp);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP Code");
        }
        // Validate the OTP code
        if (!otpService.isOTPValid(user, otp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP code expired");
        }

        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    public Long getTotalUsersCount() {
        return usersRepo.count();
    }

    public List<UserResponseDT> searchUser(String searchItem) {
        List<Users> users = usersRepo.searchUsers(searchItem);
        List<UserResponseDT> UserResponseDTList=null;
        UserResponseDTList = users.stream().map(this::mapEntityToDTO)
                .collect(Collectors.toList());

        return UserResponseDTList;
    }
    public UserResponseDT mapEntityToDTO(Users user){
        UserResponseDT map = mapper.map(user, UserResponseDT.class);
        map.setDepartments(user.getDepartment().stream().map(department -> {
            DepwithUserDto depwithUserDto = new DepwithUserDto(department.getDepId(), department.getDepName());
            return depwithUserDto;
        }).collect(Collectors.toList()));
        map.setRoleName(user.getRole().stream().map(roles -> {
            String roleName = roles.getRoleName();
            return roleName;
        }).collect(Collectors.toList()));
        return map;
    }

    public List<UserDepartmentDTO> getDepartmentsAndUserCount() {
        Long userId = currentUserInfoSer.getCurrentUserId();
        List<Department> departments = usersRepo.findDepartmentsByUserId(userId);
        List<UserDepartmentDTO> collect = departments.stream().map(department -> {
                UserDepartmentDTO map = mapper.map(department, UserDepartmentDTO.class);
                map.setUsercount(department.getUsers().stream().count());
                return map;
                })
                .collect(Collectors.toList());
        return collect;
    }
}
