package com.joaquin.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.dto.request.CreateStudentRequest;
import com.joaquin.dto.request.UpdateStudentsRequest;
import com.joaquin.dto.response.ListStudentsResponse;
import com.joaquin.model.Students;
import com.joaquin.service.impl.StudentsServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/students")
@Api(value="StudentsController")
public class StudentsController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private StudentsServiceImpl studentService;
  
  /**
   * Controller StudentsController javadoc joaquin.com
   */

  @ApiOperation(value = "See the list of all students.")
  @GetMapping(value = "/list")
  public List<Students> listAllStudentById(@RequestParam(value="Id") List<Integer> id) {
  
	  return studentService.findListStudents(id);
  
  }
  

  /**
   * Controller StudentsController javadoc joaquin.com
   */

  @ApiOperation(value = "See the list of all students.")
  @GetMapping(value = "/")
  public ResponseEntity<List<ListStudentsResponse>> listAllStudents() {
    return new ResponseEntity<List<ListStudentsResponse>>(studentService
            .findAllStudents(), HttpStatus.OK);
  }

  /**
   * Controller StudentsController create Student javadoc joaquin.com
   */

  @ApiOperation(value = "create a new student.")
  @PostMapping(value = "/")
  public @ResponseBody
  void createStudent(@RequestBody CreateStudentRequest students)
          throws URISyntaxException {
    studentService.createStudent(students);
  }

  /**
   * Controller StudentsController Update student by Id javadoc joaquin.com
   */

  @ApiOperation(value = "update student information.")
  @PutMapping(path = "/update/{studentsId}")
  public void updateExistStudent(@RequestBody UpdateStudentsRequest students,
                                 @PathVariable Integer studentsId) {
    try {
      students.setStudentId(studentsId);
      studentService.updateStudent(studentsId, students);
      logger.info("update");
    } catch (Exception e) {
      logger.info("error");
    }
  }

  /**
   * Controller StudentsController delete student by Id javadoc joaquin.com
   */
  @ApiOperation(value = "delete existing student.")
  @DeleteMapping("/delete/{studentsId}")
  public ResponseEntity<Void> deleteStudents(@PathVariable("studentsId") Integer id) {
    logger.info("> delete [persona]");
    try {
      studentService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }


}
