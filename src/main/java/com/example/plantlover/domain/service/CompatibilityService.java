package com.example.plantlover.domain.service;

import com.example.plantlover.data.repository.DalException;
import com.example.plantlover.domain.entity.Compatibility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityService {

    private final CompatibilityRepositoryInterface repository;

    public CompatibilityService(CompatibilityRepositoryInterface repository) {
        this.repository = repository;
    }

    public Compatibility create(Compatibility compatibility) {
        return repository.save(compatibility);
    }

    public Compatibility readOne(Integer id) throws DalException {
        return repository.findById(id);
    }

    public List<Compatibility> readAll() {
        return repository.findAll();
    }

    public Compatibility update(Integer id, Compatibility compatibility) throws BllException {
        try {
            Compatibility compatibilityFromDb = repository.findById(id);
            compatibility.setId(compatibilityFromDb.getId());
            return create(compatibility);
        } catch (DalException e) {
            throw new BllException("You want to update an entity that do not exist. EntityId = " + id);
        }
    }

    public void delete(Integer id) throws BllException {
        try {
            Compatibility compatibility = repository.findById(id);
            repository.delete(compatibility);
        } catch (DalException e) {
            throw new BllException("You want to delete an entity that do not exist. EntityId = " + id);
        }
    }

}
