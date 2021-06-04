package com.example.plantlover.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
public class Compatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String plantA;

    @NotBlank
    private String plantB;

    private RelationEnum relation;

    public Compatibility(String plantA, String plantB, RelationEnum relation) {
        this.plantA = plantA;
        this.plantB = plantB;
        this.relation = relation;
    }

}
