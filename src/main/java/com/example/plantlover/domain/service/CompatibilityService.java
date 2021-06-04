package com.example.plantlover.domain.service;

import com.example.plantlover.data.repository.DalException;
import com.example.plantlover.domain.entity.Compatibility;
import com.example.plantlover.domain.entity.RelationEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompatibilityService {

    private final CompatibilityRepositoryInterface repository;

    public CompatibilityService(CompatibilityRepositoryInterface repository) {
        this.repository = repository;
    }

    public Compatibility create(Compatibility compatibility) throws BllException {
        if (compatibility.getPlantA().equals(compatibility.getPlantB())) {
            throw new BllException("Off course a plant cant be associated with itself! Try again.");
        }
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

    public List<String> findAllFriends(String plantName) {
        return findAllByRelation(plantName, RelationEnum.FRIENDS);
    }

    public List<String> findAllEnemies(String plantName) {
        return findAllByRelation(plantName, RelationEnum.ENEMIES);
    }

    public String getRelation(String plantAName, String plantBName) {
        return repository.getRelation(plantAName, plantBName);
    }

    private List<String> findAllByRelation(String plantName, RelationEnum relation) {
        List<Compatibility> compatibilities = repository.findAllAssociations(plantName, relation);
        return compatibilities.stream().map(compatibility -> {
            if (compatibility.getPlantA().equals(plantName)) return compatibility.getPlantB();
            else return compatibility.getPlantA();
        }).collect(Collectors.toList());
    }

}
