package com.joaquin.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "students")
@NamedQuery(name="Students.listStudentByListId",
			query="Select s from Students s WHERE studentId in (?1)")
public class Students {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "student_id")
  private Integer studentId;
  @Column(name = "gender", length = 20, nullable = false)
  private String gender;
  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;
  @Column(name = "middle_name", length = 50, nullable = false)
  private String middleName;
  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;
  @Column(name = "date_of_birth", length = 50, nullable = false)
  private Date dateOfBirth;
  @Column(name = "other_student_details", length = 150, nullable = true)
  private String otherStudentDetails;
  @JsonIgnore
  @OneToMany(mappedBy = "primaryKey.parentId", fetch = FetchType.EAGER)
  private List<StudentParent> ltaStudentParents;

  public Students() {
  }

  /**
   * Constructor Students javadoc joaquin.com
   */

  public Students(String gender, String firstName, String middleName, String lastName,
                  Date dateOfBirth, String otherStudentDetails,
                  List<StudentParent> ltaStudentParents) {
    this.gender = gender;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.otherStudentDetails = otherStudentDetails;
    this.ltaStudentParents = ltaStudentParents;
  }
}
