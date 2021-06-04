package com.example.plantlover.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Compatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String plantA;
    private String plantB;
    private RelationEnum relation;

    public Compatibility(String plantA, String plantB, RelationEnum relation) {
        this.plantA = plantA;
        this.plantB = plantB;
        this.relation = relation;
    }

}
