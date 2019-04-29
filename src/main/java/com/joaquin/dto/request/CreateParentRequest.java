package com.joaquin.dto.request;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateParentRequest {

  private Integer parentId;

  @Size(max = 1,message = "gender 1 caracter")
  @NotEmpty(message = "empty field")
  private String gender;
  @NotEmpty(message = "empty field")
  private String firstName;
  @NotEmpty(message = "empty field")
  private String middleName;
  @NotEmpty(message = "empty field")
  private String lastName;
  private String otherParentDetails;

  private List<StudentListRequest> ltaStudentParents;

  @Data
  public static class StudentListRequest {

    private Integer studentId;

  }

}
