package com.cenfotec.ejpersona.lab5persona.repository;

import com.cenfotec.ejpersona.lab5persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findPersonaByNombre(String nombre);
}
