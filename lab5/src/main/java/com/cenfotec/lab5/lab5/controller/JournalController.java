package com.cenfotec.lab5.lab5.controller;

import com.cenfotec.lab5.lab5.domain.Journal;
import com.cenfotec.lab5.lab5.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;

@Controller
public class JournalController {
    @Autowired
    JournalService journalService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("journal", journalService.getAll());
        return "index";
    }

    @RequestMapping(value = "/addJournalForm", method = RequestMethod.GET)
    public String addJournal(Model model){
        model.addAttribute(new Journal());
        return "addJournalForm";
    }

    @PostMapping("/saveJournal")
    public String saveJournal(@ModelAttribute("journal") Journal journal) {
        journal.setCreated(Date.from(Instant.now()));
        journalService.saveJournal(journal);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Journal journal = journalService.getById(id);

        model.addAttribute("journal", journal);
        return "updateJournalForm";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Journal journal, BindingResult result, Model model) {
        /*
        if (result.hasErrors()) {
            journal.setId(id);
            return "updateJournalForm";
        }*/
        //journal.setCreated(journal.getCreated());
        journalService.updateJournal(journal);
        model.addAttribute("journals", journalService.getAll());
        return "redirect:/";
    }

    @GetMapping(value = "/deleteJournal/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        journalService.deleteJournal(id);
        return "redirect:/";
    }
}
