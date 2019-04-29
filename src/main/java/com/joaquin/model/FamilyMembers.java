package com.joaquin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "family_members")
public class FamilyMembers {
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer familyMemberId;
  @ManyToOne()
  @JoinColumn(name = "family_id")
  private Families familyId;
  @Column(length = 30, name = "parent_or_student_member")
  private String parentOrStudentMember;
  @ManyToOne()
  @JoinColumn(name = "parent_id")
  @JsonIgnoreProperties("ltaStudentParents")
  private Parents parentId;
  @JsonIgnoreProperties("ltaStudentParents")
  @ManyToOne()
  @JoinColumn(name = "student_id")
  private Students studentId;


}
