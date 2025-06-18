package com.project.GovNetMISApplication.APIsForAdmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class committeeMembersDetails {
    String firstName;
    String lastName;
    String email;
    List<String> depName;
}
