package com.joaquin.service;

import java.util.List;

public interface Crud<T> {

  List<T> findAll();

  T create(T obj);

  T find(Integer id);

  void update(T obj, Integer id);

  void delete(Integer id);
}
