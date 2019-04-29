package com.joaquin.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class UpdateParentsRequest {

  private Integer parentId;

  private String gender;

  private String firstName;

  private String middleName;

  private String lastName;

  private String otherParentDetails;

  private List<StudentUpdateRequest> ltaStudentParents;

  @Data
  public static class StudentUpdateRequest {

    private Integer studentId;

  }
}
