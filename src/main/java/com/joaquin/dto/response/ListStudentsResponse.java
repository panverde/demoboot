package com.joaquin.dto.response;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ListStudentsResponse {

  private Integer studentId;

  private String gender;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

  private String otherStudentDetails;

  private List<ParentListResponse> ltaStudentParents;

  @Data
  public static class ParentListResponse {

    private Integer parentId;
    private String lastName;


  }

}
