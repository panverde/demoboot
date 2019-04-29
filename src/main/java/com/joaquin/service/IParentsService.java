package com.joaquin.service;

import com.joaquin.dto.request.CreateParentRequest;
import com.joaquin.dto.request.UpdateParentsRequest;
import com.joaquin.dto.response.ListParentsResponse;
import com.joaquin.model.Parents;

import java.util.List;

public interface IParentsService extends Crud<Parents> {

  List<ListParentsResponse> findAlls();

  void createParent(CreateParentRequest obj);

  void updateParent(Integer id, UpdateParentsRequest obj);

}
