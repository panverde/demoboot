package com.joaquin.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ListParentsResponse {

  private Integer parentId;

  private String gender;

  private String firstName;

  private String middleName;

  private String lastName;

  private String otherParentDetails;

  private List<StudentListResponse> ltaStudentParents;

  @Data
  public static class StudentListResponse {

    private Integer studentId;
    private String lastName;


  }

}
