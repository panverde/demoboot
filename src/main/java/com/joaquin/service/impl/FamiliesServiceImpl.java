package com.joaquin.service.impl;

import com.joaquin.model.Families;

import com.joaquin.repository.IFamiliesRepository;
import com.joaquin.repository.IFamilyMembersRepository;
import com.joaquin.service.IFamiliesService;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class FamiliesServiceImpl implements IFamiliesService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IFamiliesRepository repositoryFamilies;
  @Autowired
  private IFamilyMembersRepository repositoryFamilyMembers;


  @Override
  public List<Families> findAll() {
    // TODO Auto-generated method stub
    return repositoryFamilies.findAll();
  }

  @Override
  public Families create(Families obj) {
    // TODO Auto-generated method stub
    return repositoryFamilies.save(obj);
  }

  @Override
  public Families find(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void update(Families obj, Integer id) {
    // TODO Auto-generated method stub
    repositoryFamilies.save(obj);
  }

  @Override
  public void delete(Integer id) {
    Optional<Families> familiesOptional = repositoryFamilies.findById(id);
    if (familiesOptional.isPresent()) {

      repositoryFamilyMembers.deleteFamilyMembersByIdFamilies(id);
      repositoryFamilies.deleteById(id);
      logger.info("delete families");

    } else {
      logger.error("Not found families id");
    }
  }

}
