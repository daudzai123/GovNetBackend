package com.project.DocumentMIS.user;

import com.project.DocumentMIS.Departments.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    UserDetails findByEmail(String email);
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmails(String email);

    List<Users> findAllByOrderByIdAsc();

    Page<Users> findAll(Pageable pageable);

    List<Users> findByDepartmentDepId(Long id);

    Users findByOtpCode(String otpCode);

    @Query("SELECT u.department FROM Users u WHERE u.id = :userId")
    List<Department> findDepartmentsByUserId(Long userId);
//    @Query("SELECT u FROM Users u WHERE LOWER(u.firstName) LIKE LOWER(:searchTerm) OR LOWER(u.lastName) LIKE LOWER(:searchTerm)")
    @Query("SELECT u FROM Users u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")

    List<Users> searchUsers(@Param("searchTerm") String searchTerm);
    long countByActivateTrue();
    long countByActivateFalse();
    @Query("SELECT u FROM Users u WHERE u.userType ='MEMBER_OF_COMMITTEE' ")
    List<Users> findByUserType();

}