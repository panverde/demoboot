package com.joaquin.service.impl;

import com.joaquin.dto.request.CreateStudentRequest;
import com.joaquin.dto.request.UpdateStudentsRequest;
import com.joaquin.dto.response.ListStudentsResponse;
import com.joaquin.model.Parents;
import com.joaquin.model.StudentParent;
import com.joaquin.model.StudentParentPk;
import com.joaquin.model.Students;
import com.joaquin.repository.IFamilyMembersRepository;
import com.joaquin.repository.IStudentParentsRepository;
import com.joaquin.repository.IStudentsRepository;
import com.joaquin.service.IStudentsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class StudentsServiceImpl implements IStudentsService {


  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IStudentsRepository repositoryStudents;

  @Autowired
  private IStudentParentsRepository repositoryStudentParent;

  @Autowired
  private IFamilyMembersRepository repositoryFamilyMembers;

  @Override
  public List<ListStudentsResponse> findAllStudents() {

    List<Students> listStudents = repositoryStudents.findAll();
    List<ListStudentsResponse> listStudentsResponses = null;

    if (listStudents != null) {

      listStudentsResponses = new ArrayList<ListStudentsResponse>();
      //ListStudentsResponse listStudentsResponseLs=null;

      for (Students students : listStudents) {
        ListStudentsResponse listStudentsResponseLs = new ListStudentsResponse();

        listStudentsResponseLs.setStudentId(students.getStudentId());
        listStudentsResponseLs.setFirstName(students.getFirstName());
        listStudentsResponseLs.setGender(students.getGender());
        listStudentsResponseLs.setMiddleName(students.getMiddleName());
        listStudentsResponseLs.setLastName(students.getLastName());
        listStudentsResponseLs.setDateOfBirth(students.getDateOfBirth());
        listStudentsResponseLs.setOtherStudentDetails(students.getOtherStudentDetails());

        //obtener la lista de parents buscadas por studentId
        List<StudentParent> listParents = repositoryStudentParent
                .findAllByPrimaryKeyStudentIdStudentId(students.getStudentId());
        //System.out.println(listParents);

        for (StudentParent list : listParents) {

          List<ListStudentsResponse.ParentListResponse> listParent = new ArrayList<>();
          if (list.getPrimaryKey().getParentId() != null) {
            ListStudentsResponse.ParentListResponse parent
                    = new ListStudentsResponse.ParentListResponse();
            parent.setParentId(list.getPrimaryKey().getParentId().getParentId());
            parent.setLastName(list.getPrimaryKey().getParentId().getFirstName());

            listParent.add(parent);
          }

          listStudentsResponseLs.setLtaStudentParents(listParent);

        }
        listStudentsResponses.add(listStudentsResponseLs);
      }

    }
    return listStudentsResponses;
  }

  @Override
  public void updateStudent(Integer id, UpdateStudentsRequest obj) {

    Optional<Students> studentsOptional = repositoryStudents.findById(id);//control de exception

    if (studentsOptional.isPresent()) {
      Students studentsBase = studentsOptional.get();
      studentsBase.setGender(obj.getGender());
      studentsBase.setFirstName(obj.getFirstName());
      studentsBase.setLastName(obj.getLastName());
      studentsBase.setDateOfBirth(obj.getDateOfBirth());
      studentsBase.setOtherStudentDetails(obj.getOtherStudentDetails());
      studentsBase.setMiddleName(obj.getMiddleName());

      repositoryStudents.save(studentsBase);

      for (UpdateStudentsRequest.ParentUpdateRequest r : obj.getLtaStudentParents()) {

        StudentParentPk primaryKey = new StudentParentPk();
        primaryKey.setStudentId(studentsBase);
        Parents parents = new Parents();
        parents.setParentId(r.getParentId());
        primaryKey.setParentId(parents);

        StudentParent studentParent = new StudentParent();
        studentParent.setPrimaryKey(primaryKey);
        repositoryStudentParent.save(studentParent);

      }
      logger.info("Update");
    } else {
      logger.warn("ID not found");
    }

  }

  @Override
  public void createStudent(CreateStudentRequest obj) {

    Students students = new Students();
    students.setStudentId(obj.getStudentId());
    students.setLastName(obj.getLastName());
    students.setFirstName(obj.getFirstName());
    students.setGender(obj.getGender());
    students.setMiddleName(obj.getMiddleName());
    students.setDateOfBirth(obj.getDateOfBirth());
    students.setOtherStudentDetails(obj.getOtherStudentDetails());

    students = repositoryStudents.save(students);

    //System.out.println(obj.getLtaStudentParents());

    if (obj.getLtaStudentParents() != null) {

      for (CreateStudentRequest.ParentListRequest p : obj.getLtaStudentParents()) {

        StudentParentPk primaryKey = new StudentParentPk();
        primaryKey.setStudentId(students);
        Parents parents = new Parents();
        parents.setParentId(p.getParentId());
        primaryKey.setParentId(parents);

        StudentParent studentParent = new StudentParent();
        studentParent.setPrimaryKey(primaryKey);
        repositoryStudentParent.save(studentParent);

      }
    }


  }

  @Override
  public List<Students> findAll() {

    return repositoryStudents.findAll();
  }

  @Override
  public Students create(Students obj) {
    return repositoryStudents.save(obj);
  }

  @Override
  public Students find(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Students obj, Integer id) {
    Optional<Students> studentsOptional = repositoryStudents.findById(id);

    if (studentsOptional.isPresent()) {

      Students studentsBase = studentsOptional.get();
      studentsBase.setLastName(obj.getLastName());
      studentsBase.setFirstName(obj.getFirstName());
      studentsBase.setGender(obj.getGender());
      studentsBase.setMiddleName(obj.getMiddleName());
      studentsBase.setDateOfBirth(obj.getDateOfBirth());
      studentsBase.setOtherStudentDetails(obj.getOtherStudentDetails());

      repositoryStudents.save(studentsBase);
      logger.info("update student id:" + id);

    } else {
      logger.error("error update student");
    }

  }

  @Override
  public void delete(Integer id) {

    Optional<Students> studentsOptional = repositoryStudents.findById(id);

    if (studentsOptional.isPresent()) {

      repositoryFamilyMembers.deleteFamilyMembersByIdStudents(id);
      repositoryStudentParent.deleteStudentParentByIdStudent(id);
      repositoryStudents.deleteById(id);
      logger.info("delete student");
    } else {
      logger.error("Not found student id");
    }


  }

@Override
public List<Students> findListStudents(List<Integer> id) {
	
	return repositoryStudents.listStudentByListId(id);
}

}
