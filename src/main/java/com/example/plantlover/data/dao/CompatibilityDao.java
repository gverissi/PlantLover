package com.example.plantlover.data.dao;

import com.example.plantlover.domain.entity.Compatibility;
import com.example.plantlover.domain.entity.RelationEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompatibilityDao extends CrudRepository<Compatibility, Integer> {

    @Query("select c from Compatibility c where (c.plantA=:plantName or c.plantB=:plantName) and c.relation=:relation")
    List<Compatibility> findAllAssociations(@Param("plantName") String plantName, @Param("relation") RelationEnum relation);

    @Query("select c.relation from Compatibility c where (c.plantA=:plantAName and c.plantB=:plantBName) or (c.plantB=:plantAName and c.plantA=:plantBName)")
    String getRelation(@Param("plantAName") String plantAName, @Param("plantBName") String plantBName);

}
