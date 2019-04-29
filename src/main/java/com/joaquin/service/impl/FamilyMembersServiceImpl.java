package com.joaquin.service.impl;

import com.joaquin.model.FamilyMembers;
import com.joaquin.repository.IFamilyMembersRepository;
import com.joaquin.service.IFamilyMembersService;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class FamilyMembersServiceImpl implements IFamilyMembersService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IFamilyMembersRepository repositoryFamilyMembers;

  @Override
  public List<FamilyMembers> findAll() {
    // TODO Auto-generated method stub
    return repositoryFamilyMembers.findAll();
  }

  @Override
  public FamilyMembers create(FamilyMembers obj) {
    // TODO Auto-generated method stub
    return repositoryFamilyMembers.save(obj);
  }

  @Override
  public FamilyMembers find(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(FamilyMembers obj, Integer id) {
    // TODO Auto-generated method stub
    repositoryFamilyMembers.save(obj);
  }

  @Override
  public void delete(Integer id) {

    Optional<FamilyMembers> familyMembersOptional = repositoryFamilyMembers.findById(id);
    if (familyMembersOptional.isPresent()) {
      logger.info("delete familymembers");
      repositoryFamilyMembers.deleteById(id);
    } else {
      logger.error("Not found familymembers id");
    }

  }

  @Override
  public List<FamilyMembers> listFamilyMembersbyFamilyId(Integer id) {
    return repositoryFamilyMembers.selectFamilyMembersByFamilyId(id);
  }
}
