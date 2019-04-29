package com.joaquin.repository;

import com.joaquin.model.FamilyMembers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IFamilyMembersRepository extends JpaRepository<FamilyMembers, Integer> {

  @Modifying
  @Transactional
  @Query(value = "delete from family_members fm where fm.family_id = ?1", nativeQuery = true)
  void deleteFamilyMembersByIdFamilies(Integer id);

  @Modifying
  @Transactional
  @Query(value = "delete from family_members fm where fm.parent_id = ?1", nativeQuery = true)
  void deleteFamilyMembersByIdParents(Integer id);

  @Modifying
  @Transactional
  @Query(value = "delete from family_members fm where fm.student_id = ?1", nativeQuery = true)
  void deleteFamilyMembersByIdStudents(Integer id);

  @Modifying
  @Transactional
  @Query(value = "select* from family_members fm where fm.family_id = ?1", nativeQuery = true)
  List<FamilyMembers> selectFamilyMembersByFamilyId(Integer id);

  //FamilyMembers JPA
  //List<FamilyMembers> findAllByFamilyIdFamilyId(Integer id);

}
