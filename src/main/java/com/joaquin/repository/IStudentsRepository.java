package com.joaquin.repository;

import com.joaquin.model.Students;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentsRepository extends JpaRepository<Students, Integer> {
	
	List<Students> listStudentByListId(List<Integer> id);

}
