package com.cenfotec.ejpersona.lab5persona.service;

import com.cenfotec.ejpersona.lab5persona.domain.Persona;
import com.cenfotec.ejpersona.lab5persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public void savePersona(Persona persona){
        personaRepository.save(persona);
    }

    public List<Persona> getAll(){
        return personaRepository.findAll();
    }
    public Persona getByNombre(String nombre) {
        return personaRepository.findPersonaByNombre(nombre);
    }
    public Persona getById(Long id) {
        return personaRepository.getById(id);
    }
    public Persona updatePersona(Persona dto) {
        Persona persona = personaRepository.getById(dto.getId());

        persona.setId(dto.getId());
        persona.setNombre(dto.getNombre());
        persona.setApellido1(dto.getApellido1());
        persona.setApellido2(dto.getApellido2());
        persona.setDireccion(dto.getDireccion());

        return personaRepository.save(persona);
    }
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }
}
