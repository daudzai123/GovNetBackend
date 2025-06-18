package com.project.GovNetMISApplication.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.GovNetMISApplication.Permission.PermissionsData;
import com.project.GovNetMISApplication.Departments.Department;
import com.project.GovNetMISApplication.Departments.DepartmentRepository;
import com.project.GovNetMISApplication.ExceptionHandlingFiles.MyNotFoundException;
import com.project.GovNetMISApplication.Permission.Permission;
import com.project.GovNetMISApplication.Permission.PermissionRepository;
import com.project.GovNetMISApplication.Role.RoleRepository;
import com.project.GovNetMISApplication.Role.Roles;
import com.project.GovNetMISApplication.user.Users;
import com.project.GovNetMISApplication.user.UsersRepository;
import com.project.GovNetMISApplication.user.Enum.userTypes;

@Service
public class InitializationService {
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    

    List<Permission> permissions = new ArrayList<>();

    @Transactional
    public void initialize() {
        if (userRepository.count() == 0) {
//            insertDefaultRoles();
            insertDefaultUsers();
            insertDefaultAdminPermissions();

            insertDefaultDepartment();
        }
        insertDefaultPermissions();
        insertDefaultAdminPermissions();
    }

    private Roles insertDefaultRoles() {
        Roles adminRole = new Roles();
        adminRole.setRoleName("ROLE_ADMIN");
        adminRole.setDescription("this role is predefined role with all permissions");
        adminRole.setPermissions(permissions);
        return roleRepository.save(adminRole);
    }

    private void insertDefaultUsers() {
        Users user = new Users();
        user.setActivate(true);
        user.setEmail("admin@gmail.com");
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setPosition("System Admin");
        user.setUserType(userTypes.ADMIN);
        user.setPassword(passwordEncoder.encode("admin@123"));
        user.setRole(Collections.singletonList(this.insertDefaultRoles()));
        userRepository.save(user);
    }

    private void insertDefaultDepartment(){
        Department dep = new Department();
        dep.setDepName("");
        dep.setDescription("این دیپارتمنت در راس درخت تشکیلاتی دیپارتمنت ها قرار دارد و دیګران همه ماتحت آن خواهد بود.");
        departmentRepository.save(dep);
    }

    private void insertDefaultAdminPermissions(){
        List<PermissionsData> PermissionsDataList = new ArrayList<>();

        PermissionsDataList.add(new PermissionsData("کارن جوړول", "ایجاد کاربر", "Create User", "user_creation"));
        PermissionsDataList.add(new PermissionsData("د کارن تغییرول", "ویرایش کاربر", "Edit User", "user_edit"));
        PermissionsDataList.add(new PermissionsData("دټولو کارنونو کتل","دیدن کاربران","all users","user_all"));
        PermissionsDataList.add(new PermissionsData("ډیپارتمنت جوړول", "ایجاد دیپارتمنت", "Create Department", "department_creation"));
        PermissionsDataList.add(new PermissionsData("ډیپارتمنت کتل", "دیدن دیپارتمنت", "View Department", "all_departments_view"));
        PermissionsDataList.add(new PermissionsData("ډیپارتمنت تفیرول", "تغیر دیپارتمنت", "Update Department", "department_update"));
        PermissionsDataList.add(new PermissionsData(" صلاحیت جوړول", "ایجاد صلاحیت", "Create Role", "role_creation"));
        PermissionsDataList.add(new PermissionsData("د صلاحیتونو لیدل", "دیدن لست صلاحیت ها", "View Roles List", "role_list"));
        PermissionsDataList.add(new PermissionsData("د صلاحیت تغییرول", "ویرایش صلاحیت", "Edit Role", "role_update"));
        PermissionsDataList.add(new PermissionsData("بک آپ جوړول", "ایجاد بک نسخه پشتیبان", "Backup Creation", "backup_creation"));
        PermissionsDataList.add(new PermissionsData("بک آپ لیست", " لیست نسخه پشتیبان", "Backup list view", "backup_List"));
        PermissionsDataList.add(new PermissionsData("د فعالیت لاگ لیدل", "دیدن لاگ فعالیت ها", "View Log", "log_view"));
        PermissionsDataList.add(new PermissionsData("د پوست د مرجع جوړول", "ایجاد مرجع پوست", "Create Post Reference", "add_document_reference"));
        PermissionsDataList.add(new PermissionsData("د پوست د مرجع تغیرول", "تغیر مرجع پوست", "Update Post Reference", "edit_document_reference"));
        PermissionsDataList.add(new PermissionsData("د پوست د مرجع لری کول", "حذف مرجع پوست", "Delete Post Reference", "delete_document_reference"));
        PermissionsDataList.add(new PermissionsData("د پوست د مرجع کتل", "دیدن مرجع پوست", "View all Post Reference", "view_all_document_references"));
        PermissionsDataList.add(new PermissionsData("د کارن حساب سمول", "تغییر حساب کاربری", "edit profile", "edit_profile"));
        PermissionsDataList.add(new PermissionsData("پټ نوم بدل کړی", "تغییر پسورد", "change password", "change_password"));
        PermissionsDataList.add(new PermissionsData("د کارونکي حالت بدل کړئ", "تغییر حالت کاربری", "change user status", "change_user_status"));
        PermissionsDataList.add(new PermissionsData("د فعالیت لاگ لیدل", "دیدن لاگ فعالیت های", "View Log", "log_view"));
        PermissionsDataList.add(new PermissionsData("کارن کتل", "دیدن کاربر", "view User", "getSingleUser"));

 


        for (PermissionsData PermissionsData : PermissionsDataList) {
            Permission existingPermission = permissionRepository
                    .findBypermissionName(PermissionsData.getPermissionName());
            if (existingPermission == null) {
                Permission newPermission = new Permission();
                newPermission.setPsName(PermissionsData.getPsName());
                newPermission.setDrName(PermissionsData.getDrName());
                newPermission.setEnName(PermissionsData.getEnName());
                newPermission.setPermissionName(PermissionsData.getPermissionName());
                permissions.add(permissionRepository.save(newPermission));
            } else {
                permissions.add(existingPermission);
            }
            Roles role = roleRepository.findByroleName("ROLE_ADMIN").orElseThrow(() -> new MyNotFoundException("role not found!"));
            if (role != null) {
               role.setPermissions(permissions); 
            }
        }
    }

