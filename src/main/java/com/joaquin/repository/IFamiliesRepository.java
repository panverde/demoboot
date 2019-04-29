package com.joaquin.repository;

import com.joaquin.model.Families;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IFamiliesRepository extends JpaRepository<Families, Integer> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM families f WHERE f.head_of_family_parent_id =?1", nativeQuery = true)
  void deleteFamilyByIdParent(Integer id);


}
