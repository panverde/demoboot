package com.joaquin.dto.request;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UpdateStudentsRequest {

  private Integer studentId;

  private String gender;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

  private String otherStudentDetails;

  private List<ParentUpdateRequest> ltaStudentParents;

  @Data
  public static class ParentUpdateRequest {

    private Integer parentId;

  }

}