    private void insertDefaultPermissions() {
       System.out.println("==================================================admin====================");
        List<PermissionsData> PermissionsDataList = new ArrayList<>();
        PermissionsDataList.add(new PermissionsData("ډیپارتمنت تفیرول", "تغیر دیپارتمنت", "Update Posts", "department_update"));
        PermissionsDataList.add(new PermissionsData("ډیپارتمنت کتل", "دیدن دیپارتمنت", "View Posts", "all_departments_view"));
        PermissionsDataList.add(new PermissionsData("وارده پوست", "پوست ها وارده", "Inbox", "document_inbox"));
        PermissionsDataList.add(new PermissionsData("پوست جوړول", "ایجاد پوست", "Create Document", "document_creation"));
        PermissionsDataList.add(new PermissionsData("د پوست لیدل", "دیدن پوست", "View Document", "document_view"));
        PermissionsDataList.add(new PermissionsData("د پوستونه لیدل", "دیدن لست پوست ها", "View Document list", "document_list"));
        PermissionsDataList.add(new PermissionsData("د پوست لیږل", "ارسال پوست", "Share Document", "document_share"));
        PermissionsDataList.add(new PermissionsData("صادره پوست", "پوست ها صادره", "Outbox", "document_outbox"));
        PermissionsDataList.add(new PermissionsData("د پوست تغیرول", "ویرایش پوست", "Edit Posts", "document_update"));
        PermissionsDataList.add(new PermissionsData("د پوست لری کول", "حذف پوست", "Delete Posts", "document_delete"));
        PermissionsDataList.add(new PermissionsData("د کمیته غړی", "عضو کمیته", "Committee Member", "committee_member"));
        PermissionsDataList.add(new PermissionsData("د محرم پوست لیدل", "دیدن پوست محرم", "View Secret", "secret_view"));
        PermissionsDataList.add(new PermissionsData("نظریه ورکول", "نظر دادن", "Create Comment", "comment_add"));
        PermissionsDataList.add(new PermissionsData(" راپور جوړول", "ایجاد گزارش", "Create Report", "report_add"));
        PermissionsDataList.add(new PermissionsData("د نظریی لیدل", "دیدن نظریه ها", "View Comments", "comment_view"));
        PermissionsDataList.add(new PermissionsData("د راپور لیدل", "دیدن گزارش", "View Reports", "report_view"));
        PermissionsDataList.add(new PermissionsData("د فعالیت لاگ لیدل ډیپارټمنټ په اساس", "دیدن لاگ فعالیت های به اساس ډیپارټمنټ", "View Log Department wise", "log_view_DepartmentWise"));
        PermissionsDataList.add(new PermissionsData("د پوست دیلیت", "حذف پوست", "Delete Posts", "document_delete"));
        PermissionsDataList.add(new PermissionsData("د لیږل شوی پوست دیلیت", "حذف پوست ها صادره", "Delete Send Posts ", "sendoc_delete"));
        PermissionsDataList.add(new PermissionsData("د لیږل شوی پوست تغیرول", "ویرایش پوست ها صادره", "Edite Send Posts", "sendDoc_update"));
        PermissionsDataList.add(new PermissionsData("دتاریخ تیر پوستونه لیدل", "دیدن پوست ها ختم میعاد", "view expired Posts list", "view_expired_document_list"));
        PermissionsDataList.add(new PermissionsData("د پوست تعقیب وګورئ", "دیدن تعقیب سند", "view Post tracking", "view_document_tracking"));
        PermissionsDataList.add(new PermissionsData("ضمیمه اضافه کول", "افزودن ضمیمه", "add append", "add_append"));
        PermissionsDataList.add(new PermissionsData("ضمیمه ړنګول", "حذف ضمیمه", "delete append", "delete_append"));
        PermissionsDataList.add(new PermissionsData("ضمیمه ړنګول", "افزودن پوست ها مرتبط", "add linked Posts", "add_linked_documents"));
        PermissionsDataList.add(new PermissionsData("تړل شوي پوستونه ړنګول", "حذف پوست ها مرتبط", "delete linked Posts", "delete_linked_documents"));
        PermissionsDataList.add(new PermissionsData("تړل شوي پوستونه وګورئ", "دیدن پوست ها مرتبط", "view linked Posts", "view_linked_documents"));
        PermissionsDataList.add(new PermissionsData("ځواب تبصره", "پاسخ نظر", "reply comment", "reply_comment"));
       
        for (PermissionsData PermissionsData : PermissionsDataList) {
            Permission existingPermission = permissionRepository
                    .findBypermissionName(PermissionsData.getPermissionName());
            if (existingPermission == null) {
                Permission newPermission = new Permission();
                newPermission.setPsName(PermissionsData.getPsName());
                newPermission.setDrName(PermissionsData.getDrName());
                newPermission.setEnName(PermissionsData.getEnName());
                newPermission.setPermissionName(PermissionsData.getPermissionName());
                permissionRepository.save(newPermission);
            }
        }
    }
}
