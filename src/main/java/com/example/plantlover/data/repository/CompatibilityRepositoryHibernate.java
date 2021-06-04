package com.example.plantlover.data.repository;

import com.example.plantlover.data.dao.CompatibilityDao;
import com.example.plantlover.domain.entity.Compatibility;
import com.example.plantlover.domain.service.CompatibilityRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompatibilityRepositoryHibernate implements CompatibilityRepositoryInterface {

    private final CompatibilityDao dao;

    @Autowired
    public CompatibilityRepositoryHibernate(CompatibilityDao dao) {
        this.dao = dao;
    }

    @Override
    public Compatibility save(Compatibility compatibility) {
        return dao.save(compatibility);
    }

    @Override
    public Compatibility findById(Integer id) throws DalException {
        return dao.findById(id).orElseThrow(() -> new DalException("There is no Compatibility entity with id = " + id));
    }

    @Override
    public List<Compatibility> findAll() {
        return (List<Compatibility>) dao.findAll();
    }

    @Override
    public void delete(Compatibility compatibility) {
        dao.delete(compatibility);
    }

}
