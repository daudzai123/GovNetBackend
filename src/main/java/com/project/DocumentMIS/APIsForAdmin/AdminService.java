package com.project.DocumentMIS.APIsForAdmin;

import com.project.DocumentMIS.Departments.Department;
import com.project.DocumentMIS.Departments.DepartmentRepository;
import com.project.DocumentMIS.Role.RoleRepository;
import com.project.DocumentMIS.user.Users;
import com.project.DocumentMIS.user.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final UsersRepository usersRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public AdminService(UsersRepository usersRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseDTO DataForAdminDashboard(){
        long totalUsers = usersRepository.findAll().stream().count();
        long totalActiveUsers = usersRepository.countByActivateTrue();
        long totalNonActiveUsers = usersRepository.countByActivateFalse();
        long totalDepartments = departmentRepository.findAll().stream().count();
        long totalRoles = roleRepository.findAll().stream().count();
        List<Users> byUserType = usersRepository.findByUserType();
        List<committeeMembersDetails> committeeMembersDetailsList = byUserType.stream().map(users -> {
            committeeMembersDetails committeeMembersDetails = mapUserWithCommitteeMemberDetail(users);
            return committeeMembersDetails;
        }).collect(Collectors.toList());
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setTotalUsers(totalUsers);
        responseDTO.setActiveUsers(totalActiveUsers);
        responseDTO.setNonActiveUsers(totalNonActiveUsers);
        responseDTO.setTotalDepartments(totalDepartments);
        responseDTO.setTotalRoles(totalRoles);
        responseDTO.setCommitteeMembersDetailsList(committeeMembersDetailsList);
        return responseDTO;
    }
    public committeeMembersDetails mapUserWithCommitteeMemberDetail(Users user1){
        committeeMembersDetails map = modelMapper.map(user1, committeeMembersDetails.class);
        List<Department> department = user1.getDepartment();
        List<String> departmentNames = department.stream().map(department1 -> {
            String depName = department1.getDepName();
            return depName;
        }).collect(Collectors.toList());
        map.setDepName(departmentNames);
        return map;
    }


}
