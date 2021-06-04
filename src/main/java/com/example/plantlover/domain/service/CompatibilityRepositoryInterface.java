package com.example.plantlover.domain.service;

import com.example.plantlover.data.repository.DalException;
import com.example.plantlover.domain.entity.Compatibility;
import com.example.plantlover.domain.entity.RelationEnum;

import java.util.List;

public interface CompatibilityRepositoryInterface {

    Compatibility save(Compatibility compatibility);

    Compatibility findById(Integer id) throws DalException;

    List<Compatibility> findAll();

    void delete(Compatibility compatibility);

    List<Compatibility> findAllAssociations(String plantName, RelationEnum relation);

    String getRelation(String plantAName, String plantBName);

}
