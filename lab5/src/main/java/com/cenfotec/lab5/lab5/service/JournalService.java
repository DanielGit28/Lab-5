package com.cenfotec.lab5.lab5.service;

import com.cenfotec.lab5.lab5.domain.Journal;
import com.cenfotec.lab5.lab5.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    JournalRepository journalRepository;
    public void saveJournal(Journal journal){
        journalRepository.save(journal);
    }
    public List<Journal> getAll(){
        return journalRepository.findAll();
    }
    public Journal getByTitle(String title) {
        return journalRepository.findJournalByTitle(title);
    }
    public Journal getById(Long id) {
        return journalRepository.findById(id).get();
    }
    public Journal updateJournal(Journal dto) {
        Journal jn = journalRepository.findById(dto.getId()).get();
        jn.setId(dto.getId());
        jn.setTitle(dto.getTitle());
        jn.setCreated(Date.from(Instant.now()));
        System.out.println(jn.getCreated());
        jn.setSummary(dto.getSummary());

        return journalRepository.save(jn);
    }
    public void deleteJournal(Long id) {
        journalRepository.deleteById(id);
    }

}
