package com.joaquin.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.model.Families;
import com.joaquin.service.impl.FamiliesServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/families")
public class FamiliesController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private FamiliesServiceImpl familiesService;

  @ApiOperation("Is Alive operation”, notes = “Return is the microservice is alive with a get operation returning the time")
  @GetMapping(value = "/")
  public ResponseEntity<List<Families>> listAllFamilies() {
    return new ResponseEntity<List<Families>>(familiesService.findAll(), HttpStatus.OK);
  }

  /**
   * Controller Families create javadoc joaquin.com
   */

  @PostMapping(value = "/")
  public void createFamilies(@RequestBody Families families) {
    try {
      // guardar en base de datos
      familiesService.create(families);
      logger.info("Families Create");
    } catch (Exception e) {
      logger.error("Families create error " + e);
    }
  }

  /**
   * Controller Families update by familiesId javadoc joaquin.com
   */

  @PutMapping(path = "/update/{familiesId}")
  public void updateExistFamilies(@RequestBody Families families,
                                  @PathVariable Integer familiesId) {
    try {
      families.setFamilyId(familiesId);
      familiesService.update(families, familiesId);
      logger.info("Update Families familiesId : " + familiesId);
    } catch (Exception e) {
      logger.error("Families update error: " + e);
    }
  }

  /**
   * Controller Families Delete by IdFamily javadoc joaquin.com
   */

  @DeleteMapping("/delete/{familiesId}")
  public ResponseEntity<Void> deleteFamilies(@PathVariable("familiesId") Integer id) {
    logger.info("> delete [persona]");
    try {
      familiesService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

}
