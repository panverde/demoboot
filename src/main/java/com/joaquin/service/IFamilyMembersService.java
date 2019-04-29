package com.joaquin.service;

import com.joaquin.model.FamilyMembers;

import java.util.List;

public interface IFamilyMembersService extends Crud<FamilyMembers> {

  List<FamilyMembers> listFamilyMembersbyFamilyId(Integer id);

}
