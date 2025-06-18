package com.project.GovNetMISApplication.APIsForAdmin;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    Long totalUsers;
    Long activeUsers;
    Long nonActiveUsers;
    Long totalDepartments;
    Long totalRoles;
    Long totalSpaceInMb;
    List<committeeMembersDetails> committeeMembersDetailsList;
}
