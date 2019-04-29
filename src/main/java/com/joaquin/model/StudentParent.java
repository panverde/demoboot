package com.joaquin.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student_parents")
public class StudentParent {

  @EmbeddedId
  private StudentParentPk primaryKey;

}
