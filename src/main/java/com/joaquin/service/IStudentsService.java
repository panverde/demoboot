package com.joaquin.service;

import com.joaquin.dto.request.CreateStudentRequest;
import com.joaquin.dto.request.UpdateStudentsRequest;
import com.joaquin.dto.response.ListStudentsResponse;
import com.joaquin.model.Students;

import java.util.List;

public interface IStudentsService extends Crud<Students> {

  List<ListStudentsResponse> findAllStudents();

  void updateStudent(Integer id, UpdateStudentsRequest obj);

  void createStudent(CreateStudentRequest obj);
  
  List<Students> findListStudents(List<Integer> id);

}
