package com.joaquin.controller;

import com.joaquin.model.FamilyMembers;
import com.joaquin.service.impl.FamilyMembersServiceImpl;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/familymembers")
public class FamilyMembersController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private FamilyMembersServiceImpl familyMembersService;

  /**
   * Controller FamilyMembersController javadoc joaquin.com
   */

  @GetMapping("/{familyID}")
  public ResponseEntity<List<FamilyMembers>> listFamilyMembersByFamilyId(
          @PathVariable("familyID") Integer id) {

    return new ResponseEntity<List<FamilyMembers>>(familyMembersService
            .listFamilyMembersbyFamilyId(id), HttpStatus.OK);

  }

  @GetMapping(value = "/")
  public ResponseEntity<List<FamilyMembers>> listAllFamilyMembers() {
    return new ResponseEntity<List<FamilyMembers>>(familyMembersService.findAll(), HttpStatus.OK);
  }

  /**
   * Controller FamilyMembersController create FamilyMembers javadoc joaquin.com
   */

  @PostMapping(value = "/")
  public void createFamilyMembers(@RequestBody FamilyMembers familyMembers)
          throws URISyntaxException {
    try {
      // guardar en base de datos
      familyMembersService.create(familyMembers);
      logger.info("FamilyMembers Create");
    } catch (Exception e) {
      // Excepcion retorna error (409)
      logger.error("FamilyMembers create ERROR" + e);
    }
  }

  /**
   * Controller FamilyMembersController update by familyMembersId javadoc joaquin.com
   */

  @PutMapping(path = "/update/{familyMembersId}")
  public void updateExistFamilyMembers(@RequestBody FamilyMembers familyMembers,
                                                       @PathVariable Integer familyMembersId) {
    try {
      familyMembers.setFamilyMemberId(familyMembersId);
      familyMembersService.update(familyMembers, familyMembersId);
      logger.info("FamilyMembers Update");
    } catch (Exception e) {
      logger.error("FamilyMember Error" + e);
    }
  }

  /**
   * Controller FamilyMembersController delete by familyMemberId javadoc joaquin.com
   */

  @DeleteMapping("/delete/{familyMembersId}")
  public ResponseEntity<Void> deleteFamilyMembers(@PathVariable("familyMembersId") Integer id) {
    logger.info("> delete [persona]");
    try {
      familyMembersService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

}
