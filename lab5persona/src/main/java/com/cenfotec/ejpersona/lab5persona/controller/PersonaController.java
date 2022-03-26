package com.cenfotec.ejpersona.lab5persona.controller;

import com.cenfotec.ejpersona.lab5persona.domain.Persona;
import com.cenfotec.ejpersona.lab5persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class PersonaController {
    @Autowired
    PersonaService personaService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("persona", personaService.getAll());
        return "index";
    }

    @RequestMapping(value = "/addPersonaForm", method = RequestMethod.GET)
    public String addPersona(Model model){
        model.addAttribute(new Persona());
        return "addPersonaForm";
    }

    @PostMapping("/savePersona")
    public String savePersona(@ModelAttribute("persona") Persona persona) {
        personaService.savePersona(persona);
        return "redirect:/";
    }

    @RequestMapping("/showUpdateForm/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Persona persona = personaService.getById(id);

        model.addAttribute("persona", persona);
        return "updatePersonaForm";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Persona persona, BindingResult result, Model model) {
        personaService.updatePersona(persona);
        model.addAttribute("personas", personaService.getAll());
        return "redirect:/";
    }

    @GetMapping(value = "/deletePersona/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        personaService.deletePersona(id);
        return "redirect:/";
    }
}
