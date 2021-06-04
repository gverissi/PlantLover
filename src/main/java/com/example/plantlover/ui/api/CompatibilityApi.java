package com.example.plantlover.ui.api;

import com.example.plantlover.domain.entity.Compatibility;
import com.example.plantlover.domain.service.CompatibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompatibilityApi {

    private final CompatibilityService service;

    @Autowired
    public CompatibilityApi(CompatibilityService service) {
        this.service = service;
    }

    @GetMapping("/compatibilities/{plantName}/friends")
    public List<String> findAllFriends(@PathVariable String plantName) {
        return service.findAllFriends(plantName);
    }

    @GetMapping("/compatibilities/{plantName}/enemies")
    public List<String> findAllEnemies(@PathVariable String plantName) {
        return service.findAllEnemies(plantName);
    }

    @GetMapping("/compatibilities/relation/{plantAName}/{plantBName}")
    public String getRelation(@PathVariable String plantAName, @PathVariable String plantBName) {
        return service.getRelation(plantAName, plantBName);
    }

}
