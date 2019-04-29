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
@Table(name = "families")
public class Families {
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "family_id")
  private Integer familyId;
  @JsonIgnoreProperties("ltaStudentParents")
  @ManyToOne()
  @JoinColumn(name = "head_of_family_parent_id")
  private Parents headOfFamilyParentId;
  @Column(length = 50, name = "family_name")
  private String familyName;

}