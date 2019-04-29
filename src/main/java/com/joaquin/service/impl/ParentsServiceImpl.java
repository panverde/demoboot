package com.joaquin.service.impl;

import com.joaquin.dto.request.CreateParentRequest;
import com.joaquin.dto.request.UpdateParentsRequest;
import com.joaquin.dto.response.ListParentsResponse;
import com.joaquin.model.Parents;
import com.joaquin.model.StudentParent;
import com.joaquin.model.StudentParentPk;
import com.joaquin.model.Students;
import com.joaquin.repository.IFamiliesRepository;
import com.joaquin.repository.IFamilyMembersRepository;
import com.joaquin.repository.IParentsRepository;
import com.joaquin.repository.IStudentParentsRepository;
import com.joaquin.service.IParentsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ParentsServiceImpl implements IParentsService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IParentsRepository repositoryParents;
  @Autowired
  private IFamiliesRepository repositoryFamilies;
  @Autowired
  private IFamilyMembersRepository repositoryFamilyMembers;
  @Autowired
  private IStudentParentsRepository repositoryStudentParents;

  @Override
  public List<Parents> findAll() {

    return repositoryParents.findAll();
  }

  @Override
  public Parents create(Parents obj) {
    // TODO Auto-generated method stub
    return repositoryParents.save(obj);
  }

  @Override
  public Parents find(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Parents obj, Integer id) {
    // TODO Auto-generated method stub
    repositoryParents.save(obj);
  }

  @Override
  public void delete(Integer id) {

    Optional<Parents> parentsOptional = repositoryParents.findById(id);

    if (parentsOptional.isPresent()) {

      repositoryFamilyMembers.deleteFamilyMembersByIdParents(id);
      repositoryFamilies.deleteFamilyByIdParent(id);
      repositoryStudentParents.deleteStudentParentByIdParent(id);
      repositoryParents.deleteById(id);

      logger.info("delete parent");

    } else {
      logger.error("Not found parent id");
    }

  }

  @Override
  public List<ListParentsResponse> findAlls() {
    List<Parents> listParents = repositoryParents.findAll();
    List<ListParentsResponse> listParentsResponses = null;
    if (listParents != null) {

      listParentsResponses = new ArrayList<ListParentsResponse>();
      //ListStudentsResponse listStudentsResponseLs=null;

      for (Parents parents : listParents) {
        ListParentsResponse listParentsResponseLs = new ListParentsResponse();

        listParentsResponseLs.setParentId(parents.getParentId());
        listParentsResponseLs.setFirstName(parents.getFirstName());
        listParentsResponseLs.setGender(parents.getGender());
        listParentsResponseLs.setMiddleName(parents.getMiddleName());
        listParentsResponseLs.setLastName(parents.getLastName());
        listParentsResponseLs.setOtherParentDetails(parents.getOtherParentDetails());

        //obtener la lista de parents buscadas por studentId
        List<StudentParent> listStudents = repositoryStudentParents
                .findAllByPrimaryKeyParentIdParentId(parents.getParentId());

        for (StudentParent list : listStudents) {

          List<ListParentsResponse.StudentListResponse> listStudent = new ArrayList<>();
          if (list.getPrimaryKey().getParentId() != null) {
            ListParentsResponse.StudentListResponse student
                    = new ListParentsResponse.StudentListResponse();
            student.setStudentId(list.getPrimaryKey().getStudentId().getStudentId());
            student.setLastName(list.getPrimaryKey().getStudentId().getFirstName());

            listStudent.add(student);
          }

          listParentsResponseLs.setLtaStudentParents(listStudent);

        }
        listParentsResponses.add(listParentsResponseLs);
      }


    }
    return listParentsResponses;
  }

  @Override
  public void createParent(CreateParentRequest obj) {

    Parents parents = new Parents();
    parents.setParentId(obj.getParentId());
    parents.setLastName(obj.getLastName());
    parents.setFirstName(obj.getFirstName());
    parents.setGender(obj.getGender());
    parents.setMiddleName(obj.getMiddleName());
    parents.setOtherParentDetails(obj.getOtherParentDetails());

    parents = repositoryParents.save(parents);

    //System.out.println(obj.getLtaStudentParents());


    if (obj.getLtaStudentParents() != null) {

      for (CreateParentRequest.StudentListRequest p : obj.getLtaStudentParents()) {

        StudentParentPk primaryKey = new StudentParentPk();
        primaryKey.setParentId(parents);
        Students students = new Students();
        students.setStudentId(p.getStudentId());
        primaryKey.setStudentId(students);

        StudentParent studentParent = new StudentParent();
        studentParent.setPrimaryKey(primaryKey);
        repositoryStudentParents.save(studentParent);

      }
    }


  }

  @Override
  public void updateParent(Integer id, UpdateParentsRequest obj) {

    Optional<Parents> parentsOptional = repositoryParents.findById(id);//control de exception

    if (parentsOptional.isPresent()) {
      Parents parentsBase = parentsOptional.get();
      parentsBase.setGender(obj.getGender());
      parentsBase.setFirstName(obj.getFirstName());
      parentsBase.setLastName(obj.getLastName());
      parentsBase.setOtherParentDetails(obj.getOtherParentDetails());
      parentsBase.setMiddleName(obj.getMiddleName());

      repositoryParents.save(parentsBase);

      for (UpdateParentsRequest.StudentUpdateRequest p : obj.getLtaStudentParents()) {
        StudentParentPk primaryKey = new StudentParentPk();
        primaryKey.setParentId(parentsBase);
        Students students = new Students();
        students.setStudentId(p.getStudentId());
        primaryKey.setStudentId(students);

        StudentParent studentParent = new StudentParent();
        studentParent.setPrimaryKey(primaryKey);
        repositoryStudentParents.save(studentParent);

      }

      logger.info("Update");
    } else {
      logger.warn("ID not found");
    }


  }
}