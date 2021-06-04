package com.example.plantlover.ui.comtroller;

import com.example.plantlover.domain.entity.Compatibility;
import com.example.plantlover.domain.entity.RelationEnum;
import com.example.plantlover.domain.service.BllException;
import com.example.plantlover.domain.service.CompatibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/plant-lover")
public class CompatibilityController {

    private final CompatibilityService service;

    @Autowired
    public CompatibilityController(CompatibilityService service) throws BllException {
        this.service = service;

        service.create(new Compatibility("Tomate", "Carotte", RelationEnum.FRIENDS));
        service.create(new Compatibility("Tomate", "Persil", RelationEnum.FRIENDS));
        service.create(new Compatibility("Tomate", "Chou", RelationEnum.FRIENDS));
        service.create(new Compatibility("Tomate", "Poireau", RelationEnum.FRIENDS));

        service.create(new Compatibility("Tomate", "Betterave", RelationEnum.ENEMIES));
        service.create(new Compatibility("Tomate", "Fenouil", RelationEnum.ENEMIES));
        service.create(new Compatibility("Tomate", "Pois", RelationEnum.ENEMIES));

        service.create(new Compatibility("Tomate", "Ail", RelationEnum.NEUTRAL));
        service.create(new Compatibility("Tomate", "Echalote", RelationEnum.NEUTRAL));
    }

    @GetMapping("/compatibilities/show-create-form")
    public String showCreateForm(Model model) {
        Compatibility compatibility = new Compatibility("Plant A", "Plant B", RelationEnum.NEUTRAL);
        model.addAttribute("compatibility", compatibility);
        fillUpModelWithSelect(model);
        return "show-create-form";
    }

    @PostMapping("/compatibilities/submit-create-form")
    public String submitCreateForm(@Valid Compatibility compatibility, BindingResult result, Model model) {
        if (result.hasErrors()) {
            fillUpModelWithSelect(model);
            return "show-create-form";
        }
        try {
            service.create(compatibility);
            return "redirect:/plant-lover/compatibilities";
        } catch (BllException e) {
            result.addError(new FieldError("compatibility", "plantB", e.getMessage()));
            fillUpModelWithSelect(model);
            return "show-create-form";
        }
    }

    @GetMapping("/compatibilities")
    public String readAll(Model model) {
        List<Compatibility> compatibilities = service.readAll();
        model.addAttribute("compatibilities", compatibilities);
        return "compatibilities";
    }

    @GetMapping("/compatibilities/{id}/delete")
    public String delete(@PathVariable Integer id) {
        try {
            service.delete(id);
        } catch (BllException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return "redirect:/plant-lover/compatibilities";
    }

    private void fillUpModelWithSelect(Model model) {
        List<RelationEnum> relations = new ArrayList<>();
        relations.add(RelationEnum.FRIENDS);
        relations.add(RelationEnum.ENEMIES);
        relations.add(RelationEnum.NEUTRAL);
        model.addAttribute("relations", relations);
    }


}
