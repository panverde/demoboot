package com.joaquin.repository;

import com.joaquin.model.StudentParent;

import com.joaquin.model.StudentParentPk;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IStudentParentsRepository extends JpaRepository<StudentParent, StudentParentPk> {

  List<StudentParent> findAllByPrimaryKeyStudentIdStudentId(Integer id);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM student_parents f WHERE f.student_id =?1", nativeQuery = true)
  void deleteStudentParentByIdStudent(Integer id);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM student_parents f WHERE f.parent_id =?1", nativeQuery = true)
  void deleteStudentParentByIdParent(Integer id);

  List<StudentParent> findAllByPrimaryKeyParentIdParentId(Integer id);

}
