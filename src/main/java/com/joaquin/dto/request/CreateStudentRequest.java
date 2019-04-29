package com.joaquin.dto.request;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CreateStudentRequest {

  private Integer studentId;

  private String gender;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

  private String otherStudentDetails;

  private List<ParentListRequest> ltaStudentParents;

  @Data
  public static class ParentListRequest {

    private Integer parentId;

  }

}
