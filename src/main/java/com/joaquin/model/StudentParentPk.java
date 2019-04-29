package com.joaquin.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class StudentParentPk implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@ManyToOne
  @JoinColumn(name = "student_id")
  private Students studentId;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Parents parentId;


}
