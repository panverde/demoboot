package com.joaquin.controller;

import com.joaquin.dto.request.CreateParentRequest;
import com.joaquin.dto.request.UpdateParentsRequest;
import com.joaquin.dto.response.ListParentsResponse;

import com.joaquin.service.impl.ParentsServiceImpl;

import com.joaquin.validate.ValidationException;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/parents")
public class ParentsController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ParentsServiceImpl parentsService;


  @GetMapping(value = "/")
  public ResponseEntity<List<ListParentsResponse>> listAllParents() {
    return new ResponseEntity<List<ListParentsResponse>>(parentsService.findAlls(), HttpStatus.OK);
  }

  /**
   * Controller ParentsController update by parentsId javadoc joaquin.com
   */

  @PutMapping(path = "/update/{parentsId}")
  public void updateExistParents(@RequestBody UpdateParentsRequest parents,
                                 @PathVariable Integer parentsId) {
    try {
      parents.setParentId(parentsId);
      parentsService.updateParent(parentsId, parents);
      logger.info("update parent parentsId : " + parentsId);
    } catch (Exception e) {
      logger.warn("error update parentsId : " + parentsId);
    }
  }

  /**
   * Controller ParentsController post create parents javadoc joaquin.com
   */

  @PostMapping(value = "/")
  public void createParents(@RequestBody @Validated CreateParentRequest parents,
                            BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      log.info("There are errors");
      for (FieldError e : bindingResult.getFieldErrors()) {
        log.info(e.getField());
        log.info(e.getCode());
        log.info(e.getDefaultMessage());
        log.info(e.getObjectName());
        throw new ValidationException("error in the validated field");
      }
    } else {
      parentsService.createParent(parents);
    }
  }

  /**
   * Controller ParentsController delete parents by Id javadoc joaquin.com
   */

  @DeleteMapping("/delete/{parentsId}")
  public ResponseEntity<Void> deleteParents(@PathVariable("parentsId") Integer id) {

    try {
      parentsService.delete(id);
      logger.info("> delete [parents]");
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      logger.info("> error delete [parents]:" + id + " " + e);
      return ResponseEntity.notFound().build();
    }
  }

}
