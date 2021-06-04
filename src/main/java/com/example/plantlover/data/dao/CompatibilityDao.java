package com.example.plantlover.data.dao;

import com.example.plantlover.domain.entity.Compatibility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompatibilityDao extends CrudRepository<Compatibility, Integer> {
}
