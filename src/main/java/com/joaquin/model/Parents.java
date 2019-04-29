package com.joaquin.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "parents")
public class Parents {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "parent_id")
  private Integer parentId;

  @Column(name = "gender", length = 20, nullable = false)
  private String gender;

  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;

  @Column(name = "middle_name", length = 50, nullable = false)
  private String middleName;

  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;

  @Column(name = "other_parent_details", length = 150, nullable = true)
  private String otherParentDetails;

  //@JsonIgnore
  @OneToMany(mappedBy = "primaryKey.studentId", fetch = FetchType.EAGER)
  private List<StudentParent> ltaStudentParents;

  public Parents() {
  }

  /**
   * Constructor Parents javadoc joaquin.com
   */

  public Parents(String gender, String firstName, String middleName, String lastName,
                 String otherParentDetails, List<StudentParent> ltaStudentParents) {
    this.gender = gender;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.otherParentDetails = otherParentDetails;
    this.ltaStudentParents = ltaStudentParents;
  }
}
